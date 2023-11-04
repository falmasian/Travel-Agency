package com.flightagency.controllerImpl;

import com.flightagency.controller.FlightController;
import com.flightagency.dto.FlightDto;
import com.flightagency.service.FlightService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightControllerImpl implements FlightController {

    private FlightService flightService;

    public FlightControllerImpl(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @Override
    public void insertFlight(FlightDto flightDto) {
        flightService.insertFlight(flightDto);
    }

    @Override
    public boolean deleteFlight(int id) {
        return flightService.deleteFlight(id);
    }
}
