package com.flightagency.controller;

import com.flightagency.dto.BookingDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface BookingController {

    @PostMapping(value = "/api/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
    String booking(@RequestBody BookingDto bookingDto);
}
