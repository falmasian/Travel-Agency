package com.flight.service;

import com.flight.Mapper.CityMapper;
import com.flight.dto.AllCitiesResponse;
import com.flight.dto.CityDto;
import com.flight.entity.City;
import com.flight.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public AllCitiesResponse getAll() {
       List<CityDto> cityDtoList =  cityRepository
               .findAll()
               .stream()
               .map(cityMapper::toCityDto)
               .collect(toList());
       return new AllCitiesResponse(cityDtoList);
    }

    public int insert(CityDto cityDto) {
        City city = cityMapper.toCity(cityDto);
        cityRepository.save(city);
        return city.getCityId();
    }

    public boolean deleteById(int id) {
        try {
            cityRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
