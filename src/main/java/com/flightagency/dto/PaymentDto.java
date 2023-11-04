package com.flightagency.dto;

public class PaymentDto {

    private String tracingCode;

    public PaymentDto() {
    }

    public PaymentDto(String tracingCode) {
        this.tracingCode = tracingCode;
    }

    public String getTracingCode() {
        return tracingCode;
    }

    public void setTracingCode(String tracingCode) {
        this.tracingCode = tracingCode;
    }

    @Override
    public String toString() {
        return "PaymentDto{" +
               "tracingCode='" + tracingCode + '\'' +
               '}';
    }
}
