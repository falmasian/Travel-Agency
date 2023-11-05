package com.flightagency.service;

import com.flightagency.Mapper.FlightMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dao.FlightInfoDao;
import com.flightagency.dto.FlightDto;
import com.flightagency.entity.FlightInfo;
import com.flightagency.repository.FlightRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FlightService {

    private final FlightRepository flightRepository;
    private FlightMapper flightMapper;

    public FlightService(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @ServiceAnnotation
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
    }

    @ServiceAnnotation
    public boolean deleteFlight(int id) {
        try {
            flightRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @ServiceAnnotation
    public void insertFlight(FlightDto flightDto) {
        FlightInfo flightInfo = flightMapper.toFlight(flightDto);
        flightRepository.save(flightInfo);
    }
}
