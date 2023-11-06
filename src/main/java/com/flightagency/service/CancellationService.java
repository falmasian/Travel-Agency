package com.flightagency.service;


import com.flightagency.Mapper.CancellationMapper;
import com.flightagency.Mapper.ReserveMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.cache.CacheElement;
import com.flightagency.dto.CancellationDto;
import com.flightagency.entity.FlightInfo;
import com.flightagency.entity.Reservation;
import com.flightagency.repository.FlightIfoRepository;
import com.flightagency.repository.ReserveRepository;
import com.flightagency.server.CreatedCaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CancellationService {

    private static final Logger logger = LoggerFactory.getLogger(CancellationService.class);
    private final FlightIfoRepository flightRepository;
    private final ReserveRepository reserveRepository;
    private CancellationMapper cancellationMapper;
    private ReserveMapper reserveMapper;

    public CancellationService(FlightIfoRepository flightRepository, ReserveRepository reserveRepository
            , CancellationMapper cancellationMapper, ReserveMapper reserveMapper) {
        this.flightRepository = flightRepository;
        this.reserveRepository = reserveRepository;
        this.cancellationMapper = cancellationMapper;
        this.reserveMapper = reserveMapper;
    }

    @ServiceAnnotation
    public float cancelling(CancellationDto cancellationDto) {
        Reservation inputReservation = cancellationMapper.toReservation(cancellationDto);
        try {
            List<Reservation> reservations = reserveRepository.findReserveByCustomerId(inputReservation.getCustomerId())
                    .stream().map(reserveMapper::toReservation).toList();
            if (reservations == null || reservations.size() <= 0) {
                return -1;
            }
            for (Reservation reserve : reservations) {
                System.out.println(reserve.getCustomerId() + " " + reserve.getNationalCode());
            }
            int numberOfTickets = reservations.size();
            int flightId = reservations.get(0).getFlightId();
            float cost = flightRepository.findCostById(flightId) * numberOfTickets;



            Optional<FlightInfo> optionalFlight = flightRepository.findById(flightId);

            if (optionalFlight.isPresent()) {
                FlightInfo flight = optionalFlight.get();
                int currentAvailableSeats = flight.getRemainingSeats();
                flight.setRemainingSeats(currentAvailableSeats + numberOfTickets);
                flightRepository.save(flight);
            }





            FlightInfo flight = flightRepository.getReferenceById(flightId);
            CreatedCaches.flightCapacityCacheManager.putInCacheWithName(CreatedCaches.flightCapacityCacheName
                    , flightId, new CacheElement<FlightInfo>(flight));
            flight.updateAfterCancellation(numberOfTickets);
            for (String code : inputReservation.getNationalCodes()) {
                reserveRepository.deleteByCustomerIdAndPassengerNationalCode(inputReservation.getCustomerId(), code);
            }
            logger.info("tickets cancelled.");
            return cost;
        } catch (Exception ex) {
            logger.error("Error in the server");
        }
        return -2;
    }
}
