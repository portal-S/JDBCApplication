package com.portal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnect {
    private static final String URL = "jdbc:mysql://127.0.0.1/test";
    private static final String USER = "bestuser";
    private static final String PASS = "bestuser";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
