package com.flight.facade;

import com.flight.dto.AllCitiesResponse;
import com.flight.dto.CityDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CityFacade {

    String baseUrl = "/city";

    /**
     * @return List<CityDto>
     * همه ی شهر ها را نشان می دهد
     */
    @GetMapping(value = baseUrl, produces = MediaType.APPLICATION_JSON_VALUE)
    AllCitiesResponse getAll();

    /**
     * @param cityDto اضافه کردن یک شهر
     */
    @PostMapping(value = baseUrl , consumes = MediaType.APPLICATION_JSON_VALUE)
    int insert(@RequestBody CityDto cityDto);


    /**
     * @param id ایدی شهر
     * @return boolean
     * حدف یک شهر با ایدی ان
     */
    @DeleteMapping(value = baseUrl + "/{id}")
    boolean delete(@PathVariable int id);
}
