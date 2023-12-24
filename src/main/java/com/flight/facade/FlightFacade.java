package com.flight.facade;

import com.flight.dto.AllFlightsResponse;
import com.flight.dto.FlightDto;
import com.flight.exception.FlightNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface FlightFacade {

    String baseUrl = "/flight";

    /**
     * @return List<FlightDto>
     * همه پرواز هارا نشان میدهد
     */
    @GetMapping(value = baseUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    AllFlightsResponse getAll() throws FlightNotFoundException;

    /**
     * @param flightDto اضافه کردن پرواز
     */
    @PostMapping(value = baseUrl , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    int insert(@RequestBody FlightDto flightDto);


    /**
     * @param id ایدی پرواز
     * @return boolean
     * حدف کردن یک پرواز
     */
    @DeleteMapping(value = baseUrl + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    boolean delete(@PathVariable int id);
}
