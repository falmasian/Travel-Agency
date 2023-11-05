package com.flightagency.repository;

import com.flightagency.entity.FlightInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightInfo, Integer> {


    float findCostById(int id);

    int findRemainingSeatsById(int id);


    @Transactional
    @Modifying
    @Query(" UPDATE FlightInfo f SET f.remainingSeats = f.remainingSeats + :numOfTickets WHERE f.id = :id ")
    void incrementRemainingSeatsById(@Param("id") int id, @Param("numOfTickets") int numOfTickets);

    @Transactional
    @Modifying
    @Query(" UPDATE FlightInfo f SET f.remainingSeats = f.remainingSeats - :numOfTickets WHERE f.id = :id ")
    void decrementRemainingSeatsById(@Param("id") int id, @Param("numOfTickets") int numOfTickets);

    @Query("SELECT f FROM FlightInfo f WHERE f.originId = :originId AND f.destinationId = :destinationId AND trunc(f.flyDate) = trunc(:flyDate)")
    List<FlightInfo> findFlightsByOriginIdAndDestinationIdAndFlyDate(
            @Param("originId") Integer originId,
            @Param("destinationId") Integer destinationId,
            @Param("flyDate") Date flyDate
    );

}
