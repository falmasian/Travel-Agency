package com.flight.facade;

import com.flight.dto.FlightDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface FlightFacade {

    String baseUrl = "/api/flight/";

    /**
     * @return List<FlightDto>
     * همه پرواز هارا نشان میدهد
     */
    @GetMapping(value = baseUrl + "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<FlightDto> getAll();

    /**
     * @param flightDto اضافه کردن پرواز
     */
    @PostMapping(value = baseUrl + "insert", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    void insert(@RequestBody FlightDto flightDto);


    /**
     * @param id
     * @return boolean
     * حدف کردن یک پرواز
     */
    @DeleteMapping(value = baseUrl + "delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    boolean delete(@PathVariable int id);
}
