package com.flight.Mapper;

import com.flight.dto.FilterFlightDto;
import com.flight.entity.FlightInfo;
import org.springframework.stereotype.Component;

@Component
public class FilterFlightMapper {

    public FlightInfo toFlight(FilterFlightDto filterFlightDto) {
        return new FlightInfo(filterFlightDto.getOriginId(), filterFlightDto.getDestinationId()
                , java.sql.Date.valueOf(String.valueOf(filterFlightDto.getFlyDate())));
    }

    public FilterFlightDto toFilterFlightDto(FlightInfo flight) {
        return new FilterFlightDto(flight.getOriginCity().getCityId()
                , flight.getDestinationCity().getCityId(), flight.getFlyDate());
    }
}
