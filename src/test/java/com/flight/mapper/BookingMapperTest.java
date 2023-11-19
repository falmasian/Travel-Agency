package com.flight.mapper;

import com.flight.Mapper.BookingMapper;
import com.flight.dto.BookingDto;
import com.flight.entity.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class BookingMapperTest {

    private BookingMapper bookingMapper;

    @BeforeEach
    public void setup() {
        this.bookingMapper = new BookingMapper();
    }

    @Test
    public void toReservation() {
        BookingDto bookingDto = mock(BookingDto.class);
        Reservation reservation = bookingMapper.toReservation(bookingDto);
        assertThat(reservation.getCustomerId()).isSameAs(bookingDto.getCustomerId());
        assertThat(reservation.getFlightId()).isSameAs(bookingDto.getFlightId());
    }

    @Test
    public void toBookingDto() {
        Reservation reservation = mock(Reservation.class);
        BookingDto bookingDto = bookingMapper.toBookingDto(reservation);
        assertThat(reservation.getCustomerId()).isSameAs(bookingDto.getCustomerId());
        assertThat(reservation.getFlightId()).isSameAs(bookingDto.getFlightId());
    }


}
