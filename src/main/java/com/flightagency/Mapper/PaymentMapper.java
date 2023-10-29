package com.flightagency.Mapper;

import com.flightagency.dto.PaymentDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public String toTrackingCode(PaymentDto paymentDto) {
        return paymentDto.getTracingCode();
    }

    public PaymentDto toPaymentDto(String trackingCode) {
        return new PaymentDto(trackingCode);
    }
}
