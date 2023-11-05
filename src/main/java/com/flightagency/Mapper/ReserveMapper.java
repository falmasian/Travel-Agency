package com.flightagency.Mapper;

import com.flightagency.entity.Reservation;
import com.flightagency.entity.Reserve;
import org.springframework.stereotype.Component;

@Component
public class ReserveMapper {
    public Reserve toReserve(Reservation reservation, int index) {
        return new Reserve(reservation.getCustomerId(), reservation.getFlightId(), reservation.getFromNationalCodesByIndex(index), reservation.getTrackingCode());
    }

    public Reservation toReservation(Reserve reserve) {
        return new Reservation(reserve.getCustomerId(), reserve.getFlightId(), reserve.getPassengerNationalCode(), reserve.getTrackingCode());
    }
}
