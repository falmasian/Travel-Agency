package com.flightagency.controller;

import com.flightagency.dto.FilterFlightDto;
import com.flightagency.service.FilterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {

    private FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping("/api/filterFlight")
    public List<FilterFlightDto> filter(@RequestBody FilterFlightDto filterFlightDto){
        return filterService.filter(filterFlightDto);
    }
}
