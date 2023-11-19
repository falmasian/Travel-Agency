package com.flight.service;

import com.flight.Mapper.FlightMapper;
import com.flight.aspect.Service;
import com.flight.dto.AllFlightsResponse;
import com.flight.dto.FlightDto;
import com.flight.entity.FlightInfo;
import com.flight.repository.FlightIfoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class FlightService {

    private final FlightIfoRepository flightRepository;
    private final FlightMapper flightMapper;

    public FlightService(FlightIfoRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Service
    public AllFlightsResponse getAllFlights() {
        flightRepository.findAll().stream().forEach(c -> System.out.println(c.getDestinationCity().getCityName()));

        List<FlightDto> flightDtoList =  flightRepository.findAll()
                .stream()
                .map(flightMapper::toFlightDto)
                .collect(toList());
        return new AllFlightsResponse(flightDtoList);
    }
//    public static Specification<FlightInfo> joinCity() {
//        return (root, query, criteriaBuilder) -> {
//            Join<FlightInfo, City> FlightCity = root.join("City ");
//            return criteriaBuilder.equal(authorsBook.get("title"), bookTitle);
//        };
//    }

    @Service
    public boolean deleteFlight(int id) {
        try {
            flightRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Service
    public int insertFlight(FlightDto flightDto) {
        FlightInfo flightInfo = flightMapper.toFlight(flightDto);
        flightRepository.save(flightInfo);
        return flightInfo.getFlightNumber();
    }
}
