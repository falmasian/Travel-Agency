package com.flightagency.controller;

import com.flightagency.dto.FlightDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface FlightController {

    @GetMapping(value = "/api/flight", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FlightDto> getAllFlights();

    @PostMapping(value = "/api/flight", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    void insertFlight(@RequestBody FlightDto flightDto);

    @DeleteMapping(value = "/api/flight/{id}", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    boolean deleteFlight(@PathVariable int id);
}
