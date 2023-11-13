package com.flight.controller;

import com.flight.facade.FlightFacade;
import com.flight.dto.FlightDto;
import com.flight.service.FlightService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController implements FlightFacade {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public List<FlightDto> getAll() {
        return flightService.getAllFlights();
    }

    @Override
    public void insert(FlightDto flightDto) {
        flightService.insertFlight(flightDto);
    }

    @Override
    public boolean delete(int id) {
        return flightService.deleteFlight(id);
    }
}
