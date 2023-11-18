package com.flight.service;

import com.flight.Mapper.FilterFlightMapper;
import com.flight.Mapper.FlightMapper;
import com.flight.aspect.ServiceLoggingAspect;
import com.flight.dto.FilterFlightDto;
import com.flight.dto.FilterResponseDto;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import com.flight.repository.FlightIfoRepository;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
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

    @ServiceLoggingAspect
    public FilterResponseDto filter(FilterFlightDto filterFlightDto) {
        FlightInfo flight = filterFlightMapper.toFlight(filterFlightDto);

        Calendar c = Calendar.getInstance();
        c.setTime(flight.getFlyDate());
        c.add(Calendar.DATE, 1);
        Date nextDay = c.getTime();
        List<FlightDto> flightDtoList = flightRepository.findFlightInfoByOriginIdAndDestinationId(flight.getOriginId()
                        , flight.getDestinationId())
                .stream()
                .filter(f -> f.getRemainingSeats() > 0)
                .filter(f -> f.getFlyDateTime().after(flight.getFlyDate()))
                .filter(f -> f.getFlyDateTime().before(nextDay))
                .map(flightMapper::toFlightDto).toList();
        return new FilterResponseDto(flightDtoList);
    }
}
