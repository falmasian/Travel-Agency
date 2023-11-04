package com.flightagency.controllerImpl;

import com.flightagency.controller.PaymentController;
import com.flightagency.dto.PaymentDto;
import com.flightagency.service.PaymentService;
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
