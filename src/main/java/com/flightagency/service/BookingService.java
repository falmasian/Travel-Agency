package com.flightagency.service;

import com.flightagency.Mapper.BookingMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.cache.CacheElement;
import com.flightagency.dto.BookingDto;
import com.flightagency.entity.FlightInfo;
import com.flightagency.entity.Reservation;
import com.flightagency.repository.FlightIfoRepository;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    private final FlightIfoRepository flightRepository;
    private BookingMapper bookingMapper;


    public BookingService(FlightIfoRepository flightRepository, BookingMapper bookingMapper) {
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;
    }

    @ServiceAnnotation
    public String book(BookingDto bookingDto) {
        Reservation reservation = bookingMapper.toReservation(bookingDto);
        try {

            Optional<FlightInfo> chosenFlight = flightRepository.findById(reservation.getFlightId());
            if (chosenFlight.isEmpty()) {
                return "-1";
            }
            int key = chosenFlight.get().getFlightNumber();
            if (!CreatedCaches.flightCapacityCacheManager.isKeyInCache(CreatedCaches.flightCapacityCacheName, key)) {
                boolean result = CreatedCaches.flightCapacityCacheManager
                        .putInCacheWithName(CreatedCaches.flightCapacityCacheName, key
                                , new CacheElement<>(chosenFlight.get()));
            }
            FlightInfo fc = CreatedCaches.flightCapacityCacheManager
                    .getItemFromCache(CreatedCaches.flightCapacityCacheName, key);
            int remain = fc.getRemainingCapacity();
            if (remain >= reservation.getNumberOfTickets()) {
                CreatedCaches.reservationCacheManager.putInCacheWithName(CreatedCaches.reservedFlightsCacheName
                        , reservation.getTrackingCode(), new CacheElement(reservation));
                fc.addTemporaryReserves(reservation.getNumberOfTickets());
                logger.info("a temporary reservation with tracking code {} created by customer with ID {} "
                        , reservation.getTrackingCode(), reservation.getCustomerId());
                return reservation.getTrackingCode();
            }
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
        return "";
    }
}
