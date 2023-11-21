package com.flight.service;

import com.flight.Mapper.FilterFlightMapper;
import com.flight.Mapper.FlightMapper;
import com.flight.aspect.Service;
import com.flight.dto.FilterFlightDto;
import com.flight.dto.FilterResponseDto;
import com.flight.dto.FlightDto;
import com.flight.entity.City;
import com.flight.entity.FlightInfo;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.InvalidInputException;
import com.flight.repository.FlightIfoRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class FilterService {

    private final FlightIfoRepository flightRepository;
    private final FilterFlightMapper filterFlightMapper;
    private final FlightMapper flightMapper;

    public FilterService(FlightIfoRepository flightRepository, FilterFlightMapper filterFlightMapper
            , FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.filterFlightMapper = filterFlightMapper;
        this.flightMapper = flightMapper;
    }

    @Service
    public FilterResponseDto filter(FilterFlightDto filterFlightDto) throws FlightNotFoundException {
        FlightInfo flight = filterFlightMapper.toFlight(filterFlightDto);
        if(flight.getOriginCity().getCityId() == flight.getDestinationCity().getCityId()){
            throw new InvalidInputException("origin and destination city can't be the same.");
        }
        LocalDateTime now = LocalDateTime.now();
//        if (flight.getFlyDate().before(new Date())){
//            throw new InvalidInputException("Input date is before current date.");
//        }
        Calendar c = Calendar.getInstance();
        c.setTime(flight.getFlyDate());
        c.add(Calendar.DATE, 1);
        java.util.Date nextDay = c.getTime();
        Specification<FlightInfo> specification = hasOriginId(filterFlightDto.getOriginId())
                .and(hasDestinationId(filterFlightDto.getDestinationId()))
                .and(hasRemainSeats());
        List<FlightDto> flightDtoList = flightRepository.findAll(specification)
                .stream()
                .filter(f -> f.getFlyDateTime().after(flight.getFlyDate()))
                .filter(f -> f.getFlyDateTime().before(nextDay))
                .map(flightMapper::toFlightDto).toList();
        if (flightDtoList.isEmpty()) {
            throw new FlightNotFoundException("No match flights with this specification was found");
        }
        return new FilterResponseDto(flightDtoList);
    }

    private Specification<FlightInfo> hasOriginId(int originId) {
        return (root, query, criteriaBuilder) -> {
            Join<City, FlightInfo> cityFlightInfoJoin = root.join("originCity");
            return criteriaBuilder.equal(cityFlightInfoJoin.get("cityId"), originId);
        };
    }

    private Specification<FlightInfo> hasDestinationId(int destinationId) {
        return (root, query, criteriaBuilder) -> {
            Join<City, FlightInfo> cityFlightInfoJoin = root.join("destinationCity");
            return criteriaBuilder.equal(cityFlightInfoJoin.get("cityId"), destinationId);
        };
    }

    private Specification<FlightInfo> hasRemainSeats() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("remainingSeats"), 0);
        };
    }
}
