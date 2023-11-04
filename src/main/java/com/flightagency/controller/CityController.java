package com.flightagency.controller;

import com.flightagency.dto.CityDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public interface CityController {

    /**
     * @return List<CityDto>
     *     همه ی شهر ها را نشان می دهد
     */
    @GetMapping(value = "/api/city/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<CityDto> getAllCities();

    /**
     * @param cityDto
     * اضافه کردن یک شهر
     */
    @PostMapping(value = "/api/city/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    void insertCity(@RequestBody CityDto cityDto);


    /**
     * @param id    ایدی شهر
     * @return boolean
     * حدف یک شهر با ایدی ان
     */
    @DeleteMapping(value = "/api/city/delete/{id}")
    boolean deleteCity(@PathVariable int id);
}
