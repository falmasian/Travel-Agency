package com.flight.mapper;

import com.flight.Mapper.CityMapper;
import com.flight.dto.CityDto;
import com.flight.entity.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.mock;

public class CityMapperTest {
    private CityMapper cityMapper;

    @BeforeEach
    public void setup() {
        this.cityMapper = new CityMapper();
    }


    @Test
    public void toCityDto(){
        City city = mock(City.class);
        CityDto cityDto = cityMapper.toCityDto(city);
        assertThat(cityDto.getCityName()).isSameAs(city.getCityName());
    }

    @Test
    public void toCity(){
        CityDto cityDto = mock(CityDto.class);
        City city = cityMapper.toCity(cityDto);
        assertThat(cityDto.getCityName()).isSameAs(city.getCityName());
    }

}
