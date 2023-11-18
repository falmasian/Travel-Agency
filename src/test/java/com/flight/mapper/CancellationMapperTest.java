package com.flight.mapper;

import com.flight.Mapper.CancellationMapper;
import com.flight.dto.CancellationDto;
import com.flight.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class CancellationMapperTest {

    private CancellationMapper cancellationMapper;

    @BeforeEach
    public void setUp(){
        cancellationMapper = new CancellationMapper();
    }

    @Test
    public void toReservation(){
        CancellationDto cancellationDto = mock(CancellationDto.class);
        Reservation reservation = cancellationMapper.toReservation(cancellationDto);
        assertThat(reservation.getCustomerId()).isSameAs(cancellationDto.getCustomerId());
    }

    @Test
    public void toBookingDto(){
        Reservation reservation = mock(Reservation.class);
        CancellationDto cancellationDto = cancellationMapper.toCancellationDto(reservation);
        assertThat(reservation.getCustomerId()).isSameAs(cancellationDto.getCustomerId());
    }

}
