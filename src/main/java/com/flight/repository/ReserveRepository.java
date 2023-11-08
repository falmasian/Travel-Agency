package com.flight.repository;

import com.flight.entity.Reserve;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {

    List<Reserve> findReserveByCustomerId(String customerId);

    @Transactional
    void deleteByCustomerIdAndPassengerNationalCode(@Param("customerId") String customerId, @Param("passengerNationalCode") String nationalcode);

}
