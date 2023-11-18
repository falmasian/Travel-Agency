package com.flight.mapper;

import com.flight.Mapper.ReservationGetMapper;
import com.flight.dto.ReservationGetDto;
import com.flight.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class ReservationGetMapperTest {

    private ReservationGetMapper reservationGetMapper;

    @BeforeEach
    public void setUp(){
        reservationGetMapper = new ReservationGetMapper();
    }

    @Test
    public void toReservation(){
        ReservationGetDto reservationGetDto = mock(ReservationGetDto.class);
        Reservation reservation = reservationGetMapper.toReservation(reservationGetDto);
        assertThat(reservation.getCustomerId()).isSameAs(reservationGetDto.getCustomerId());
        assertThat(reservation.getFlightId()).isSameAs(reservationGetDto.getFlightId());
        assertThat(reservation.getTrackingCode()).isSameAs(reservationGetDto.getTrackingCode());
        assertThat(reservation.getPassengerNationalCode()).isSameAs(reservationGetDto.getNationalCode());
    }

    @Test
    public void toReservationGetDto(){
        Reservation reservation = mock(Reservation.class);
        ReservationGetDto reservationGetDto = reservationGetMapper.toReservationGetDto(reservation);
        assertThat(reservation.getCustomerId()).isSameAs(reservationGetDto.getCustomerId());
        assertThat(reservation.getFlightId()).isSameAs(reservationGetDto.getFlightId());
        assertThat(reservation.getTrackingCode()).isSameAs(reservationGetDto.getTrackingCode());
        assertThat(reservation.getPassengerNationalCode()).isSameAs(reservationGetDto.getNationalCode());
    }
}
