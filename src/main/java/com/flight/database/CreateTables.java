//package com.flightagency.database;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Connection;
//
//public class CreateTables {
//
//    private static final Logger logger = LoggerFactory.getLogger(CreateTables.class);
//    private final Connection conn;
//
//    public CreateTables(Connection conn) {
//        this.conn = conn;
//    }
//
//    public Connection getConn() {
//        return conn;
//    }
//
//    public void createAllTables() {
//        try {
//            createCustomerTable();
//            createCityTable();
//            createFlightInfoTable();
//            createReservationTable();
//            makeAllIdSeq();
//        } catch (Exception e) {
//            logger.error("An error occurred");
//        }
//    }
//
//    public void createCustomerTable() {
//        var createCustomerTableSql = """
//                CREATE TABLE customer(
//                id VARCHAR2(10) ,
//                nationalCode VARCHAR2(10) NOT NULL,
//                firstName NVARCHAR2(50) NOT NULL,
//                lastName NVARCHAR2(50) NOT NULL,
//                birthDate DATE,
//                CONSTRAINT customer_pk PRIMARY KEY(id)
//                )""";
//        try (var ps = conn.prepareStatement(createCustomerTableSql)) {
//            ps.executeQuery();
//        } catch (Exception e) {
//            logger.error("error in creating customer table. maybe this Table already exist.");
//        }
//    }
//
//    public void createCityTable() {
//
//        var createCityTableSql = """
//                CREATE TABLE city(
//                id NUMBER ,
//                cityName NVARCHAR2(50) NOT NULL,
//                CONSTRAINT city_pk PRIMARY KEY(id)
//                )""";
//        try (var ps = conn.prepareStatement(createCityTableSql)) {
//            ps.executeQuery();
//        } catch (Exception e) {
//            logger.error("error in creating city table. maybe this Table already exist.");
//        }
//    }
//
//    public void createFlightInfoTable() {
//        var createFlightInfoTableSql = """
//                CREATE TABLE flightInfo(
//                id NUMBER,
//                flightNumber NUMBER NOT NULL,
//                originId NUMBER NOT NULL,
//                destinationId NUMBER NOT NULL,
//                flyDate TIMESTAMP(2) NOT NULL,
//                cost FLOAT NOT NULL,
//                capacity NUMBER NOT NULL,
//                remainingSeats NUMBER NOT NULL,
//                CONSTRAINT flightInfo_pk  PRIMARY KEY(id),
//                FOREIGN KEY(originId) REFERENCES city(id),
//                FOREIGN KEY(destinationId) REFERENCES city(id)
//                				   )""";
//        try (var ps = conn.prepareStatement(createFlightInfoTableSql)) {
//            ps.executeQuery();
//        } catch (Exception e) {
//            logger.error("error in creating flightInfo table. maybe this Table already exist.");
//        }
//    }
//
//    public void createReservationTable() {
//        var createReservationTableSql = """
//                CREATE TABLE reservation(
//                id Number,
//                flightId NUMBER,
//                customerId VARCHAR2(10),
//                passengerNationalCode VARCHAR2(10),
//                trackingCode VARCHAR2(20),
//                CONSTRAINT reservation_pk PRIMARY KEY(id),
//                FOREIGN KEY(flightId) REFERENCES flightInfo(id)
//                )""";
//
//
//        try (var ps = conn.prepareStatement(createReservationTableSql)) {
//            ps.executeQuery();
//        } catch (Exception e) {
//            logger.error("error in creating reservation table. maybe this Table already exist.");
//        }
//    }
//
//    public void makeIdSeq(String idSeq, String triggerName, String tableName, String idName) {
//        var createIdSeq = " CREATE SEQUENCE " + idSeq;
//        try (var ps = conn.prepareStatement(createIdSeq)) {
//            ps.executeQuery();
//        } catch (Exception e) {
//            logger.error("An error occurred");
//        }
//    }
//
//    public void makeAllIdSeq() {
//        //makeIdSeq(conn, "customerId_seq", "customer_bi", "customer", "id");
//        //makeIdSeq(conn, "cityId_seq", "city", "city", "id");
//        makeIdSeq("reservationId_seq", "reservation", "reservation", "id");
//        //makeIdSeq(conn, "flightInfoId_seq", "flightInfo_bi", "flightInfo", "id");
//    }
//
//
//}