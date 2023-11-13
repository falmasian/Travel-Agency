package com.flight.controller;

import com.flight.dto.PaymentDto;
import com.flight.facade.PaymentFacade;
import com.flight.service.PaymentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController implements PaymentFacade {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public float payment(PaymentDto paymentDto) {
        return paymentService.payment(paymentDto);
    }
}
