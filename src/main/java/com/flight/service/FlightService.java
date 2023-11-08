package com.flight.service;

import com.flight.Mapper.FlightMapper;
import com.flight.aspect.ServiceLoggingAspect;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import com.flight.repository.FlightIfoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FlightService {

    private final FlightIfoRepository flightRepository;
    private FlightMapper flightMapper;

    public FlightService(FlightIfoRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @ServiceLoggingAspect
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
    }

    @ServiceLoggingAspect
    public boolean deleteFlight(int id) {
        try {
            flightRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @ServiceLoggingAspect
    public void insertFlight(FlightDto flightDto) {
        FlightInfo flightInfo = flightMapper.toFlight(flightDto);
        flightRepository.save(flightInfo);
    }
}
