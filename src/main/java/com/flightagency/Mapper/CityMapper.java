package com.flightagency.Mapper;

import com.flightagency.dto.CityDto;
import com.flightagency.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDto toCityDto(City city) {
        String name = city.getCityName();
        return new CityDto(name);
    }

    public City toCity(CityDto cityDto) {
        String name = cityDto.getCityName();
        return new City(name);
    }
}
