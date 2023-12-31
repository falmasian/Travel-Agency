package com.flight.controller;

import com.flight.aspect.Controller;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.InsufficientSeatsException;
import com.flight.facade.BookingFacade;
import com.flight.dto.*;
import com.flight.service.BookingService;
import com.flight.service.CancellationService;
import com.flight.service.FilterService;
import com.flight.service.ReservationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController implements BookingFacade {

    private final BookingService bookingService;
    private final FilterService filterService;
    private final CancellationService cancellationService;
    private final ReservationService reservationService;

    public BookingController(BookingService bookingService, FilterService filterService
            , CancellationService cancellationService, ReservationService reservationService) {
        this.bookingService = bookingService;
        this.filterService = filterService;
        this.cancellationService = cancellationService;
        this.reservationService = reservationService;
    }

    @Override
    @Controller
    public ReservationResponseDto reserve(BookingDto bookingDto) throws FlightNotFoundException
            , InsufficientSeatsException {
        return bookingService.book(bookingDto);
    }

    @Override
    @Controller
    public FilterResponseDto filter(FilterFlightDto filterFlightDto) throws FlightNotFoundException {
        return filterService.filter(filterFlightDto);
    }

    @Override
    @Controller
    public CancellingResponseDto cancel(CancellationDto cancellationDto) throws FlightNotFoundException
            , ReservationNotFoundException {
        return cancellationService.cancel(cancellationDto);
    }

    @Override
    @Controller
    public CustomerReservationsResponseDto getAllReservations(ReservationDto reservationDto)
            throws ReservationNotFoundException {
        return reservationService.getAllReservations(reservationDto);
    }
}
