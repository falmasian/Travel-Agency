package com.flight.mapper;

import com.flight.Mapper.FilterFlightMapper;
import com.flight.dto.FilterFlightDto;
import com.flight.entity.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class FilterFlightMapperTest {

    private FilterFlightMapper filterFlightMapper;

    @BeforeEach
    public void SetUp(){
        filterFlightMapper = new FilterFlightMapper();
    }

//    @Test
//    public void toFlight(){
//        FilterFlightDto filterFlightDto = mock(FilterFlightDto.class);
//        filterFlightDto.setFlyDate(2023/04/05);
//        FlightInfo flightInfo =  filterFlightMapper.toFlight(filterFlightDto);
//        assertThat(filterFlightDto.getDestinationId()).isSameAs(flightInfo.getDestinationId());
//        assertThat(filterFlightDto.getOriginId()).isSameAs(flightInfo.getOriginId());
//
//
//    }

    @Test
    public void toFilterFlightDto(){
        FlightInfo flightInfo = mock(FlightInfo.class);
        FilterFlightDto filterFlightDto = filterFlightMapper.toFilterFlightDto(flightInfo);
        assertThat(filterFlightDto.getDestinationId()).isSameAs(flightInfo.getDestinationCity().getCityId());
        assertThat(filterFlightDto.getOriginId()).isSameAs(flightInfo.getOriginCity().getCityId());

    }
}
