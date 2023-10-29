package com.flightagency.service;

import com.flightagency.Mapper.CityMapper;
import com.flightagency.config.dao.CityDao;
import com.flightagency.dto.CityDto;
import com.flightagency.entity.City;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Component
public class CityService {

    private CityDao cityDao;

    private CityMapper cityMapper;

    public CityService(CityDao cityDao, CityMapper cityMapper) {
        this.cityDao = cityDao;
        this.cityMapper = cityMapper;
    }

    public List<CityDto> getAllCities() {
        return cityDao.getAllCity()
                .stream()
                .map(cityMapper::toCityDto)
                .collect(toList());
    }

    public void insertCity(CityDto cityDto) {
        City city = cityMapper.toCity(cityDto);
        cityDao.insertCity(city);
    }

    public boolean deleteCityById(int id) {
        return cityDao.deleteCityById(id) > 0;
    }
}
