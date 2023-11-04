package com.flightagency.controllerImpl;

import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.controller.FilterController;
import com.flightagency.dto.FilterFlightDto;
import com.flightagency.dto.FlightDto;
import com.flightagency.service.FilterService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterControllerImpl implements FilterController {

    private FilterService filterService;

    public FilterControllerImpl(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public List<FlightDto> filter(FilterFlightDto filterFlightDto){
        return filterService.filter(filterFlightDto);
    }
}
