package com.portal.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@FunctionalInterface
public interface RepoInterface<T> {
    public T func(Connection connection, ResultSet set, Statement statement) throws SQLException;
}
