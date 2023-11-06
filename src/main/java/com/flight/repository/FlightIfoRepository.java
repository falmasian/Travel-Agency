package com.flight.repository;

import com.flight.entity.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightIfoRepository extends JpaRepository<FlightInfo, Integer> {

    float findCostById(int id);

    int findRemainingSeatsById(int id);

    List<FlightInfo> findFlightInfoByOriginIdAndDestinationId(int originId, int destinationId);

}
