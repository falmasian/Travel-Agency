package com.flight.controller;

import com.flight.facade.BookingFacade;
import com.flight.dto.*;
import com.flight.service.BookingService;
import com.flight.service.CancellationService;
import com.flight.service.FilterService;
import com.flight.service.ReservationService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController implements BookingFacade {

    private BookingService bookingService;
    private FilterService filterService;
    private CancellationService cancellationService;
    private ReservationService reservationService;

    public BookingController(BookingService bookingService, FilterService filterService, CancellationService cancellationService, ReservationService reservationService) {
        this.bookingService = bookingService;
        this.filterService = filterService;
        this.cancellationService = cancellationService;
        this.reservationService = reservationService;
    }

    @Override
    public String reserve(BookingDto bookingDto) {
        return bookingService.book(bookingDto);
    }

    @Override
    public List<FlightDto> filter(FilterFlightDto filterFlightDto) {
        return filterService.filter(filterFlightDto);
    }

    @Override
    public float cancel(CancellationDto cancellationDto) {
        return cancellationService.cancelling(cancellationDto);
    }

    @Override
    public List<ReservationGetDto> getAllReservations(ReservationDto reservationDto) {
        return reservationService.getAllReservations(reservationDto);
    }
}
