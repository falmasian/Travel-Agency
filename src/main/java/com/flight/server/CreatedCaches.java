package com.flight.server;


import com.flight.cache.CacheManager;
import com.flight.entity.FlightInfo;
import com.flight.entity.Reservation;

public class CreatedCaches {

    public final static CacheManager<String, Reservation> reservationCacheManager = new CacheManager<>();
    public final static String reservedFlightsCacheName = "reservedFlights";

    static {
        reservationCacheManager.createCache(reservedFlightsCacheName, 600);
    }

    public final static CacheManager<Integer, FlightInfo> flightCapacityCacheManager = new CacheManager<>();
    public final static String flightCapacityCacheName = "flightCapacity";

    static {
        flightCapacityCacheManager.createCache(flightCapacityCacheName, -1);
    }
}