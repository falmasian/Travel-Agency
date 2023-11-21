package com.flight.service;

import com.flight.Mapper.PaymentMapper;
import com.flight.aspect.Service;
import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.FailedToPayException;
import com.flight.exception.EnoughSeatsNotFoundException;
import com.flight.repository.FlightIfoRepository;
import com.flight.repository.ReserveRepository;
import com.flight.server.CreatedCaches;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentService {

    private final FlightIfoRepository flightRepository;
    private final ReserveRepository reserveRepository;
    private final PaymentMapper paymentMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(FlightIfoRepository flightRepository
            , ReserveRepository reserveRepository
            , PaymentMapper paymentMapper) {
        this.flightRepository = flightRepository;
        this.reserveRepository = reserveRepository;
        this.paymentMapper = paymentMapper;
    }

    @Service
    @Transactional
    public PaymentResponseDto pay(PaymentDto paymentDto) throws FailedToPayException
            , FlightNotFoundException
            , ReservationNotFoundException, EnoughSeatsNotFoundException {
        String tracingCode = paymentMapper.toTrackingCode(paymentDto);
        float cost = getCost(tracingCode);
        if (cost > 0) {
            try {
                confirmReservation(tracingCode);
                LOGGER.info("reservation with tracking code {} is payed.", tracingCode);
            } catch (Exception ex) {
                throw new FailedToPayException("Payment failed.");
            }
        }
        return new PaymentResponseDto(cost);
    }

    private float getCost(String tracingCode) throws ReservationNotFoundException
            , EnoughSeatsNotFoundException
            , FlightNotFoundException {
        Reservation reservation = findReservationByTrackingCode(tracingCode);
        if (reservation == null) {
            throw new ReservationNotFoundException("There is no reservation with this tracing code." +
                                                " \nor payment deadline has expired.");
        } else {
            if (reservation.getNumberOfTickets() <= getRemainSeatsByFlightId(reservation.getFlightId())) {
                return calculateCostOfReservation(reservation);
            } else {
                deleteReservationFromCache(tracingCode);
                throw new EnoughSeatsNotFoundException("There is no enough remain seats in this flights");
            }
        }
    }

    private Reservation findReservationByTrackingCode(String tracingCode) {
        return CreatedCaches.reservationCacheManager.getItemFromCache(CreatedCaches.reservedFlightsCacheName
                , tracingCode);
    }

    private float calculateCostOfReservation(Reservation reservation) throws FlightNotFoundException {
        float eachTicketCost = getCostByFlightId(reservation.getFlightId());
        return eachTicketCost * reservation.getNumberOfTickets();
    }

    private float getCostByFlightId(int flightId) throws FlightNotFoundException {
        Optional<FlightInfo> optionalFlightInfo = flightRepository.findById(flightId);
        if (optionalFlightInfo.isEmpty()) {
            throw new FlightNotFoundException("There is no flight with this flight number.");
        }
        return optionalFlightInfo.get().getCost();
    }

    private int getRemainSeatsByFlightId(int flightId) {
        Optional<FlightInfo> optionalFlightInfo = flightRepository.findById(flightId);
        return optionalFlightInfo.map(FlightInfo::getRemainingSeats).orElse(-1);
    }

    private void deleteReservationFromCache(String tracingCode) {
        CreatedCaches.reservationCacheManager
                .removeItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
    }

    private synchronized void updateCapacity(Reservation reservation) {
        FlightInfo flightInfo = CreatedCaches.flightCapacityCacheManager
                .getItemFromCache(CreatedCaches.flightCapacityCacheName, reservation.getFlightId());
        int numOfCompleted = reservation.getNumberOfTickets();
        flightInfo.setCompletedReserves(flightInfo.getCompletedReserves() + numOfCompleted);
        flightInfo.setTemporaryReserves(flightInfo.getTemporaryReserves() - numOfCompleted);
        flightInfo.setRemainingSeats(flightInfo.getRemainingSeats() - numOfCompleted);
    }

    @Transactional
    private synchronized void insertInDatabase(Reservation reservation) {
        for (int i = 0; i < reservation.getNumberOfTickets(); i++) {
            reservation.setPassengerNationalCode(reservation.getFromNationalCodesByIndex(i));
            Reservation reserve = new Reservation(reservation.getCustomerId(), reservation.getFlightId()
                    , reservation.getFromNationalCodesByIndex(i), reservation.getTrackingCode());
            reserveRepository.save(reserve);
        }
        LOGGER.info("reservation with tracking code {} is inserted in database."
                , reservation.getTrackingCode());
    }

    public synchronized void updateFlightRemainSeats(int flightId, int numOfTickets) {
        Optional<FlightInfo> optionalFlight = flightRepository.findById(flightId);

        if (optionalFlight.isPresent()) {
            FlightInfo flight = optionalFlight.get();
            int currentAvailableSeats = flight.getRemainingSeats();
            flight.setRemainingSeats(currentAvailableSeats - numOfTickets);
            flightRepository.save(flight);
        }
    }

    private void confirmReservation(String tracingCode) {
        Reservation reservation = findReservationByTrackingCode(tracingCode);
        synchronized (this) {
            insertInDatabase(reservation);
            updateFlightRemainSeats(reservation.getFlightId(), reservation.getNumberOfTickets());
            deleteReservationFromCache(tracingCode);
            updateCapacity(reservation);
        }
    }
}
