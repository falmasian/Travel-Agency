package com.flight.facade;

import com.flight.dto.PaymentDto;
import com.flight.dto.PaymentResponseDto;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.ReservationNotFoundException;
import com.flight.exception.PaymentProblemException;
import com.flight.exception.InsufficientSeatsException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface PaymentFacade {

    String url = "/payment";
    /**
     * @param paymentDto مشخصات ورودی
     * @return cost
     * عملیات پرداخت را انجام میدهد
     * هزینه را نشان میدهد
     */
    @PostMapping(value = url, consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentResponseDto pay(@RequestBody PaymentDto paymentDto)
            throws PaymentProblemException, FlightNotFoundException
            , ReservationNotFoundException, InsufficientSeatsException;
}
