package com.flightagency.controller;

import com.flightagency.dto.FlightDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface FlightController {

    /**
     * @return List<FlightDto>
     *     همه پرواز هارا نشان میدهد
     */
    @GetMapping(value = "/api/flight/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FlightDto> getAllFlights();

    /**
     * @param flightDto
     * اضافه کردن پرواز
     */
    @PostMapping(value = "/api/flight/insert", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    void insertFlight(@RequestBody FlightDto flightDto);


    /**
     * @param id
     * @return boolean
     * حدف کردن یک پرواز
     */
    @DeleteMapping(value = "/api/flight/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    boolean deleteFlight(@PathVariable int id);
}
