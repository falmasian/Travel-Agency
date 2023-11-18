package com.flight.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "Reserve")
@NoArgsConstructor
public class Reservation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerId;
    private int flightId;
    private String trackingCode;
    private String passengerNationalCode;

    @Transient
    private ArrayList<String> nationalCodes = new ArrayList<>();
    @Transient
    private int numberOfTickets;
    @Transient
    private boolean completed;

    public Reservation(int id, String customerId, int flightId, String passengerNationalCode, String trackingCode) {
        this.id = id;
        this.customerId = customerId;
        this.flightId = flightId;
        this.trackingCode = trackingCode;
        this.passengerNationalCode = passengerNationalCode;
    }

    public Reservation(String customerId, int flightId, String passengerNationalCode, String trackingCode) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.trackingCode = trackingCode;
        this.passengerNationalCode = passengerNationalCode;
    }

    public Reservation(String customerId, int flightId, int numberOfTickets) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.numberOfTickets = numberOfTickets;
        this.trackingCode =  UUID.randomUUID().toString();
        completed = false;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumberOfTickets() {
        return numberOfTickets;
    }



    public String getPassengerNationalCode() {
        return passengerNationalCode;
    }

    public void setPassengerNationalCode(String passengerNationalCode) {
        this.passengerNationalCode = passengerNationalCode;
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


    public void setNumberOfTickets(int num) {
        numberOfTickets = num;
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

//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + Integer.parseInt(customerId.substring(2, 7));
//        result = 31 * result + flightId;
//        for (String str : nationalCodes) {
//            String sub = str.substring(4, 6);
//            int num = Integer.parseInt(sub) * 3 + 13;
//            result = result + num;
//        }
//        return result;
//    }
}
