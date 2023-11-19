package com.flight.controller;

import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.exception.EmptyFlightException;
import com.flight.exception.EmptyReservationException;
import com.flight.exception.FailedToPayException;
import com.flight.exception.NotEnoughSeatsException;
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
            , EmptyFlightException, EmptyReservationException, NotEnoughSeatsException {
        return paymentService.pay(paymentDto);
    }
}
