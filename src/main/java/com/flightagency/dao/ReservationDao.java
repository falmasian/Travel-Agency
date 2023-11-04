package com.flightagency.dao;

import com.flightagency.entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao extends BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(ReservationDao.class);

    public ReservationDao(Connection conn) {
        super(conn);
    }

    public void insertReservation(String customerId, int flightId, String passengerNationalCode, String trackingCode) {
        var query = "insert into reservation(id, customerId , flightId  , TRACKINGCODE , passengerNationalCode) values(reservationId_seq.nextval,?,?,?,?)";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, customerId);
            ps.setInt(2, flightId);
            ps.setString(3, trackingCode);
            ps.setString(4, passengerNationalCode);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in insert to reservation table");
        }
    }

    public void deleteReservationByCustomerId(String customerId) {
        var query = "delete from reservation where customerId = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, customerId);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in remove from reservation table");
        }
    }

    public void deleteReservationByFlightId(int flightId) {
        var query = "delete from reservation where flightId = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in remove from reservation table");
        }
    }

    public List<Reservation> getAllReservation() {
        List<Reservation> reservations = new ArrayList<>();
        var query = "select CustomerId,FlightId,passengerNationalCode,trackingCode from reservation";
        try (var ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            logger.error("error in select from reservation table");
        }
        return reservations;
    }

    public List<Reservation> getAllReservationsByCostomerId(String customerId) {
        List<Reservation> reservations = new ArrayList<>();
        var query = "select  CustomerId,FlightId,passengerNationalCode,trackingCode from reservation where customerId = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            logger.error("error in select from reservation table");
        }
        return reservations;
    }

    public void getReservationByCustomerId(String customerId) {
        var query = "select * from reservation where customerId = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getInt(1)+" "+rs.getString(2));
            }
        } catch (Exception e) {
            logger.error("error in select from reservation table");
        }
    }

    public void getReservationByFlightId(int flightId) {
        var query = "select * from reservation where flightId = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, flightId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getInt(1)+" "+rs.getString(2));
            }
        } catch (Exception e) {
            logger.error("error in select from reservation table");
        }
    }

    public List<Reservation> getAllReservationsByTrackingCode(String trackingCode) {
        List<Reservation> reservations = new ArrayList<>();
        var query = "select  CustomerId,FlightId,passengerNationalCode,trackingCode from reservation where trackingCode = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, trackingCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            logger.error("error in select from reservation table");
        }
        return reservations;
    }

    public void deleteByCustomerIdAndPassengerNationalCode(String customerId, String nationalcode) {
        var query = "delete from reservation where CustomerId = ? and passengerNationalCode = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, customerId);
            ps.setString(2, nationalcode);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in remove from reservation table");
        }
    }
}