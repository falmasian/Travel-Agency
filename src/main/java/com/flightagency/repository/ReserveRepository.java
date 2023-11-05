package com.flightagency.repository;

import com.flightagency.entity.Reservation;
import com.flightagency.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve, Integer> {

    List<Reserve> findReserveByCustomerId(String customerId);
    void deleteByCustomerIdAndPassengerNationalCode(String customerId, String nationalcode);
}
