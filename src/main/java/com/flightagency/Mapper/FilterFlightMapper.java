package com.flightagency.Mapper;

import com.flightagency.dto.FilterFlightDto;
import com.flightagency.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FilterFlightMapper {

    public Flight toFlight(FilterFlightDto filterFlightDto) {
        return new Flight(filterFlightDto.getOriginId(), filterFlightDto.getDestinationId()
                , java.sql.Date.valueOf(String.valueOf(filterFlightDto.getFlyDate())));
    }

    public FilterFlightDto toFilterFlightDto(Flight flight) {
        return new FilterFlightDto(flight.getOriginId(), flight.getDestinationId(), flight.getFlyDate());
    }
}
