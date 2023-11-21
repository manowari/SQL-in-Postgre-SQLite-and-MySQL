package sql_project;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfigLoader {
    public static Connection getConnection(String databaseType) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/" + databaseType)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading database configuration file: " + e.getMessage());
            return null;
        }

        System.out.println(properties);
        System.out.println(properties.getProperty("url"));

        String url = properties.getProperty("db.url"); // Corrected property name
        String username = properties.getProperty("db.user"); // Corrected property name
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        if (url == null || username == null || password == null || driver == null) {
            System.err.println("Database configuration is incomplete. Ensure url, username, password, and driver are provided for " + databaseType);
            return null;
        }

        try {
            // Load the JDBC driver class
            Class.forName(driver);

            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error establishing database connection." + "e:"+ e.getMessage() + " for " + databaseType);
            return null;
        }
    }
}

