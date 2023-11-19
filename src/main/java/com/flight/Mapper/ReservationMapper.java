package com.flight.Mapper;

import com.flight.dto.ReservationDto;
import com.flight.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationDto.getNationalCode());
        reservation.setFlightId(reservationDto.getFlightId());
        return reservation;
    }

    public ReservationDto toReservationDto(Reservation reserve) {
        return new ReservationDto(reserve.getCustomerId(), reserve.getFlightId());
    }
}
