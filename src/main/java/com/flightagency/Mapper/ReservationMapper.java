package com.flightagency.Mapper;

import com.flightagency.dto.ReservationDto;
import com.flightagency.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public Reservation toReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(reservationDto.getNationalCode());
        return reservation;
    }

    public ReservationDto toReservationDto(Reservation reservation) {
        return new ReservationDto(reservation.getCustomerId());
    }
}
