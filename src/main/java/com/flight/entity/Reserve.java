package com.flight.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Reserve")
@NoArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customerId;
    private int flightId;
    private String trackingCode;
    private String passengerNationalCode;

    public Reserve(int id, String customerId, int flightId, String passengerNationalCode, String trackingCode) {
        this.id = id;
        this.customerId = customerId;
        this.flightId = flightId;
        this.trackingCode = trackingCode;
        this.passengerNationalCode = passengerNationalCode;
    }

    public Reserve(String customerId, int flightId, String passengerNationalCode, String trackingCode) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.trackingCode = trackingCode;
        this.passengerNationalCode = passengerNationalCode;
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

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getPassengerNationalCode() {
        return passengerNationalCode;
    }

    public void setPassengerNationalCode(String passengerNationalCode) {
        this.passengerNationalCode = passengerNationalCode;
    }
}
