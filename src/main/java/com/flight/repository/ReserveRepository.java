package com.flight.repository;

import com.flight.entity.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findReserveByCustomerId(String customerId);

    @Transactional
    void deleteByCustomerIdAndPassengerNationalCode(@Param("customerId") String customerId, @Param("passengerNationalCode") String nationalcode);

}
