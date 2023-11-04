package com.flightagency.controllerImpl;

import com.flightagency.Mapper.CityMapper;
import com.flightagency.controller.CityController;
import com.flightagency.dto.CityDto;
import com.flightagency.service.CityService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityControllerImpl implements CityController {

    private CityService cityService;

    public CityControllerImpl(CityService cityService, CityMapper cityMapper) {
        this.cityService = cityService;
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityService.getAllCities();
    }

    @Override
    public void insertCity(CityDto cityDto) {
        cityService.insertCity(cityDto);
    }

    @Override
    public boolean deleteCity(int id) {
        return cityService.deleteCityById(id);
    }
}
