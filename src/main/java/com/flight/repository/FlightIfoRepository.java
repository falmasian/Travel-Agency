package com.flight.repository;

import com.flight.entity.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Repository
public interface FlightIfoRepository extends JpaRepository<FlightInfo, Integer> {

    List<FlightInfo> findFlightInfoByOriginIdAndDestinationId(int originId, int destinationId);
}
