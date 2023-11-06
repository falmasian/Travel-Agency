package com.flight.Mapper;

import com.flight.dto.ReservationGetDto;
import com.flight.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationGetMapper {
    public Reservation toReservation(ReservationGetDto reservationGetDto) {
        Reservation reservation = new Reservation(reservationGetDto.getCustomerId(), reservationGetDto.getFlightId()
                , reservationGetDto.getNationalCode(), reservationGetDto.getTrackingCode());
        return reservation;
    }

    public ReservationGetDto toReservationGetDto(Reservation reservation) {
        return new ReservationGetDto(reservation.getCustomerId(), reservation.getFlightId()
                , reservation.getTrackingCode(), reservation.getNationalCode());
    }
}
