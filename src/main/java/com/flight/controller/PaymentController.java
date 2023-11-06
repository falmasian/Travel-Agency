package com.flight.controller;

import com.flight.dto.PaymentDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface PaymentController {
    /**
     * @param paymentDto
     * @return cost
     * عملیات پرداخت را انجام میدهد
     * هزینه را نشان میدهد
     */
    @PostMapping(value = "/api/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    float payment(@RequestBody PaymentDto paymentDto);
}
