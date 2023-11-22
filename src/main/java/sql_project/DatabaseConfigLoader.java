package sql_project;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DatabaseConfigLoader {

    public static Connection getConnection(String databaseType) {

        log("Loading database configuration...");

        Properties properties = loadProperties(databaseType);
        if (properties == null) {
            return null; // Return early if loading properties fails
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");
        String dbName = properties.getProperty("db.database");

        log("Loaded properties - url: " + url + ", username: " + username + ", driver: " + driver + ", password: " + password + ", dbName: " + dbName);

        log("Loading DB driver...");
        if (loadDriver(driver)) {
            return establishConnection(url, username, password, dbName);
        } else {
            return null; // Return null if loading driver fails
        }
    }

    private static void handleSQLException(SQLException e) {
        logError("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }

    private static void handleClassNotFoundException(ClassNotFoundException e) {
        logError("Error: PostgreSQL JDBC Driver not found.");
        e.printStackTrace();
    }

    private static Properties loadProperties(String databaseType) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/" + databaseType)) {
            properties.load(fis);
        } catch (IOException e) {
            logError("Error loading database configuration file: " + e.getMessage());
            return null; // Return null on failure
        }
        return properties;
    }

    private static boolean loadDriver(String driver) {
        try {
            Class.forName(driver);
            return true;
        } catch (ClassNotFoundException e) {
            handleClassNotFoundException(e);
            return false;
        }
    }

    private static Connection establishConnection(String url, String username, String password, String dbName) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (isDatabaseConnected(connection, dbName)) {
                log("Connected to the database!");
                return connection;
            } else {
                log("Database does not exist or connection failed.");
                return null;
            }
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    private static boolean isDatabaseConnected(Connection connection, String dbName) {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();

            while (resultSet.next()) {
                String existingDbName = resultSet.getString("TABLE_CAT");
                if (existingDbName.equals(dbName)) {
                    return true;
                }
            }

            return false;
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    private static void log(String message) {
        System.out.println(message);
    }

    private static void logError(String message) {
        System.err.println(message);
    }
}
