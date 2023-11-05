package com.flightagency.service;

import com.flightagency.Mapper.PaymentMapper;
import com.flightagency.Mapper.ReserveMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dto.PaymentDto;
import com.flightagency.entity.FlightInfo;
import com.flightagency.entity.Reservation;
import com.flightagency.entity.Reserve;
import com.flightagency.repository.FlightRepository;
import com.flightagency.repository.ReserveRepository;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final FlightRepository flightRepository;
    private final ReserveRepository reserveRepository;
    private PaymentMapper paymentMapper;
    private ReserveMapper reserveMapper;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(FlightRepository flightRepository, ReserveRepository reserveRepository, PaymentMapper paymentMapper, ReserveMapper reserveMapper) {
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

    @ServiceAnnotation
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
        return flightRepository.findCostById(flightId);
    }

    private int getRemainSeatsByFlightId(int flightId) {
        return flightRepository.findRemainingSeatsById(flightId);
    }

    private void deleteReservationFromCache(String tracingCode) {
        CreatedCaches.reservationCacheManager.removeItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
    }

    private void updateCapacity(Reservation reservation) {
        FlightInfo flightInfo = CreatedCaches.flightCapacityCacheManager.getItemFromCache(CreatedCaches.flightCapacityCacheName, reservation.getFlightId());
        flightInfo.updateAfterPayment(reservation.getNumberOfTickets());
    }

    private synchronized void insertInDatabase(Reservation reservation) {
        for (int i = 0; i < reservation.getNumberOfTickets(); i++) {
            Reserve reserve = reserveMapper.toReserve(reservation , i);
            reserveRepository.save(reserve);
        }
        logger.info("reservation with tracking code {} is inserted in database.", reservation.getTrackingCode());
    }

    public synchronized void updateFlightRemainSeats(int flightId, int numOfTickets) {
        flightRepository.decrementRemainingSeatsById(flightId, numOfTickets);
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
