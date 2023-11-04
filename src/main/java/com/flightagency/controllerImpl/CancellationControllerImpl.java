package com.flightagency.controllerImpl;

import com.flightagency.controller.CancellationController;
import com.flightagency.dto.CancellationDto;
import com.flightagency.service.CancellationService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancellationControllerImpl implements CancellationController {

    private CancellationService cancellationService;

    public CancellationControllerImpl(CancellationService cancellationService) {
        this.cancellationService = cancellationService;
    }

    @Override
    public float cancelling(CancellationDto cancellationDto) {
        return cancellationService.cancelling(cancellationDto);
    }
}
