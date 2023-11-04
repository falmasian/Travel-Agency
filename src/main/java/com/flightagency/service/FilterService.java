package com.flightagency.service;

import com.flightagency.Mapper.FilterFlightMapper;
import com.flightagency.Mapper.FlightMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dao.FlightInfoDao;
import com.flightagency.dto.FilterFlightDto;
import com.flightagency.dto.FlightDto;
import com.flightagency.entity.Flight;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterService {

    private FlightInfoDao flightInfoDao;
    private FilterFlightMapper filterFlightMapper;
    private FlightMapper flightMapper;

    public FilterService(FlightInfoDao flightInfo, FilterFlightMapper filterFlightMapper, FlightMapper flightMapper) {
        this.flightInfoDao = flightInfo;
        this.filterFlightMapper = filterFlightMapper;
        this.flightMapper = flightMapper;
    }

    @ServiceAnnotation
    public List<FlightDto> filter(FilterFlightDto filterFlightDto) {
        Flight flight = filterFlightMapper.toFlight(filterFlightDto);
        return flightInfoDao.getAllFilterFlightInfo(flight).stream().filter(f -> f.getRemainingSeats() > 0)
                .map(flightMapper::toFlightDto).toList();
    }
}
