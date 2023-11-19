package com.flight.service;

import com.flight.Mapper.BookingMapper;
import com.flight.aspect.Service;
import com.flight.cache.CacheElement;
import com.flight.dto.BookingDto;
import com.flight.dto.ReservationResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.repository.FlightIfoRepository;
import com.flight.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);
    private final FlightIfoRepository flightRepository;
    private final BookingMapper bookingMapper;


    public BookingService(FlightIfoRepository flightRepository, BookingMapper bookingMapper) {
        this.flightRepository = flightRepository;
        this.bookingMapper = bookingMapper;
    }

    @Service
    public ReservationResponseDto book(BookingDto bookingDto) {
        Reservation reservation = bookingMapper.toReservation(bookingDto);
        try {
            Optional<FlightInfo> chosenFlight = flightRepository.findById(reservation.getFlightId());
            if (chosenFlight.isEmpty()) {
                return new ReservationResponseDto("-1");
            }
            int key = chosenFlight.get().getFlightNumber();
            if (!CreatedCaches.flightCapacityCacheManager.isKeyInCache(CreatedCaches.flightCapacityCacheName, key)) {
                CreatedCaches.flightCapacityCacheManager.putInCacheWithName
                        (CreatedCaches.flightCapacityCacheName, key, new CacheElement<>(chosenFlight.get()));
            }
            FlightInfo flightInfo = CreatedCaches.flightCapacityCacheManager
                    .getItemFromCache(CreatedCaches.flightCapacityCacheName, key);
            int remain = flightInfo.getRemainingCapacity();
            if (remain >= reservation.getNumberOfTickets()) {
                CreatedCaches.reservationCacheManager.putInCacheWithName(CreatedCaches.reservedFlightsCacheName
                        , reservation.getTrackingCode(), new CacheElement(reservation));
                flightInfo.addTemporaryReserves(reservation.getNumberOfTickets());
                LOGGER.info("a temporary reservation with tracking code {} created by customer with ID {} "
                        , reservation.getTrackingCode(), reservation.getCustomerId());
                return new ReservationResponseDto(reservation.getTrackingCode());
            }
            //todo: khafe sazie exception
        } catch (Exception ex) {
            LOGGER.error("Error in the server");
        }
        return new ReservationResponseDto("");
    }
}
