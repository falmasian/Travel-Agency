package com.flight.controllerImpl;

import com.flight.controller.FlightController;
import com.flight.dto.FlightDto;
import com.flight.service.FlightService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightControllerImpl implements FlightController {

    private FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
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
