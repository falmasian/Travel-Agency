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
    private int id;

    @Column(name = "cityName")
    private String cityName;

    public City(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}