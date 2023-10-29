package com.flightagency.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Flight {
    private int id;
    private int flightNumber;
    private int originId;
    private int destinationId;
    private Timestamp flyDateTime;
    private Date flyDate;
    private float cost;
    private int capacity;
    private int remainingSeats;
    private int completedReserves;
    private int temporaryReserves;

    public Flight(int originId, int destinationId, Date flyDate) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.flyDate = flyDate;

    }

    public Flight(int id, int flightNumber, int originId, int destinationId, Timestamp flyDateTime, float cost, int capacity, int remainingSeats) {
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

    public void SetOriginId(int id) {
        originId = id;
    }

    public void SetDestinationId(int id) {
        destinationId = id;
    }

    public void SetFlyDate(Date date) {
        flyDate = date;
    }

    public void SetFlightNumber(int number) {
        flightNumber = number;
    }

    public void SetFlyDateTime(Timestamp time) {
        flyDateTime = time;
    }

    public void SetCapacity(int cap) {
        capacity = cap;
    }

    public void SetRemainingSeats(int seats) {
        remainingSeats = seats;
    }

    public void SetCost(Float co) {
        cost = co;
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

    public synchronized void updateAfterPayment(int numOfCompleted) {
        completedReserves = completedReserves + numOfCompleted;
        temporaryReserves = temporaryReserves - numOfCompleted;
        remainingSeats = remainingSeats - numOfCompleted;
    }

    public synchronized void updateAfterCancellation(int numOfCancelled) {
        completedReserves = completedReserves - numOfCancelled;
        remainingSeats = remainingSeats + numOfCancelled;
    }
}
