package com.flightagency.entity;

import java.util.ArrayList;

public class Reservation {

    private int id;
    private String customerId;
    private int flightId;
    private String trackingCode;
    private ArrayList<String> nationalCodes = new ArrayList<>();
    private String nationalCode;
    private int numberOfTickets;
    private boolean completed;

    public Reservation() {
    }

    public Reservation(String customerId, int flightId, int numberOfTickets) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.numberOfTickets = numberOfTickets;
        this.trackingCode = String.valueOf(hashCode());
        completed = false;
    }

    public Reservation(String customerId, int flightId, String nationalCode, String trackingCode) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.nationalCode = nationalCode;
        this.trackingCode = trackingCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public ArrayList<String> getNationalCodes() {
        return nationalCodes;
    }

    public void setNationalCodes(ArrayList<String> stringArrayList) {
        nationalCodes = stringArrayList;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setFlightId(int id) {
        flightId = id;
    }

    public void setCustomerId(String id) {
        customerId = id;
    }

    public void setNumberOfTickets(int num) {
        numberOfTickets = num;
    }

    public void setTrackingCode(String code) {
        trackingCode = code;
    }

    public void setCompleted(boolean bool) {
        completed = bool;
    }

    public boolean addNationalCode(String code) {
        if (!nationalCodes.contains(code)) {
            nationalCodes.add(code);
            return true;
        }
        return false;
    }

    public String getFromNationalCodesByIndex(int index) {
        return nationalCodes.get(index);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Integer.parseInt(customerId.substring(2, 7));
        result = 31 * result + flightId;
        for (String str : nationalCodes) {
            String sub = str.substring(4, 6);
            int num = Integer.parseInt(sub) * 3 + 13;
            result = result + num;
        }
        return result;
    }
}