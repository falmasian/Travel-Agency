package com.flightagency.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class ConnectOracleDB {

    private final String dbPropertiesFile;
    private String driverClass;
    private String connectionObject;
    private String username;
    private String password;
    private final HashMap<String, String> fileDataMap;
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(ConnectOracleDB.class);

    public ConnectOracleDB(String dbPropertiesFile) throws Exception {
        this.dbPropertiesFile = dbPropertiesFile;
        fileDataMap = new HashMap<>();
        setFileData();
        setFields();
    }

    public void setFileData() {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(dbPropertiesFile);
            Scanner myReader;
            myReader = new Scanner(is);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split("=", 2);
                fileDataMap.put(arrOfData[0].trim().toLowerCase(), arrOfData[1].trim());
            }
            myReader.close();
        } catch (Exception e) {
            logger.error("could not connect to database.");
        }
    }

    public void setFields() throws Exception {
        username = fileDataMap.get("username");
        throwExceptionIfIsNull(username, "username");
        password = fileDataMap.get("password");
        throwExceptionIfIsNull(password, "password");
        connectionObject = fileDataMap.get("connectionobject");
        throwExceptionIfIsNull(connectionObject, "connectionobject");
        driverClass = fileDataMap.get("driverclass");
        throwExceptionIfIsNull(driverClass, "driverclass");
    }

    public void throwExceptionIfIsNull(String field, String text) throws Exception {
        if (field == null) {
            throw new Exception(text + " could not be empty");
        }
    }

    public Connection connect() {
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(
                    connectionObject, username, password);
        } catch (Exception e) {

            System.out.println("exception in connecting db");
            e.printStackTrace();
        } finally {
            if (connection == null) {
                logger.error("could not connect to database.");
            }
            return connection;
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error("error in disconnect");
        }
    }

}