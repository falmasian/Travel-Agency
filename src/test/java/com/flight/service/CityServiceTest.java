package com.flight.service;

import com.flight.Mapper.CityMapper;
import com.flight.dto.CityDto;
import com.flight.entity.City;
import com.flight.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CityServiceTest {

    private  CityRepository cityRepository;
    private  CityMapper cityMapper;
    private CityService cityService;

    @BeforeEach
    public void setup() {
        this.cityRepository = mock(CityRepository.class);
        this.cityMapper =  mock(CityMapper.class);
        this.cityService = new CityService(cityRepository, cityMapper);
    }

    @Test
    public void getAllCities() {
        List<City> cities = new ArrayList<>();
        cities.add(new City(1,"name1"));
        cities.add(new City(2,"name2"));
        cities.add(new City(3,"name3"));
        when(cityRepository.findAll()).thenReturn(cities);
    }

}
