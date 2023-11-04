package com.flightagency.dto;

import java.util.ArrayList;

public class CancellationDto {
    private String customerId;
    private ArrayList<String> nationalCodes;

    public CancellationDto() {
    }

    public CancellationDto(String customerId, ArrayList<String> nationalCodes) {
        this.customerId = customerId;
        this.nationalCodes = nationalCodes;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ArrayList<String> getNationalCodes() {
        return nationalCodes;
    }

    public void setNationalCodes(ArrayList<String> nationalCodes) {
        this.nationalCodes = nationalCodes;
    }

    @Override
    public String toString() {
        return "CancellationDto{" +
               "customerId='" + customerId + '\'' +
               ", nationalCodes=" + nationalCodes +
               '}';
    }
}
