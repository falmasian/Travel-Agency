package com.flight.service;


import com.flight.Mapper.CancellationMapper;
import com.flight.aspect.Service;
import com.flight.cache.CacheElement;
import com.flight.dto.CancellationDto;
import com.flight.dto.CancellingResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.InvalidInputException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.repository.FlightIfoRepository;
import com.flight.repository.ReserveRepository;
import com.flight.server.CreatedCaches;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class CancellationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancellationService.class);
    private final FlightIfoRepository flightRepository;
    private final ReserveRepository reserveRepository;
    private final CancellationMapper cancellationMapper;

    public CancellationService(FlightIfoRepository flightRepository, ReserveRepository reserveRepository
            , CancellationMapper cancellationMapper) {
        this.flightRepository = flightRepository;
        this.reserveRepository = reserveRepository;
        this.cancellationMapper = cancellationMapper;
    }

    @Service
    @Transactional
    public CancellingResponseDto cancel(CancellationDto cancellationDto) throws ReservationNotFoundException
            , FlightNotFoundException {
        Reservation inputReservation = cancellationMapper.toReservation(cancellationDto);
        int flightId = inputReservation.getFlightId();
        Optional<FlightInfo> optionalFlightInfo = flightRepository.findById(flightId);
        if (optionalFlightInfo.isEmpty()) {
            throw new FlightNotFoundException("There is no flight with this flight number.");
        }
        FlightInfo flightInfo = optionalFlightInfo.get();
//        if (flightInfo.getFlyDateTime().before(new Date())) {
//            throw new InvalidInputException("The date of this flight is in the past. it was done in the past");
//        }
        List<Reservation> reservations = reserveRepository
                .findReserveByCustomerIdAndFlightId(inputReservation.getCustomerId()
                        , inputReservation.getFlightId())
                .stream().toList();
        if (reservations == null || reservations.size() <= 0) {
            throw new ReservationNotFoundException("There is no reservation with this national code and flight number.");
        }
        for(String nationalcode : cancellationDto.getNationalCodes()){
            if(nationalcode.trim().length() != 10){
               throw new InvalidInputException("national code should have 10 character");
            }
        }
        int numberOfTickets = reservations.size();
        float cost = flightInfo.getCost() * numberOfTickets;
        int currentAvailableSeats = flightInfo.getRemainingSeats();
        flightInfo.setRemainingSeats(currentAvailableSeats + numberOfTickets);
        flightRepository.save(flightInfo);

        FlightInfo flight = flightRepository.getReferenceById(flightId);
        CreatedCaches.flightCapacityCacheManager.putInCacheWithName(CreatedCaches.flightCapacityCacheName
                , flightId, new CacheElement<>(flight));
        updateAfterCancellation(numberOfTickets, flight);
        for (String code : inputReservation.getNationalCodes()) {
            reserveRepository.deleteByCustomerIdAndPassengerNationalCode(inputReservation.getCustomerId(), code);
        }
        LOGGER.info("tickets cancelled.");
        return new CancellingResponseDto(cost);
    }

    @Transactional
    public synchronized void updateAfterCancellation(int numOfCancelled, FlightInfo flight) {
        flight.setCompletedReserves(flight.getCompletedReserves() - numOfCancelled);
        flight.setRemainingSeats(flight.getRemainingSeats() + numOfCancelled);
        flight.setCompletedReserves(flight.getCompletedReserves() - numOfCancelled);
    }
}
