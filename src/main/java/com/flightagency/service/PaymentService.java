package com.flightagency.service;

import com.flightagency.Mapper.PaymentMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dao.FlightInfoDao;
import com.flightagency.dao.ReservationDao;
import com.flightagency.dto.PaymentDto;
import com.flightagency.entity.Flight;
import com.flightagency.entity.Reservation;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private final FlightInfoDao flightInfoDao;
    private final ReservationDao reservationDao;
    private PaymentMapper paymentMapper;
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(FlightInfoDao flightInfoDao, ReservationDao reservationDao, PaymentMapper paymentMapper) {
        this.flightInfoDao = flightInfoDao;
        this.reservationDao = reservationDao;
        this.paymentMapper = paymentMapper;
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
        return flightInfoDao.getCostByFlightId(flightId);
    }

    private int getRemainSeatsByFlightId(int flightId) {
        return flightInfoDao.getRemainSeatsByFlightId(flightId);
    }

    private void deleteReservationFromCache(String tracingCode) {
        CreatedCaches.reservationCacheManager.removeItemFromCache(CreatedCaches.reservedFlightsCacheName, tracingCode);
    }

    private void updateCapacity(Reservation reservation) {
        Flight flightCapacity = CreatedCaches.flightCapacityCacheManager.getItemFromCache(CreatedCaches.flightCapacityCacheName, reservation.getFlightId());
        flightCapacity.updateAfterPayment(reservation.getNumberOfTickets());
    }

    private synchronized void insertInDatabase(Reservation reservation) {
        for (int i = 0; i < reservation.getNumberOfTickets(); i++) {
            reservationDao.insertReservation(reservation.getCustomerId(), reservation.getFlightId(), reservation.getFromNationalCodesByIndex(i), reservation.getTrackingCode());
        }
        logger.info("reservation with tracking code {} is inserted in database.", reservation.getTrackingCode());
    }

    public synchronized void updateFlightRemainSeats(int flightId, int numOfTickets) {
        int res = flightInfoDao.decreaseRemainSeatsById(flightId, numOfTickets);
        if (res > 0) {
            logger.info("remain seats of flight with ID {} is updated.", flightId);
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
