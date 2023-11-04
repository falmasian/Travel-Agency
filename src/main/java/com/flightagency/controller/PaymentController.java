package com.flightagency.controller;

import com.flightagency.dto.PaymentDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface PaymentController {
    @PostMapping(value =  "/api/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
     float payment(@RequestBody PaymentDto paymentDto);
}
