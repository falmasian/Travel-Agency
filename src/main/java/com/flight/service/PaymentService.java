package com.flight.service;

import com.flight.Mapper.PaymentMapper;
import com.flight.Mapper.ReserveMapper;
import com.flight.aspect.ServiceLoggingAspect;
import com.flight.dto.PaymentDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.entity.Reserve;
import com.flight.repository.FlightIfoRepository;
import com.flight.repository.ReserveRepository;
import com.flight.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentService {

    private final FlightIfoRepository flightRepository;
    private final ReserveRepository reserveRepository;
    private final PaymentMapper paymentMapper;
    private final ReserveMapper reserveMapper;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(FlightIfoRepository flightRepository, ReserveRepository reserveRepository, PaymentMapper paymentMapper, ReserveMapper reserveMapper) {
        this.flightRepository = flightRepository;
        this.reserveRepository = reserveRepository;
        this.paymentMapper = paymentMapper;
        this.reserveMapper = reserveMapper;
    }

    public float payment(PaymentDto paymentDto) {
        String tracingCode = paymentMapper.toTrackingCode(paymentDto);
        float cost = pay(tracingCode);
        if (cost > 0) {
            try {
                confirmReservation(tracingCode);
                logger.info("reservation with tracking code {} is payed.", tracingCode);
            } catch (Exception ex) {
                logger.error("Error in the server");
            }
        }
        return cost;
    }

    @ServiceLoggingAspect
    private float pay(String tracingCode) {
        try {
            Reservation reservation = findReservationByTrackingCode(tracingCode);
            if (reservation == null) {
                return 0;
            } else {
                if (reservation.getNumberOfTickets() <= getRemainSeatsByFlightId(reservation.getFlightId())) {
                    return calculateCostOfReservation(reservation);
                } else {
                    deleteReservationFromCache(tracingCode);
                    return -1;
                }
            }
        } catch (NumberFormatException ex) {
            logger.error("Error in the server");
        }
        return -2;
    }

    private Reservation findReservationByTrackingCode(String tracingCode) {
        return CreatedCaches.reservationCacheManager.getItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
    }

    private float calculateCostOfReservation(Reservation reservation) {
        float eachTicketCost = getCostByFlightId(reservation.getFlightId());
        return eachTicketCost * reservation.getNumberOfTickets();
    }

    private float getCostByFlightId(int flightId) {

        Optional<FlightInfo> f = flightRepository.findById(flightId);
        return f.get().getCost();
    }

    private int getRemainSeatsByFlightId(int flightId) {
        Optional<FlightInfo> f = flightRepository.findById(flightId);
        return f.get().getRemainingSeats();
    }

    private void deleteReservationFromCache(String tracingCode) {
        CreatedCaches.reservationCacheManager.removeItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
    }

    private synchronized void updateCapacity(Reservation reservation) {
        FlightInfo flightInfo = CreatedCaches.flightCapacityCacheManager.getItemFromCache(CreatedCaches.flightCapacityCacheName, reservation.getFlightId());
        int numOfCompleted = reservation.getNumberOfTickets();
        flightInfo.setCompletedReserves(flightInfo.getCompletedReserves() + numOfCompleted);
        flightInfo.setTemporaryReserves(flightInfo.getTemporaryReserves() - numOfCompleted);
        flightInfo.setRemainingSeats(flightInfo.getRemainingSeats() - numOfCompleted);
    }

    private synchronized void insertInDatabase(Reservation reservation) {
        for (int i = 0; i < reservation.getNumberOfTickets(); i++) {
            Reserve reserve = reserveMapper.toReserve(reservation, i);
            reserveRepository.save(reserve);
        }
        logger.info("reservation with tracking code {} is inserted in database.", reservation.getTrackingCode());
    }

    public synchronized void updateFlightRemainSeats(int flightId, int numOfTickets) {


        Optional<FlightInfo> optionalFlight = flightRepository.findById(flightId);

        if (optionalFlight.isPresent()) {
            FlightInfo flight = optionalFlight.get();
            int currentAvailableSeats = flight.getRemainingSeats();
            flight.setRemainingSeats(currentAvailableSeats - numOfTickets);
            flightRepository.save(flight);
        }

//        if (res > 0) {
//            logger.info("remain seats of flight with ID {} is updated.", flightId);
//        }
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
