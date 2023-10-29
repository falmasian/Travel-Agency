package com.flightagency.controller;

import com.flightagency.Mapper.CityMapper;
import com.flightagency.dto.CityDto;
import com.flightagency.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
    }

    @GetMapping("/api/city")
    @ResponseBody
    public List<CityDto> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping("/api/city")
    public void insertCity(@RequestBody  CityDto cityDto) {
        cityService.insertCity(cityDto);
    }

    @DeleteMapping("/api/city/{id}")
    public boolean deleteCity(@PathVariable int id) {
        return cityService.deleteCityById(id);
    }
}
