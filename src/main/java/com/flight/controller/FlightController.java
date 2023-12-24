package com.flight.controller;

import com.flight.aspect.Controller;
import com.flight.dto.AllFlightsResponse;
import com.flight.dto.FlightDto;
import com.flight.exception.FlightNotFoundException;
import com.flight.facade.FlightFacade;
import com.flight.service.FlightService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController implements FlightFacade {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    @Controller
    public AllFlightsResponse getAll() throws FlightNotFoundException {
        return flightService.getAll();
    }

    @Override
    @Controller
    public int insert(FlightDto flightDto) {
        return flightService.insert(flightDto);
    }

    @Override
    @Controller
    public boolean delete(int id) {
        return flightService.deleteById(id);
    }
}
