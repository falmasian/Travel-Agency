package com.flight.controller;

import com.flight.Mapper.CityMapper;
import com.flight.facade.CityFacade;
import com.flight.dto.CityDto;
import com.flight.service.CityService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController implements CityFacade {

    private CityService cityService;

    public CityController(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
    }

    @Override
    public List<CityDto> getAll() {
        return cityService.getAllCities();
    }

    @Override
    public void insert(CityDto cityDto) {
        cityService.insertCity(cityDto);
    }

    @Override
    public boolean delete(int id) {
        return cityService.deleteCityById(id);
    }
}
