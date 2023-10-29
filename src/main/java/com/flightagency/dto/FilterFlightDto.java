package com.flightagency.dto;

import java.sql.Date;

public class FilterFlightDto {

    private int originId;
    private int destinationId;
    private Date flyDate;

    public FilterFlightDto() {
    }

    public FilterFlightDto(int originId, int destinationId, Date flyDate) {
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

    public Date getFlyDate() {
        return flyDate;
    }

    public void setFlyDate(Date flyDate) {
        this.flyDate = flyDate;
    }
}
