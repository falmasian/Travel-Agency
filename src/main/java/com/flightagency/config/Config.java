package com.flightagency.config;

import com.flightagency.admin.CreateTables;
import com.flightagency.config.dao.CityDao;
import com.flightagency.config.dao.FlightInfoDao;
import com.flightagency.config.dao.ReservationDao;
import com.flightagency.database.ConnectOracleDB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

@Configuration
public class Config {

    @Bean
    public ConnectOracleDB connectOracleDB() throws Exception {
        ConnectOracleDB c= new ConnectOracleDB("static/DBpropreties.txt");
        if (c == null){
            System.out.println("ConnectOracleDB is null");
        }
        return c;
    }

    @Bean
    public Connection connection() throws Exception {
        Connection cc = connectOracleDB().connect();
        if (cc == null){
            System.out.println("Connect is null");
        }
        return cc;
    }

    @Bean
    public ReservationDao reservationDao() throws Exception {
        return new ReservationDao(connection());
    }

    @Bean
    public FlightInfoDao flightInfoDao() throws Exception {
        return new FlightInfoDao(connection());
    }

    @Bean
    public CityDao cityDao() throws Exception {
        System.out.println("");
        return new CityDao(connection());
    }

    @Bean
    public CreateTables createTables() throws Exception {
        return new CreateTables(connection());
    }

//    @Bean
//    public CityService cityService() throws Exception {
//        return new CityService(cityDao());
//    }

  //  @Bean
    //public CityMapper cityMapper() {
        //return new CityMapper();
   // }

//    @Bean
//    public BookingService bookingService() throws Exception {
//        return new BookingService(cityDao(), flightInfoDao());
//    }

//    @Bean
//    public BookingMapper bookingMapper() throws Exception {
//        return new BookingMapper();
//    }

//
//    @Bean
//    public FilterService filterService() throws Exception {
//        return new FilterService(flightInfoDao());
//    }

//    @Bean
//    public FilterFlightMapper filterFlightMapper() {
//        return new FilterFlightMapper();
//    }

//    @Bean
//    public FlightService flightService() throws Exception {
//        return new FlightService(flightInfoDao());
//    }
//
//    @Bean
//    public FlightMapper flightMapper() throws Exception {
//        return new FlightMapper();
//    }

//    @Bean
//    public PaymentService paymentService() throws Exception {
//        return new PaymentService(flightInfoDao(), reservationDao());
//    }

//    @Bean
//    public PaymentMapper paymentMapper() {
//        return new PaymentMapper();
//    }

//    @Bean
//    public ReservationService reservationService() throws Exception {
//        return new ReservationService(reservationDao());
//    }

//    @Bean
//    public ReservationGetMapper reservationGetMapper() {
//        return new ReservationGetMapper();
//    }

//    @Bean
//    public ReservationMapper reservationMapper() {
//        return new ReservationMapper();
//    }

//    @Bean
//    public CancellationService cancellationService() throws Exception {
//        return new CancellationService(flightInfoDao(),reservationDao());
//    }
//
//    @Bean
//    public CancellationMapper cancellationMapper() {
//        return new CancellationMapper();
//    }
}

