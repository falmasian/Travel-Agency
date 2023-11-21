package com.flight.Mapper;

import com.flight.dto.CancellationDto;
import com.flight.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class CancellationMapper {
    public Reservation toReservation(CancellationDto cancellationDto) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(cancellationDto.getCustomerId());
        reservation.setNationalCodes(cancellationDto.getNationalCodes());
        reservation.setFlightId(cancellationDto.getFlightId());
        return reservation;
    }

    public CancellationDto toCancellationDto(Reservation reservation) {
        return new CancellationDto(reservation.getCustomerId(), reservation.getFlightId(), reservation.getNationalCodes());
    }
}
