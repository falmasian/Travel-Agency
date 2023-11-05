package com.flightagency.Mapper;

import com.flightagency.dto.FlightDto;
import com.flightagency.entity.FlightInfo;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toFlightDto(FlightInfo flight) {
        return new FlightDto(flight.getFlightNumber(), flight.getOriginId(), flight.getDestinationId()
                , flight.getFlyDateTime(), flight.getCost(), flight.getCapacity(), flight.getRemainingSeats());
    }

    public FlightInfo toFlight(FlightDto flightDto) {
        return new FlightInfo(flightDto.getFlightNumber(), flightDto.getFlightNumber(), flightDto.getOriginId()
                , flightDto.getDestinationId(), flightDto.getFlyDateTime(), flightDto.getCost(), flightDto.getCapacity()
                , flightDto.getRemainingSeats());
    }
}
