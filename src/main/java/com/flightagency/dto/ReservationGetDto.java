package com.flightagency.dto;

public class ReservationGetDto {
    private String customerId;
    private int flightId;
    private String trackingCode;
    private String nationalCode;

    public ReservationGetDto() {
    }

    public ReservationGetDto(String customerId, int flightId, String trackingCode, String nationalCode) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.trackingCode = trackingCode;
        this.nationalCode = nationalCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}