package com.flightagency.controller;

import com.flightagency.dto.ReservationDto;
import com.flightagency.dto.ReservationGetDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
public interface ReservationController {
    @PostMapping(value = "/api/reservation", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<ReservationGetDto> getAllReservations(@RequestBody ReservationDto reservationDto);
}
