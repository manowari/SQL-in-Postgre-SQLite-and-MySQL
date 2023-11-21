package sql_project.postgre;


import sql_project.DatabaseConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLDatabaseConnection {
    private static final String PROPERTIES_FILE = "postgresql-database-config.properties";

    public static Connection getConnection() throws SQLException {
        return DatabaseConfigLoader.getConnection(PROPERTIES_FILE);
    }
}
