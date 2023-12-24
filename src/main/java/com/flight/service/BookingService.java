package com.flight.service;

import com.flight.Mapper.BookingMapper;
import com.flight.cache.CacheElement;
import com.flight.dto.BookingDto;
import com.flight.dto.ReservationResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.InsufficientSeatsException;
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

    public ReservationResponseDto book(BookingDto bookingDto) throws FlightNotFoundException
            , InsufficientSeatsException {
        Reservation reservation = bookingMapper.toReservation(bookingDto);
        Optional<FlightInfo> chosenFlight = flightRepository.findById(reservation.getFlightId());
        if (chosenFlight.isEmpty()) {
            throw new FlightNotFoundException("There is no Flight with this flight number.");
        }
        int key = chosenFlight.get().getFlightNumber();
        if (!CreatedCaches.flightCapacityCacheManager.isKeyInCache(CreatedCaches.flightCapacityCacheName, key)) {
            CreatedCaches.flightCapacityCacheManager.putInCacheWithName
                    (CreatedCaches.flightCapacityCacheName, key, new CacheElement<>(chosenFlight.get()));
        }
        FlightInfo flightInfo = CreatedCaches.flightCapacityCacheManager
                .getItemFromCache(CreatedCaches.flightCapacityCacheName, key);
        int remain = flightInfo.getRemainingCapacity();
        if (remain < reservation.getNumberOfTickets()) {
            throw new InsufficientSeatsException("there are no enough seats to reserve.");
        }
        CreatedCaches.reservationCacheManager.putInCacheWithName(CreatedCaches.reservedFlightsCacheName
                , reservation.getTrackingCode(), new CacheElement(reservation));
        flightInfo.addTemporaryReserves(reservation.getNumberOfTickets());
        LOGGER.info("a temporary reservation with tracking code {} created by customer with ID {} "
                , reservation.getTrackingCode(), reservation.getCustomerId());
        return new ReservationResponseDto(reservation.getTrackingCode());
    }
}
