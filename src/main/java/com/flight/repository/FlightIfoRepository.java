package com.flight.repository;

import com.flight.entity.FlightInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightIfoRepository extends JpaRepository<FlightInfo, Integer>
        , JpaSpecificationExecutor<FlightInfo> {
}
