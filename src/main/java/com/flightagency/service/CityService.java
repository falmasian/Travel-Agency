package com.flightagency.service;

import com.flightagency.Mapper.CityMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dto.CityDto;
import com.flightagency.entity.City;
import com.flightagency.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CityService {

    private final CityRepository cityRepository;

    private CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @ServiceAnnotation
    public List<CityDto> getAllCities() {
        return cityRepository.findAll().stream().map(cityMapper::toCityDto).collect(toList());
    }

    @ServiceAnnotation
    public void insertCity(CityDto cityDto) {
        City city = cityMapper.toCity(cityDto);
        cityRepository.save(city);
    }

    @ServiceAnnotation
    public boolean deleteCityById(int id) {
        try {
            cityRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
