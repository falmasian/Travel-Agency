package com.flightagency.service;

import com.flightagency.Mapper.BookingMapper;
import com.flightagency.cache.CacheElement;
import com.flightagency.config.dao.CityDao;
import com.flightagency.config.dao.FlightInfoDao;
import com.flightagency.dto.BookingDto;
import com.flightagency.entity.Flight;
import com.flightagency.entity.Reservation;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    CityDao cityDao;
    FlightInfoDao flightInfoDao;
    private BookingMapper bookingMapper;


    public BookingService(CityDao cityDao, FlightInfoDao flightInfoDao, BookingMapper bookingMapper) {
        this.cityDao = cityDao;
        this.flightInfoDao = flightInfoDao;
        this.bookingMapper = bookingMapper;
    }

    public boolean booking(BookingDto bookingDto) {
        Reservation reservation = bookingMapper.toReservation(bookingDto);
        try {
            Flight chosenFlight = flightInfoDao.getFlightInfoByFlightNumber(reservation.getFlightId());
            int key = chosenFlight.getFlightNumber();
            if (!CreatedCaches.flightCapacityCacheManager.isKeyInCache(CreatedCaches.flightCapacityCacheName, key)) {
                boolean result = CreatedCaches.flightCapacityCacheManager
                        .putInCacheWithName(CreatedCaches.flightCapacityCacheName, key, new CacheElement<Flight>(chosenFlight));
            }
            Flight fc = CreatedCaches.flightCapacityCacheManager.getItemFromCache(CreatedCaches.flightCapacityCacheName, key);
            int remain = fc.getRemainingCapacity();
            if (remain >= reservation.getNumberOfTickets()) {
                CreatedCaches.reservationCacheManager.putInCacheWithName(CreatedCaches.reservedFlightsCacheName, reservation.getTrackingCode(), new CacheElement(reservation));
                fc.addTemporaryReserves(reservation.getNumberOfTickets());
                logger.info("a temporary reservation with tracking code {} created by customer with ID {} ", reservation.getTrackingCode(), reservation.getCustomerId());
                return true;
            }
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
        return false;
    }
}
