package com.flight.Mapper;

import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toFlightDto(FlightInfo flight) {
        return new FlightDto(flight.getFlightNumber(), flight.getOriginCity().getCityId(),flight.getOriginCity().getCityName()
                , flight.getDestinationCity().getCityId() , flight.getDestinationCity().getCityName()
                , flight.getFlyDateTime(), flight.getCost(), flight.getCapacity(), flight.getRemainingSeats());
    }

    public FlightInfo toFlight(FlightDto flightDto) {
        return new FlightInfo(flightDto.getFlightNumber(), flightDto.getFlightNumber(), flightDto.getOriginId()
                , flightDto.getDestinationId(), flightDto.getFlyDateTime(), flightDto.getCost(), flightDto.getCapacity()
                , flightDto.getRemainingSeats());
    }
}
