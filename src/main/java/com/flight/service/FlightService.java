package com.flight.service;

import com.flight.Mapper.FlightMapper;
import com.flight.dto.AllFlightsResponse;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import com.flight.exception.FlightNotFoundException;
import com.flight.repository.FlightIfoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FlightService {

    private final FlightIfoRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightIfoRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    public AllFlightsResponse getAll() throws FlightNotFoundException {
        List<FlightInfo> flightInfoList = flightRepository.findAll();
        if (flightInfoList.isEmpty()){
            throw new FlightNotFoundException("there are no available flights.");
        }
        List<FlightDto> flightDtoList =  flightInfoList
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
        return new AllFlightsResponse(flightDtoList);
    }

    public boolean deleteById(int id) {
        try {
            flightRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public int insert(FlightDto flightDto) {
        FlightInfo flightInfo = flightMapper.toFlight(flightDto);
        flightRepository.save(flightInfo);
        return flightInfo.getFlightNumber();
    }
}
