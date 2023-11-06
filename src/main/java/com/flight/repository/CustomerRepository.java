package com.flight.repository;

import com.flight.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
}