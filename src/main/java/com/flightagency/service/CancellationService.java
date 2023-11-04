package com.flightagency.service;


import com.flightagency.Mapper.CancellationMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.cache.CacheElement;
import com.flightagency.dao.FlightInfoDao;
import com.flightagency.dao.ReservationDao;
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

    @ServiceAnnotation
    public float cancelling(CancellationDto cancellationDto) {
        Reservation inputReservation = cancellationMapper.toReservation(cancellationDto);
        try {
            List<Reservation> reservations = reservationDao.getAllReservationsByCostomerId(inputReservation.getCustomerId());
            if (reservations == null || reservations.size() <= 0) {
                return -1;
            }
            for (Reservation reserve : reservations) {
                System.out.println(reserve.getCustomerId() + " " + reserve.getNationalCode());
            }
            int numberOfTickets = reservations.size();
            int flightId = reservations.get(0).getFlightId();
            float cost = flightInfoDao.getCostByFlightId(flightId) * numberOfTickets;
            flightInfoDao.increaseRemainSeatsById(flightId, numberOfTickets);
            Flight flight = flightInfoDao.getFlightInfoByFlightNumber(flightId);
            CreatedCaches.flightCapacityCacheManager.putInCacheWithName(CreatedCaches.flightCapacityCacheName
                    , flightId, new CacheElement<Flight>(flight));
            flight.updateAfterCancellation(numberOfTickets);
            for (String code : inputReservation.getNationalCodes()) {
                reservationDao.deleteByCustomerIdAndPassengerNationalCode(inputReservation.getCustomerId(), code);
            }
            logger.info("tickets cancelled.");
            return cost;
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
        return -2;
    }
}
