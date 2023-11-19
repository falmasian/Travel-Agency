package com.flight.service;

import com.flight.Mapper.ReservationGetMapper;
import com.flight.Mapper.ReservationMapper;
import com.flight.aspect.Service;
import com.flight.dto.CustomerReservationsResponseDto;
import com.flight.dto.ReservationDto;
import com.flight.dto.ReservationGetDto;
import com.flight.entity.Reservation;
import com.flight.repository.ReserveRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationService {

    private final ReserveRepository reserveRepository;
    private final ReservationGetMapper reservationGetMapper;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReserveRepository reserveRepository, ReservationGetMapper reservationGetMapper
            , ReservationMapper reservationMapper) {
        this.reserveRepository = reserveRepository;
        this.reservationGetMapper = reservationGetMapper;
        this.reservationMapper = reservationMapper;
    }

    @Service
    public CustomerReservationsResponseDto getAllReservations(ReservationDto reservationDto) {
        Reservation r = reservationMapper.toReservation(reservationDto);
        List<ReservationGetDto> reservationGetDtoList = reserveRepository.findReserveByCustomerId(r.getCustomerId().trim())
                .stream()
                .map(reservationGetMapper::toReservationGetDto)
                .toList();
        return new CustomerReservationsResponseDto(reservationGetDtoList);

    }
}
