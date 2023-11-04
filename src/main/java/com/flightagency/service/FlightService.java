package com.flightagency.service;

import com.flightagency.Mapper.FlightMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dao.FlightInfoDao;
import com.flightagency.dto.FlightDto;
import com.flightagency.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FlightService {

    private final FlightInfoDao flightInfoDao;
    private FlightMapper flightMapper;

    public FlightService(FlightInfoDao flightInfoDao, FlightMapper flightMapper) {
        this.flightInfoDao = flightInfoDao;
        this.flightMapper = flightMapper;
    }

    @ServiceAnnotation
    public List<FlightDto> getAllFlights() {
        return flightInfoDao.getAllFlightInfo()
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
    }

    @ServiceAnnotation
    public boolean deleteFlight(int id) {
        return flightInfoDao.deleteFlightInfoById(id) > 0;
    }

    @ServiceAnnotation
    public void insertFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toFlight(flightDto);
        flightInfoDao.insertFlightInfo(flight);
    }
}
