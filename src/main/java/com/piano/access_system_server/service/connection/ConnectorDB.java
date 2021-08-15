package com.piano.access_system_server.service.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * User: Svetlana Chernyshkova
 * Time: 2021-08-12 23:25
 */
public class ConnectorDB {
    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/db");

        return ds.getConnection();
    }
}
