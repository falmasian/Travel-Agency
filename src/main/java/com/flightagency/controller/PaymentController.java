package com.flightagency.controller;

import com.flightagency.dto.PaymentDto;
import com.flightagency.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payment")
    public float payment(@RequestBody PaymentDto paymentDto){
        return paymentService.payment(paymentDto);
    }
}
