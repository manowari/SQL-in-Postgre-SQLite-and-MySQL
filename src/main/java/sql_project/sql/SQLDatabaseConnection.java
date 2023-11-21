package sql_project.sql;


 import sql_project.DatabaseConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDatabaseConnection {
    private static final String PROPERTIES_FILE = "sql-database-config.properties";

    public static Connection getConnection() throws SQLException {
        return DatabaseConfigLoader.getConnection(PROPERTIES_FILE);
    }
}
