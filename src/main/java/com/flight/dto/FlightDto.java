package com.flight.dto;

import java.sql.Timestamp;

public class FlightDto {
    /**
     * شماره پرواز
     */
    private int flightNumber;
    /**
     * ایدی شهر مبدا
     */
    private int originId;
    /**
     * ایدی شهر مقصد
     */
    private int destinationId;
    /**
     * تاریخ و ساعت پرواز
     */
    private Timestamp flyDateTime;
    /**
     * هزیه بلیط
     */
    private float cost;
    /**
     * ظرفیت ئرواز
     */
    private int capacity;
    /**
     * صندلی های فروش نرفته
     */
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

    @Override
    public String toString() {
        return "FlightDto{" + "\n" +
               "    flightNumber=" + flightNumber + ",\n" +
               "    originId=" + originId + ",\n" +
               "    destinationId=" + destinationId + ",\n" +
               "    flyDateTime=" + flyDateTime + ",\n" +
               "    cost=" + cost + ",\n" +
               "    capacity=" + capacity + ",\n" +
               "    remainingSeats=" + remainingSeats + "\n" +
               '}' + "\n";
    }
}
