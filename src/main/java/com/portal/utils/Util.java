package com.portal.utils;

import java.sql.*;

public class Util {
    public static Integer getMaxId(String table){
        Statement statement = null;
        Connection connection = BDConnect.getConnection();
        ResultSet set = null;
        try {
            statement = connection.createStatement();
            set = statement.executeQuery("SELECT id FROM " + table + " ORDER BY id DESC LIMIT 1");
            if(set.next()){
                return set.getInt("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
                set.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }
}
