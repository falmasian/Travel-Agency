package com.flightagency.controller;

import com.flightagency.dto.FlightDto;
import com.flightagency.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/api/flight")
    @ResponseBody
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping("/api/flight")
    public void insertFlight(@RequestBody FlightDto flightDto) {
        flightService.insertFlight(flightDto);
    }

    @DeleteMapping("/api/flight/{id}")
    public boolean deleteFlight(@PathVariable int id) {
        return flightService.deleteFlight(id);
    }
}
