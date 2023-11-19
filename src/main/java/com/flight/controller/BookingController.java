package com.flight.controller;

import com.flight.exception.EmptyFlightException;
import com.flight.exception.EmptyReservationException;
import com.flight.exception.NotEnoughSeatsException;
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
    public ReservationResponseDto reserve(BookingDto bookingDto) throws EmptyFlightException
            , NotEnoughSeatsException {
        return bookingService.book(bookingDto);
    }

    @Override
    public FilterResponseDto filter(FilterFlightDto filterFlightDto) throws EmptyFlightException {
        return filterService.filter(filterFlightDto);
    }

    @Override
    public CancellingResponseDto cancel(CancellationDto cancellationDto) throws EmptyFlightException
            , EmptyReservationException {
        return cancellationService.cancel(cancellationDto);
    }

    @Override
    public CustomerReservationsResponseDto getAllReservations(ReservationDto reservationDto) {
        return reservationService.getAllReservations(reservationDto);
    }
}
