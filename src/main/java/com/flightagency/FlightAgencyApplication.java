package com.flightagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FlightAgencyApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightAgencyApplication.class, args);
    }


}

