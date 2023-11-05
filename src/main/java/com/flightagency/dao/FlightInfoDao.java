package com.flightagency.dao;

import com.flightagency.entity.FlightInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlightInfoDao extends BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(FlightInfoDao.class);

    public FlightInfoDao(Connection connection) {
        super(connection);
    }

    public void insertFlightInfo(FlightInfo flight) {
        int res = 0;
        var query = "insert into flightInfo(id,flightNumber,originId,destinationId,flyDate,cost,capacity,remainingSeats) values(?,?,?,?,?,?,?,?)";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flight.getId());
            ps.setInt(2, flight.getFlightNumber());
            ps.setInt(3, flight.getOriginId());
            ps.setInt(4, flight.getDestinationId());
            ps.setTimestamp(5, flight.getFlyDateTime());
            ps.setFloat(6, flight.getCost());
            ps.setInt(7, flight.getCapacity());
            ps.setInt(8, flight.getRemainingSeats());
            res = ps.executeUpdate();
        } catch (Exception e) {
            logger.error("error in insert to flightInfo table");
        }
    }

    public int deleteFlightInfoById(int id) {
        var query = "delete from flightInfo where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.error("error in insert to flightInfo table");
        }
        return 0;
    }

    public int deleteFlightInfoByFlightNumber(int flightNumber) {
        var query = "delete from flightInfo where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightNumber);
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.error("error in insert to flightInfo table");
        }
        return 0;
    }

    public ArrayList<FlightInfo> getAllFlightInfo() {
        var query = "select id,flightNumber,originId,destinationId,flyDate,cost,capacity,remainingSeats from flightInfo";
        ArrayList<FlightInfo> flightArrayList = new ArrayList<>();
        try (var ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            System.out.println("flight number  originId destinationId  Fly time cost Seats number");
            while (rs.next()) {
                FlightInfo flight = new FlightInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        rs.getTimestamp(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
                flightArrayList.add(flight);
            }
        } catch (Exception e) {
            logger.error("error in select from flightInfo table");
        }
        return flightArrayList;
    }

    public List<FlightInfo> getAllFilterFlightInfo(FlightInfo flight) {
        var query = "select * from flightInfo where originId = ? AND destinationId = ? AND trunc(flyDate) = ?";
        List<FlightInfo> flights = new ArrayList<>();
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flight.getOriginId());
            ps.setInt(2, flight.getDestinationId());
            ps.setDate(3, flight.getFlyDate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                flights.add(new FlightInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
                        rs.getFloat(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (Exception e) {
            logger.error("error in select from flightInfo table");
        }
        return flights;
    }


    public FlightInfo getFlightInfoByFlightNumber(int flightNumber) {
        var query = "select * from flightInfo where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new FlightInfo(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getTimestamp(5),
                        rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
        } catch (Exception e) {
            logger.error("error in select from flightInfo table");
        }
        return null;
    }

    public float getCostByFlightId(int flightId) {
        float cost = 0;
        var query = "select cost from flightInfo where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cost = rs.getFloat(1);
            }
        } catch (Exception e) {
            logger.error("error in select from flightInfo table");
        }
        return cost;
    }

    public int getRemainSeatsByFlightId(int flightId) {
        int remain = 0;
        var query = "select remainingSeats from flightInfo where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                remain = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error("error in select from flightInfo table");
        }
        return remain;
    }

    public int decreaseRemainSeatsById(int flightId, int numOfTickets) {
        var query = """
                update flightInfo 
                set remainingSeats =(select remainingSeats from flightInfo where id = ?) - ?
                where id = ? """;

        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ps.setInt(2, numOfTickets);
            ps.setInt(3, flightId);
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            logger.error("error in update  flightInfo table");
        }
        return 0;
    }

    public int increaseRemainSeatsById(int flightId, int numOfTickets) {
        var query = """
                update flightInfo 
                set remainingSeats =(select remainingSeats from flightInfo where id = ?) + ?
                where id = ? """;

        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ps.setInt(2, numOfTickets);
            ps.setInt(3, flightId);
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            logger.error("error in update  flightInfo table");
        }
        return 0;
    }
}