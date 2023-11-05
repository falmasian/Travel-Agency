package com.flightagency.service;

import com.flightagency.Mapper.FilterFlightMapper;
import com.flightagency.Mapper.FlightMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dto.FilterFlightDto;
import com.flightagency.dto.FlightDto;
import com.flightagency.entity.FlightInfo;
import com.flightagency.repository.FlightRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterService {

    private final FlightRepository flightRepository;
    private FilterFlightMapper filterFlightMapper;
    private FlightMapper flightMapper;

    public FilterService(FlightRepository flightRepository, FilterFlightMapper filterFlightMapper, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.filterFlightMapper = filterFlightMapper;
        this.flightMapper = flightMapper;
    }

    @ServiceAnnotation
    public List<FlightDto> filter(FilterFlightDto filterFlightDto) {
        FlightInfo flight = filterFlightMapper.toFlight(filterFlightDto);
        return flightRepository.findFlightsByOriginIdAndDestinationIdAndFlyDate(flight.getOriginId()
                        , flight.getDestinationId(), flight.getFlyDate())
                .stream().filter(f -> f.getRemainingSeats() > 0)
                .map(flightMapper::toFlightDto).toList();
    }
}
