package com.flight.mapper;

import com.flight.Mapper.FlightMapper;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FlightMapperTest {

    private FlightMapper flightMapper;

    @BeforeEach
    public void setup() {
        this.flightMapper = new FlightMapper();
    }

    @Test
    public void toFlightDto() {
        FlightInfo flight = mock(FlightInfo.class);
        FlightDto flightDto = flightMapper.toFlightDto(flight);
        assertThat(flight.getFlightNumber()).isSameAs(flightDto.getFlightNumber());
        assertThat(flight.getOriginId()).isSameAs(flightDto.getOriginId());
        assertThat(flight.getDestinationId()).isSameAs(flightDto.getDestinationId());
        assertThat(flight.getFlyDateTime()).isSameAs(flightDto.getFlyDateTime());
        assertThat(flight.getCost()).isEqualTo(flightDto.getCost());
        assertThat(flight.getCapacity()).isSameAs(flightDto.getCapacity());
        assertThat(flight.getRemainingSeats()).isSameAs(flightDto.getRemainingSeats());
    }

    @Test
    public void toFlightInfo() {
        FlightDto flightDto = mock(FlightDto.class);
        FlightInfo flight = flightMapper.toFlight(flightDto);
        assertThat(flight.getFlightNumber()).isSameAs(flightDto.getFlightNumber());
        assertThat(flight.getOriginId()).isSameAs(flightDto.getOriginId());
        assertThat(flight.getDestinationId()).isSameAs(flightDto.getDestinationId());
        assertThat(flight.getFlyDateTime()).isSameAs(flightDto.getFlyDateTime());
        assertThat(flight.getCost()).isEqualTo(flightDto.getCost());
        assertThat(flight.getCapacity()).isSameAs(flightDto.getCapacity());
        assertThat(flight.getRemainingSeats()).isSameAs(flightDto.getRemainingSeats());
    }

}
