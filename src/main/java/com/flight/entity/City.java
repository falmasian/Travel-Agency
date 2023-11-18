package com.flight.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "City")
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;

    @Column(name = "cityName")
    private String cityName;

    public City(int id, String cityName) {
        this.cityId = id;
        this.cityName = cityName;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}