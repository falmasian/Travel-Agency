package com.flightagency.repository;

import com.flightagency.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CityRepository extends JpaRepository<City, Integer> {
}




















