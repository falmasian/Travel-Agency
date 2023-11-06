package com.flight.controllerImpl;

import com.flight.controller.PaymentController;
import com.flight.dto.PaymentDto;
import com.flight.service.PaymentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentControllerImpl implements PaymentController {

    private PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public float payment(PaymentDto paymentDto) {
        return paymentService.payment(paymentDto);
    }
}
