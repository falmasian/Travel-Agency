package com.flightagency.dto;

public class ReservationDto {
    String nationalCode;

    public ReservationDto() {
    }

    public ReservationDto(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }


}