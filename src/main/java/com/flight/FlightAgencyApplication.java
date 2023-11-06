package com.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAspectJAutoProxy
@EntityScan(value = "com.flight.entity")
@EnableJpaRepositories
public class FlightAgencyApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightAgencyApplication.class, args);
    }


}

