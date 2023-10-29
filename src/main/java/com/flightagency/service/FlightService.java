package com.flightagency.service;

import com.flightagency.Mapper.FlightMapper;
import com.flightagency.config.dao.FlightInfoDao;
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

    public List<FlightDto> getAllFlights() {
        return flightInfoDao.getAllFlightInfo()
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
    }

    public boolean deleteFlight(int id) {
        return flightInfoDao.deleteFlightInfoById(id) > 0;
    }

    public void insertFlight(FlightDto flightDto) {
        Flight flight = flightMapper.toFlight(flightDto);
        flightInfoDao.insertFlightInfo(flight);
    }
}
