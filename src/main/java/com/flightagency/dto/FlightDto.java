package com.flightagency.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class FlightDto {
    private int flightNumber;
    private int originId;
    private int destinationId;
    private Timestamp flyDateTime;
    private float cost;
    private int capacity;
    private int remainingSeats;

    public FlightDto() {
    }

    public FlightDto(int flightNumber, int originId, int destinationId, Timestamp flyDateTime, float cost, int capacity, int remainingSeats) {
        this.flightNumber = flightNumber;
        this.originId = originId;
        this.destinationId = destinationId;
        this.flyDateTime = flyDateTime;
        this.cost = cost;
        this.capacity = capacity;
        this.remainingSeats = remainingSeats;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public Timestamp getFlyDateTime() {
        return flyDateTime;
    }

    public void setFlyDateTime(Timestamp flyDateTime) {
        this.flyDateTime = flyDateTime;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }
}
