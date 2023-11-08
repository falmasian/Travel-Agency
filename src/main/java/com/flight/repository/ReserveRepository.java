package com.flight.repository;

import com.flight.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {

    List<Reserve> findReserveByCustomerId(String customerId);

    void deleteByCustomerIdAndPassengerNationalCode(String customerId, String nationalcode);

}
