package com.flight.controller;

import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.FailedToPayException;
import com.flight.exception.EnoughSeatsNotFoundException;
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
    public PaymentResponseDto pay(PaymentDto paymentDto) throws FailedToPayException
            , FlightNotFoundException, ReservationNotFoundException, EnoughSeatsNotFoundException {
        return paymentService.pay(paymentDto);
    }
}
