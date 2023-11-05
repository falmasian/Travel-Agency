package com.flightagency.server;


import com.flightagency.cache.CacheManager;
import com.flightagency.entity.FlightInfo;
import com.flightagency.entity.Reservation;

public class CreatedCaches{
	
	public final static CacheManager<String, Reservation> reservationCacheManager = new CacheManager<>();
	public final static String reservedFlightsCacheName = "reservedFlights";
	static{reservationCacheManager.createCache(reservedFlightsCacheName, 600);}
	public final static CacheManager<Integer, FlightInfo> flightCapacityCacheManager = new CacheManager<>();
	public final static String flightCapacityCacheName = "flightCapacity";
	 static{flightCapacityCacheManager.createCache(flightCapacityCacheName, -1);}
}