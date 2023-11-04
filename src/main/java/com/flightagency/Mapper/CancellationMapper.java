package com.flightagency.Mapper;

import com.flightagency.dto.CancellationDto;
import com.flightagency.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class CancellationMapper {
    public Reservation toReservation(CancellationDto cancellationDto) {
        Reservation reservation = new Reservation();
        reservation.setCustomerId(cancellationDto.getCustomerId());
        reservation.setNationalCodes(cancellationDto.getNationalCodes());
        return reservation;
    }

    public CancellationDto toCancellationDto(Reservation reservation) {
        return new CancellationDto(reservation.getCustomerId(), reservation.getNationalCodes());
    }
}
