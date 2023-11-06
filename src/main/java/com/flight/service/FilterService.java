package com.flight.service;

import com.flight.Mapper.FilterFlightMapper;
import com.flight.Mapper.FlightMapper;
import com.flight.aspect.ServiceAnnotation;
import com.flight.dto.FilterFlightDto;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import com.flight.repository.FlightIfoRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class FilterService {

    private final FlightIfoRepository flightRepository;
    private final FilterFlightMapper filterFlightMapper;
    private final FlightMapper flightMapper;

    public FilterService(FlightIfoRepository flightRepository, FilterFlightMapper filterFlightMapper, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.filterFlightMapper = filterFlightMapper;
        this.flightMapper = flightMapper;
    }

    @ServiceAnnotation
    public List<FlightDto> filter(FilterFlightDto filterFlightDto) {
        FlightInfo flight = filterFlightMapper.toFlight(filterFlightDto);

        java.util.Date inputDate = new java.util.Date(flight.getFlyDate().getTime());


        return flightRepository.findFlightInfoByOriginIdAndDestinationId(flight.getOriginId()
                        , flight.getDestinationId())
                .stream()
                .filter(f -> f.getRemainingSeats() > 0)
                .filter(f -> (new Date(f.getFlyDateTime().getTime())).equals(inputDate))
                .map(flightMapper::toFlightDto).toList();
    }
}
