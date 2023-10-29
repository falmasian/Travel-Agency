package com.flightagency.service;

import com.flightagency.Mapper.FilterFlightMapper;
import com.flightagency.config.dao.FlightInfoDao;
import com.flightagency.dto.FilterFlightDto;
import com.flightagency.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class FilterService {

    private FlightInfoDao flightInfoDao;
    private FilterFlightMapper filterFlightMapper;

    public FilterService(FlightInfoDao flightInfo, FilterFlightMapper filterFlightMapper) {
        this.flightInfoDao = flightInfo;
        this.filterFlightMapper = filterFlightMapper;
    }

    public List<FilterFlightDto> filter(FilterFlightDto filterFlightDto) {
        Flight flight = filterFlightMapper.toFlight(filterFlightDto);
        return flightInfoDao.getAllFilterFlightInfo(flight).stream().filter(f -> f.getRemainingSeats() > 0).collect(Collectors.toList())
                .stream()
                .map(filterFlightMapper::toFilterFlightDto)
                .collect(toList());
    }
}
