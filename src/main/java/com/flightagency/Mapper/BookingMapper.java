package com.flightagency.Mapper;

import com.flightagency.dto.BookingDto;
import com.flightagency.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public Reservation toReservation(BookingDto bookingDto) {
        int numberOfTickets = bookingDto.getNationalCodes().size();
        Reservation reservation = new Reservation(bookingDto.getCustomerId(),
                bookingDto.getFlightId(), numberOfTickets);
        reservation.setNationalCodes(bookingDto.getNationalCodes());
        return reservation;
    }

    public BookingDto toBookingDto(Reservation reservation) {
        return new BookingDto(reservation.getCustomerId(), reservation.getFlightId(),
                reservation.getNationalCodes());
    }
}
