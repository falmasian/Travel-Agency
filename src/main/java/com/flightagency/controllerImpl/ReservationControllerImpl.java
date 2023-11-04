package com.flightagency.controllerImpl;

import com.flightagency.controller.ReservationController;
import com.flightagency.dto.ReservationDto;
import com.flightagency.dto.ReservationGetDto;
import com.flightagency.service.ReservationService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationControllerImpl implements ReservationController {

    private ReservationService reservationService;

    public ReservationControllerImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public List<ReservationGetDto> getAllReservations(ReservationDto reservationDto) {
        return reservationService.getAllReservations(reservationDto);
    }
}
