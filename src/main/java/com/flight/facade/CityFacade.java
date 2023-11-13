package com.flight.facade;

import com.flight.dto.CityDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component

public interface CityFacade {

    String baseUrl = "/city/";

    /**
     * @return List<CityDto>
     * همه ی شهر ها را نشان می دهد
     */
    @GetMapping(value = baseUrl + "getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<CityDto> getAll();

    /**
     * @param cityDto اضافه کردن یک شهر
     */
    @PostMapping(value = baseUrl + "insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    void insert(@RequestBody CityDto cityDto);


    /**
     * @param id ایدی شهر
     * @return boolean
     * حدف یک شهر با ایدی ان
     */
    @DeleteMapping(value = baseUrl + "delete/{id}")
    boolean delete(@PathVariable int id);
}
