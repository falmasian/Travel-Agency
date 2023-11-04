package com.flightagency.dao;

import java.sql.Connection;

public class BaseDao {
    Connection connection;

    public BaseDao(Connection connection) {
        this.connection = connection;
    }
}
