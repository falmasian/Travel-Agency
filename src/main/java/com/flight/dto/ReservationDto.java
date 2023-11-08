package com.flight.dto;

public class ReservationDto {
    /**
     * کد ملی
     */
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

    @Override
    public String toString() {
        return "ReservationDto{" + "\n" +
               "    nationalCode=" + nationalCode + "\n" +
               '}' + "\n";
    }
}
