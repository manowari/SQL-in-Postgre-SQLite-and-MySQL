package sql_project.sqlite;


import sql_project.DatabaseConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabaseConnection {
    private static final String PROPERTIES_FILE = "sqlite-database-config.properties";

    public static Connection getConnection() throws SQLException {
        return DatabaseConfigLoader.getConnection(PROPERTIES_FILE);
    }
}