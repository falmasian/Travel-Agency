package com.flightagency.controller;

import com.flightagency.dto.BookingDto;
import com.flightagency.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/api/booking")
    public boolean booking(@RequestBody BookingDto bookingDto) {
        return bookingService.booking(bookingDto);
    }
}
