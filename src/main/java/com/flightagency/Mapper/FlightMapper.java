package com.flightagency.Mapper;

import com.flightagency.dto.FlightDto;
import com.flightagency.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toFlightDto(Flight flight) {
        return new FlightDto(flight.getFlightNumber(), flight.getOriginId(), flight.getDestinationId()
                , flight.getFlyDateTime(), flight.getCost(), flight.getCapacity(), flight.getRemainingSeats());
    }

    public Flight toFlight(FlightDto flightDto) {
        return new Flight(flightDto.getFlightNumber(), flightDto.getFlightNumber(), flightDto.getOriginId()
                , flightDto.getDestinationId(), flightDto.getFlyDateTime(), flightDto.getCost(), flightDto.getCapacity()
                , flightDto.getRemainingSeats());
    }
}
