package com.flightagency.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

public class CustomerDao extends BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    public CustomerDao(Connection conn) {
        super(conn);
    }

    public void insertCustomer(String nationalCode, String firstName, String lastName, Date birthDate) {
        var query = "insert into customer(id,nationalCode,firstName,lastName,birthDate) values(?,?,?,?,?)";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, nationalCode);
            ps.setString(2, nationalCode);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setDate(5, birthDate);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in insert to customer table");
        }
    }

    public void deleteCustomerById(int id) {
        var query = "delete from customer where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in remove from customer table");
        }
    }

    public void deleteCustomerByNationalCode(String nationalCode) {
        var query = "delete from customer where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, nationalCode);
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in remove from customer table");
        }
    }

    public void getAllCustomer() {
        var query = "select * from customer";
        try (var ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            }
        } catch (Exception e) {
            logger.error("error in select from customer table");
        }
    }

    public void getCustomerById(int id) {
        var query = "select * from customer where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            }
        } catch (Exception e) {
            logger.error("error in select from customer table");
        }
    }

    public void getCustomerByNationalCode(String nationalCode) {
        var query = "select * from customer where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, nationalCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            }
        } catch (Exception e) {
            logger.error("error in select from customer table");
        }
    }
}