package com.flight.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "FlightInfo")
@NoArgsConstructor
public class FlightInfo {

    @Id
    private int id;

    private int flightNumber;

    private int originId;

    private int destinationId;

    private Timestamp flyDateTime;

//    @ManyToOne
//    private City originCity;
//
//    @ManyToOne
//    private City destinationCity;

    @Transient
    private Date flyDate;

    private float cost;

    private int capacity;

    private int remainingSeats;

    @Transient
    private int completedReserves;

    @Transient
    private int temporaryReserves;

    public FlightInfo(int originId, int destinationId, Date flyDate) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.flyDate = flyDate;
    }

    public FlightInfo(int id, int flightNumber, int originId, int destinationId, Timestamp flyDateTime,
                      float cost, int capacity, int remainingSeats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.originId = originId;
        this.destinationId = destinationId;
        this.flyDateTime = flyDateTime;
        this.cost = cost;
        this.capacity = capacity;
        this.remainingSeats = remainingSeats;
        completedReserves = capacity - remainingSeats;
        temporaryReserves = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public void setFlyDateTime(Timestamp flyDateTime) {
        this.flyDateTime = flyDateTime;
    }

    public void setFlyDate(Date flyDate) {
        this.flyDate = flyDate;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
    }

    public void setCompletedReserves(int completedReserves) {
        this.completedReserves = completedReserves;
    }

    public void setTemporaryReserves(int temporaryReserves) {
        this.temporaryReserves = temporaryReserves;
    }

    public int getId() {
        return id;
    }

    public int getOriginId() {
        return originId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public Date getFlyDate() {
        return flyDate;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Timestamp getFlyDateTime() {
        return flyDateTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public Float getCost() {
        return cost;
    }

    public void addTemporaryReserves(int num) {
        temporaryReserves = temporaryReserves + num;
    }

    public int getRemainingCapacity() {
        return remainingSeats - temporaryReserves;
    }

    public int getCompletedReserves() {
        return completedReserves;
    }

    public int getTemporaryReserves() {
        return temporaryReserves;
    }

//    public City getOriginCity() {
//        return originCity;
//    }
//
//    public void setOriginCity(City originCity) {
//        this.originCity = originCity;
//    }
//
//    public City getDestinationCity() {
//        return destinationCity;
//    }
//
//    public void setDestinationCity(City destinationCity) {
//        this.destinationCity = destinationCity;
//    }
}
