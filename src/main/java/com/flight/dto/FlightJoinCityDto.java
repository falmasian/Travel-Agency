//package com.flight.dto;
//
//
//import java.sql.Timestamp;
//
//public class FlightJoinCityDto {
//    /**
//     * شماره پرواز
//     */
//    private int flightNumber;
//    /**
//     * ایدی شهر مبدا
//     */
//    private int originId;
//    /**
//     * ایدی شهر مقصد
//     */
//    private int destinationId;
//    /**
//     * نام شهر مبدا
//     */
//    private String originCityName;
//    /**
//     * نام شهر مقصد
//     */
//    private String destinationCityName;
//    /**
//     * تاریخ و ساعت پرواز
//     */
//    private Timestamp flyDateTime;
//    /**
//     * هزیه بلیط
//     */
//    private float cost;
//    /**
//     * ظرفیت ئرواز
//     */
//    private int capacity;
//    /**
//     * صندلی های فروش نرفته
//     */
//    private int remainingSeats;
//
//    public FlightJoinCityDto() {
//    }
//
//
//    public FlightJoinCityDto(int flightNumber, int originId, int destinationId, String originCityName
//            , String destinationCityName, Timestamp flyDateTime, float cost, int capacity, int remainingSeats) {
//        this.flightNumber = flightNumber;
//        this.originId = originId;
//        this.destinationId = destinationId;
//        this.originCityName = originCityName;
//        this.destinationCityName = destinationCityName;
//        this.flyDateTime = flyDateTime;
//        this.cost = cost;
//        this.capacity = capacity;
//        this.remainingSeats = remainingSeats;
//    }
//
//    public int getFlightNumber() {
//        return flightNumber;
//    }
//
//    public void setFlightNumber(int flightNumber) {
//        this.flightNumber = flightNumber;
//    }
//
//    public int getOriginId() {
//        return originId;
//    }
//
//    public void setOriginId(int originId) {
//        this.originId = originId;
//    }
//
//    public int getDestinationId() {
//        return destinationId;
//    }
//
//    public void setDestinationId(int destinationId) {
//        this.destinationId = destinationId;
//    }
//
//    public Timestamp getFlyDateTime() {
//        return flyDateTime;
//    }
//
//    public void setFlyDateTime(Timestamp flyDateTime) {
//        this.flyDateTime = flyDateTime;
//    }
//
//    public float getCost() {
//        return cost;
//    }
//
//    public void setCost(float cost) {
//        this.cost = cost;
//    }
//
//    public int getCapacity() {
//        return capacity;
//    }
//
//    public void setCapacity(int capacity) {
//        this.capacity = capacity;
//    }
//
//    public int getRemainingSeats() {
//        return remainingSeats;
//    }
//
//    public void setRemainingSeats(int remainingSeats) {
//        this.remainingSeats = remainingSeats;
//    }
//
//    public String getOriginCityName() {
//        return originCityName;
//    }
//
//    public void setOriginCityName(String originCityName) {
//        this.originCityName = originCityName;
//    }
//
//    public String getDestinationCityName() {
//        return destinationCityName;
//    }
//
//    public void setDestinationCityName(String destinationCityName) {
//        this.destinationCityName = destinationCityName;
//    }
//
//    @Override
//    public String toString() {
//        return "FlightJoinCityDto{" +
//               "flightNumber=" + flightNumber +
//               ", originId=" + originId +
//               ", destinationId=" + destinationId +
//               ", originCityName=" + originCityName +
//               ", destinationCityName=" + destinationCityName +
//               ", flyDateTime=" + flyDateTime +
//               ", cost=" + cost +
//               ", capacity=" + capacity +
//               ", remainingSeats=" + remainingSeats +
//               '}';
//    }
//}
