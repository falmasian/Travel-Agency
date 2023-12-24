package com.flight.controller;

import com.flight.aspect.Controller;
import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.PaymentProblemException;
import com.flight.exception.InsufficientSeatsException;
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
    @Controller
    public PaymentResponseDto pay(PaymentDto paymentDto) throws PaymentProblemException
            , FlightNotFoundException, ReservationNotFoundException, InsufficientSeatsException {
        return paymentService.pay(paymentDto);
    }
}
