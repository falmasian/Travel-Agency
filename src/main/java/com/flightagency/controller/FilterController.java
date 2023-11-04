package com.flightagency.controller;

import com.flightagency.dto.FilterFlightDto;
import com.flightagency.dto.FlightDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface FilterController {

    @PostMapping(value = "/api/filterFlight", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    List<FlightDto> filter(@RequestBody FilterFlightDto filterFlightDto);
}
