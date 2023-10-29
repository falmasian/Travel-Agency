package com.flightagency.config.dao;

import com.flightagency.entity.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CityDao extends BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(CityDao.class);

    public CityDao(Connection connection) {
        super(connection);
    }

    public void insertCity(City city) {

        var query = "insert into city(id,cityName) values(cityId_seq.nextval,?)";
        try (var ps = connection.prepareStatement(query)) {
            ps.setString(1, city.getCityName());
            ps.executeQuery();
        } catch (Exception e) {
            logger.error("error in insert to city table");
        }
    }

    public int deleteCityById(int id) {
        var query = "delete from city where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            logger.error("error in remove from city table");
        }
        return 0;
    }

    public List<City> getAllCity() {

        var query = "select * from city";
        List<City> cities = new ArrayList<>();
        if (connection == null){
            logger.error("conn is null");
        }
        try (var ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
            logger.error("error in select from city table");
        }
        return cities;
    }

    public void getCityById(int id) {
        var query = "select * from city where id = ?";
        try (var ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            logger.error("error in insert from city table");
        }
    }
}