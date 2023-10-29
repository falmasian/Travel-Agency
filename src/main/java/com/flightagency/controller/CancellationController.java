package com.flightagency.controller;

import com.flightagency.dto.CancellationDto;
import com.flightagency.service.CancellationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancellationController {

    private CancellationService cancellationService;

    public CancellationController(CancellationService cancellationService) {
        this.cancellationService = cancellationService;
    }

    @PostMapping("/api/cancellation")
    public boolean cancelling(@RequestBody CancellationDto cancellationDto){
        return cancellationService.cancelling(cancellationDto);
    }
}
