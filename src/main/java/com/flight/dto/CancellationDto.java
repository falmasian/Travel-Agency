package com.flight.dto;

import java.util.ArrayList;

public class CancellationDto {

    /**
     * ایدی مشتری
     */
    private String customerId;
    /**
     * کد ملی های کنسلی مسافران
     */
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
        return "CancellationDto{" + "\n" +
               "    customerId=" + customerId + ",\n" +
               "    nationalCodes=" + nationalCodes + "\n" +
               '}' + "\n";
    }
}
