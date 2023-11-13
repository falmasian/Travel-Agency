package com.flight.repository;

import com.flight.entity.Reserve;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {

    List<Reserve> findReserveByCustomerId(String customerId);

    @Transactional
    void deleteByCustomerIdAndPassengerNationalCode(@Param("customerId") String customerId, @Param("passengerNationalCode") String nationalcode);

}
