package com.flightagency.controller;

import com.flightagency.dto.ReservationDto;
import com.flightagency.dto.ReservationGetDto;
import com.flightagency.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/api/reservation")
    @ResponseBody
    public List<ReservationGetDto> getAllReservations(@RequestBody ReservationDto reservationDto) {
        return reservationService.getAllReservations(reservationDto);
    }
}
