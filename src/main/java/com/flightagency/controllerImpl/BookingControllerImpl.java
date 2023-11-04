package com.flightagency.controllerImpl;

import com.flightagency.controller.BookingController;
import com.flightagency.dto.BookingDto;
import com.flightagency.service.BookingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingControllerImpl implements BookingController {

    private BookingService bookingService;

    public BookingControllerImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public String booking(BookingDto bookingDto) {
        return bookingService.booking(bookingDto);
    }
}
