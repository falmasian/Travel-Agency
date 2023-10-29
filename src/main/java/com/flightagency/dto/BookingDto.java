package com.flightagency.dto;

import java.util.ArrayList;

public class BookingDto {
    private String customerId;
    private int flightId;
    private ArrayList<String> nationalCodes = new ArrayList<>();

    public BookingDto() {
    }

    public BookingDto(String customerId, int flightId, ArrayList<String> nationalCodes) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.nationalCodes = nationalCodes;
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

    public ArrayList<String> getNationalCodes() {
        return nationalCodes;
    }

    public void setNationalCodes(ArrayList<String> nationalCodes) {
        this.nationalCodes = nationalCodes;
    }
}