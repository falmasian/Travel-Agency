package com.flight.controller;

import com.flight.dto.AllCitiesResponse;
import com.flight.dto.CityDto;
import com.flight.facade.CityFacade;
import com.flight.service.CityService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController implements CityFacade {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public AllCitiesResponse getAll() {
        return cityService.getAllCities();
    }

    @Override
    public int insert(CityDto cityDto) {
        return cityService.insertCity(cityDto);
    }

    @Override
    public boolean delete(int id) {
        return cityService.deleteCityById(id);
    }
}
