package com.flightagency.controller;

import com.flightagency.dto.CityDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface CityController {

    @GetMapping(value = "/api/city", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<CityDto> getAllCities();

    @PostMapping(value = "/api/city", consumes = MediaType.APPLICATION_JSON_VALUE)
    void insertCity(@RequestBody CityDto cityDto);

    @DeleteMapping(value = "/api/city/{id}")
    boolean deleteCity(@PathVariable int id);
}
