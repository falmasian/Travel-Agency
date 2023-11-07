package com.flight.service;

import com.flight.Mapper.ReservationGetMapper;
import com.flight.Mapper.ReservationMapper;
import com.flight.Mapper.ReserveMapper;
import com.flight.aspect.ServiceLoggingAspect;
import com.flight.dto.ReservationDto;
import com.flight.dto.ReservationGetDto;
import com.flight.entity.Reservation;
import com.flight.entity.Reserve;
import com.flight.repository.ReserveRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ReservationService {
    private ReserveRepository reserveRepository;
    private ReservationGetMapper reservationGetMapper;
    private ReservationMapper reservationMapper;
    private ReserveMapper reserveMapper;

    public ReservationService(ReserveRepository reserveRepository, ReservationGetMapper reservationGetMapper
            , ReservationMapper reservationMapper,ReserveMapper reserveMapper) {
        this.reserveRepository = reserveRepository;
        this.reservationGetMapper = reservationGetMapper;
        this.reservationMapper = reservationMapper;
        this.reserveMapper = reserveMapper;
    }

    @ServiceLoggingAspect
    public List<ReservationGetDto> getAllReservations(ReservationDto reservationDto) {
        reserveRepository.findAll().forEach(Reserve::getId);
        reserveRepository.findAll().forEach(r -> r.toString());
        Reservation r = reservationMapper.toReservation(reservationDto);
        return reserveRepository.findReserveByCustomerId(r.getCustomerId().trim())
                .stream()
                .map(reserveMapper::toReservation)
                .map(reservationGetMapper::toReservationGetDto)
                .collect(toList());

    }
}
