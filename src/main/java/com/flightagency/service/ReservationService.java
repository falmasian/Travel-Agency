package com.flightagency.service;

import com.flightagency.Mapper.ReservationGetMapper;
import com.flightagency.Mapper.ReservationMapper;
import com.flightagency.Mapper.ReserveMapper;
import com.flightagency.aspect.ServiceAnnotation;
import com.flightagency.dao.ReservationDao;
import com.flightagency.dto.ReservationDto;
import com.flightagency.dto.ReservationGetDto;
import com.flightagency.entity.Reservation;
import com.flightagency.repository.ReserveRepository;
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

    @ServiceAnnotation
    public List<ReservationGetDto> getAllReservations(ReservationDto reservationDto) {
        Reservation r = reservationMapper.toReservation(reservationDto);
        return reserveRepository.findReserveByCustomerId(r.getCustomerId().trim())
                .stream()
                .map(reserveMapper::toReservation)
                .map(reservationGetMapper::toReservationGetDto)
                .collect(toList());

    }
}
