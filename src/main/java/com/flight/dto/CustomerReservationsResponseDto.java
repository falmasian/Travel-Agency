package com.flight.dto;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CustomerReservationsResponseDto {

    /**
     * لیست رزرو ها
     */
    private List<ReservationGetDto> reservationGetDtoList;

    public CustomerReservationsResponseDto(List<ReservationGetDto> reservationGetDtoList) {
        this.reservationGetDtoList = reservationGetDtoList;
    }

    public List<ReservationGetDto> getReservationGetDtoList() {
        return reservationGetDtoList;
    }

    public void setReservationGetDtoList(List<ReservationGetDto> reservationGetDtoList) {
        this.reservationGetDtoList = reservationGetDtoList;
    }
}
