package com.flightagency.dto;

import java.util.ArrayList;

public class CancellationDto {
    private String trackingCode;
    private ArrayList<String> nationalCodes;

    public CancellationDto() {
    }

    public CancellationDto(String trackingCode, ArrayList<String> nationalCodes) {
        this.trackingCode = trackingCode;
        this.nationalCodes = nationalCodes;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public ArrayList<String> getNationalCodes() {
        return nationalCodes;
    }

    public void setNationalCodes(ArrayList<String> nationalCodes) {
        this.nationalCodes = nationalCodes;
    }
}
