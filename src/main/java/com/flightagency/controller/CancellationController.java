package com.flightagency.controller;

import com.flightagency.dto.CancellationDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface CancellationController {
    @PostMapping(value = "/api/cancellation", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    float cancelling(@RequestBody CancellationDto cancellationDto);
}
