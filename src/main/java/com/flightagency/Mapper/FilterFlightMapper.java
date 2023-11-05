package com.flightagency.Mapper;

import com.flightagency.dto.FilterFlightDto;
import com.flightagency.entity.FlightInfo;
import org.springframework.stereotype.Component;

@Component
public class FilterFlightMapper {

    public FlightInfo toFlight(FilterFlightDto filterFlightDto) {
        return new FlightInfo(filterFlightDto.getOriginId(), filterFlightDto.getDestinationId()
                , java.sql.Date.valueOf(String.valueOf(filterFlightDto.getFlyDate())));
    }

    public FilterFlightDto toFilterFlightDto(FlightInfo flight) {
        return new FilterFlightDto(flight.getOriginId(), flight.getDestinationId(), flight.getFlyDate());
    }
}
