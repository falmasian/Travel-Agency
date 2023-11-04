package com.flightagency.dto;

import java.sql.Date;

public class FilterFlightDto {

    private int originId;
    private int destinationId;
    private java.sql.Date flyDate;

    public FilterFlightDto() {
    }

    public FilterFlightDto(int originId, int destinationId, java.sql.Date flyDate) {
        this.originId = originId;
        this.destinationId = destinationId;
        this.flyDate = flyDate;
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

    public java.sql.Date getFlyDate() {
        return flyDate;
    }

    public void setFlyDate(java.sql.Date flyDate) {
        this.flyDate = flyDate;
    }

    @Override
    public String toString() {
        return "FilterFlightDto{" +
               "originId=" + originId +
               ", destinationId=" + destinationId +
               ", flyDate=" + flyDate +
               '}';
    }
}
