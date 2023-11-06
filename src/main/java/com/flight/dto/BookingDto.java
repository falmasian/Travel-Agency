package com.flight.dto;

import java.util.ArrayList;

public class BookingDto {
    /**
     * ایدی رزرو کننده
     */
    private String customerId;
    /**
     * شماره پرواز
     */
    private int flightId;
    /**
     * کد ملی های مسافران
     */
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

    @Override
    public String toString() {
        return "BookingDto{" +
               "customerId='" + customerId + '\'' +
               ", flightId=" + flightId +
               ", nationalCodes=" + nationalCodes +
               '}';
    }
}
