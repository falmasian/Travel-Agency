package com.flightagency.dto;

public class CityDto {

    String cityName;

    public CityDto() {
    }

    public CityDto(String cityName) {
        this.cityName = cityName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
