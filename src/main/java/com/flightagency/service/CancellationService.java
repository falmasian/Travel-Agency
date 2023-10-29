package com.flightagency.service;

import com.flightagency.Mapper.CancellationMapper;
import com.flightagency.cache.CacheElement;
import com.flightagency.config.dao.FlightInfoDao;
import com.flightagency.config.dao.ReservationDao;
import com.flightagency.dto.CancellationDto;
import com.flightagency.entity.Flight;
import com.flightagency.entity.Reservation;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CancellationService {

    private static final Logger logger = LoggerFactory.getLogger(CancellationService.class);
    FlightInfoDao flightInfoDao;
    ReservationDao reservationDao;
    private CancellationMapper cancellationMapper;

    public CancellationService(FlightInfoDao flightInfoDao, ReservationDao reservationDao, CancellationMapper cancellationMapper) {
        this.flightInfoDao = flightInfoDao;
        this.reservationDao = reservationDao;
        this.cancellationMapper = cancellationMapper;
    }

    public boolean cancelling(CancellationDto cancellationDto) {
        Reservation reservation = cancellationMapper.toReservation(cancellationDto);
        try {
            List<Reservation> reservations = reservationDao.getAllReservationsByTrackingCode(reservation.getTrackingCode());
            if (reservations == null || reservations.size() <= 0) {
                return false;
            }
            for (Reservation reserve : reservations) {
                if (!reservation.getNationalCodes().contains(reserve.getNationalCode())) {
                    reservations.remove(reserve);
                }
            }
            int numberOfTickets = reservations.size();
            int flightId = reservations.get(0).getFlightId();
            float cost = flightInfoDao.getCostByFlightId(flightId) * numberOfTickets;
            flightInfoDao.increaseRemainSeatsById(flightId, numberOfTickets);
            Flight flight = flightInfoDao.getFlightInfoByFlightNumber(flightId);
            boolean result = CreatedCaches.flightCapacityCacheManager.putInCacheWithName(CreatedCaches.flightCapacityCacheName, flightId, new CacheElement<Flight>(flight));
            flight.updateAfterCancellation(numberOfTickets);
            for (String code : reservation.getNationalCodes()) {
                reservationDao.deleteByTrackingCodeAndNationalCode(reservation.getTrackingCode(), code);
            }
            logger.info("tickets cancelled.");
            return true;
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
        return false;
    }
}
