package com.portal.utils;

import java.sql.*;

public class BDConnect {
    private static final String URL = "jdbc:mysql://127.0.0.1/test";
    private static final String USER = "bestuser";
    private static final String PASS = "bestuser";
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
                connection.setAutoCommit(false);
                return connection;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void commit(){
        try {
            if(connection != null && !connection.isClosed()){
                connection.commit();
            }
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Statement getStatement() throws SQLException {
        try {
            return connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getPrepareStatement(String sql) throws SQLException {
        try {
            return connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
