package com.flightagency.service;

import com.flightagency.Mapper.ReservationGetMapper;
import com.flightagency.Mapper.ReservationMapper;
import com.flightagency.config.dao.ReservationDao;
import com.flightagency.dto.ReservationDto;
import com.flightagency.dto.ReservationGetDto;
import com.flightagency.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Component
public class ReservationService {
    private ReservationDao reservationDao;
    private ReservationGetMapper reservationGetMapper;
    private ReservationMapper reservationMapper;

    public ReservationService(ReservationDao reservationDao, ReservationGetMapper reservationGetMapper, ReservationMapper reservationMapper) {
        this.reservationDao = reservationDao;
        this.reservationGetMapper = reservationGetMapper;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationGetDto> getAllReservations(ReservationDto reservationDto) {
        Reservation r = reservationMapper.toReservation(reservationDto);
        return reservationDao.getAllReservationsByCostomerId(r.getCustomerId().trim())
                .stream()
                .map(reservationGetMapper::toReservationGetDto)
                .collect(toList());

    }
}
