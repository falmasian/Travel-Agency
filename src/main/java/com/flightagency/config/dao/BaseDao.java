package com.flightagency.config.dao;

import java.sql.Connection;

public class BaseDao {
    Connection connection;

    public BaseDao(Connection connection) {
        this.connection = connection;
    }
}
