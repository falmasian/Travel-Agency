package com.flight.service;


import com.flight.Mapper.CancellationMapper;
import com.flight.aspect.Service;
import com.flight.cache.CacheElement;
import com.flight.dto.CancellationDto;
import com.flight.dto.CancellingResponseDto;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;
import com.flight.repository.FlightIfoRepository;
import com.flight.repository.ReserveRepository;
import com.flight.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    //todo : esme method bayad simple present bashe.
    public CancellingResponseDto cancelling(CancellationDto cancellationDto) {
        Reservation inputReservation = cancellationMapper.toReservation(cancellationDto);
        try {
            List<Reservation> reservations = reserveRepository.findReserveByCustomerId(inputReservation.getCustomerId())
                    .stream().toList();
            if (reservations == null || reservations.size() <= 0) {
                return new CancellingResponseDto(-1);
            }
            int numberOfTickets = reservations.size();
            int flightId = reservations.get(0).getFlightId();
            Optional<FlightInfo> f = flightRepository.findById(flightId);
            float cost = f.get().getCost() * numberOfTickets;

            Optional<FlightInfo> optionalFlight = flightRepository.findById(flightId);

            if (optionalFlight.isPresent()) {
                FlightInfo flight = optionalFlight.get();
                int currentAvailableSeats = flight.getRemainingSeats();
                flight.setRemainingSeats(currentAvailableSeats + numberOfTickets);
                flightRepository.save(flight);
            }

            FlightInfo flight = flightRepository.getReferenceById(flightId);
            CreatedCaches.flightCapacityCacheManager.putInCacheWithName(CreatedCaches.flightCapacityCacheName
                    , flightId, new CacheElement<>(flight));
            updateAfterCancellation(numberOfTickets, flight);
            for (String code : inputReservation.getNationalCodes()) {
                reserveRepository.deleteByCustomerIdAndPassengerNationalCode(inputReservation.getCustomerId(), code);
            }
            LOGGER.info("tickets cancelled.");
            return new CancellingResponseDto(cost);
        } catch (Exception ex) {
            LOGGER.error("Error in the server");
        }
        return new CancellingResponseDto(-2);
    }

    public synchronized void updateAfterCancellation(int numOfCancelled, FlightInfo flight) {
        flight.setCompletedReserves(flight.getCompletedReserves() - numOfCancelled);
        flight.setRemainingSeats(flight.getRemainingSeats() + numOfCancelled);
        flight.setCompletedReserves(flight.getCompletedReserves() - numOfCancelled);
    }
}
