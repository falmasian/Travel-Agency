package com.flight.service;

import com.flight.Mapper.PaymentMapper;
import com.flight.aspect.Service;
import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
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

    public PaymentService(FlightIfoRepository flightRepository, ReserveRepository reserveRepository
            , PaymentMapper paymentMapper) {
        this.flightRepository = flightRepository;
        this.reserveRepository = reserveRepository;
        this.paymentMapper = paymentMapper;
    }

    @Service
    @Transactional
    public PaymentResponseDto payment(PaymentDto paymentDto) {
        String tracingCode = paymentMapper.toTrackingCode(paymentDto);
        float cost = pay(tracingCode);
        if (cost > 0) {
            try {
                confirmReservation(tracingCode);
                LOGGER.info("reservation with tracking code {} is payed.", tracingCode);
            } catch (Exception ex) {
                LOGGER.error("Error in the server");
            }
        }
        return new PaymentResponseDto(cost);
    }

    private float pay(String tracingCode) {
        try {
            Reservation reservation = findReservationByTrackingCode(tracingCode);
            if (reservation == null) {
                return -2;
            } else {
                if (reservation.getNumberOfTickets() <= getRemainSeatsByFlightId(reservation.getFlightId())) {
                    return calculateCostOfReservation(reservation);
                } else {
                    deleteReservationFromCache(tracingCode);
                    return -1;
                }
            }
        } catch (NumberFormatException ex) {
            LOGGER.error("Error in the server");
        }
        return -2;
    }

    private Reservation findReservationByTrackingCode(String tracingCode) {
        return CreatedCaches.reservationCacheManager.getItemFromCache(CreatedCaches.reservedFlightsCacheName
                , tracingCode);
    }

    private float calculateCostOfReservation(Reservation reservation) {
        float eachTicketCost = getCostByFlightId(reservation.getFlightId());
        return eachTicketCost * reservation.getNumberOfTickets();
    }

    private float getCostByFlightId(int flightId) {

        Optional<FlightInfo> optionalFlightInfo = flightRepository.findById(flightId);
        return optionalFlightInfo.map(FlightInfo::getCost).orElse((float) -1);
    }

    private int getRemainSeatsByFlightId(int flightId) {
        Optional<FlightInfo> optionalFlightInfo = flightRepository.findById(flightId);
        return optionalFlightInfo.map(FlightInfo::getRemainingSeats).orElse(-1);
    }

    private void deleteReservationFromCache(String tracingCode) {
        CreatedCaches.reservationCacheManager.removeItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
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
    private synchronized void insertInDatabase(Reservation reservation){
        for (int i = 0; i < reservation.getNumberOfTickets(); i++) {
            reservation.setPassengerNationalCode(reservation.getFromNationalCodesByIndex(i));
            Reservation reserve = new Reservation(reservation.getCustomerId() , reservation.getFlightId()
                    , reservation.getFromNationalCodesByIndex(i) ,reservation.getTrackingCode());
            reserveRepository.save(reserve);
        }
        LOGGER.info("reservation with tracking code {} is inserted in database.", reservation.getTrackingCode());
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

    private void confirmReservation(String tracingCode){
        Reservation reservation = findReservationByTrackingCode(tracingCode);
        synchronized (this) {
            insertInDatabase(reservation);
            updateFlightRemainSeats(reservation.getFlightId(), reservation.getNumberOfTickets());
            deleteReservationFromCache(tracingCode);
            updateCapacity(reservation);
        }
    }
}
