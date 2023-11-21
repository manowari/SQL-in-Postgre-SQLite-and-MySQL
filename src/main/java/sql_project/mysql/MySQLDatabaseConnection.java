package sql_project.mysql;


import sql_project.DatabaseConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection {
    private static final String PROPERTIES_FILE = "mysql-database-config.properties";

    public static Connection getConnection() throws SQLException {
        return DatabaseConfigLoader.getConnection(PROPERTIES_FILE);
    }
}
