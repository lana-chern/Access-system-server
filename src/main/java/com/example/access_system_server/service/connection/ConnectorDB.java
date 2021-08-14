package com.example.access_system_server.service.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-12 23:25
 */
public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String password = resource.getString("db.password");
        String dbName = resource.getString("db.name");

        return DriverManager.getConnection(url + dbName, user, password);
    }
}
