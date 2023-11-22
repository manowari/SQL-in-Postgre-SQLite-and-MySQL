package sql_project.setups;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseSetup {

    public static void main(String[] args) {
        createPostgreSQLDatabase();
        createMySQLDatabase();
        createSQLiteDatabase();
        createSQLDatabase(); // Added for SQL Server
    }

    public static void createPostgreSQLDatabase() {
        createDatabase("postgresql");
    }

    public static void createMySQLDatabase() {
        createDatabase("mysql");
    }

    public static void createSQLiteDatabase() {
        createDatabase("sqlite");
    }

    public static void createSQLDatabase() {
        createDatabase("sql");
    }

    private static void createDatabase(String databaseType) {
        Properties properties = new Properties();
        String propertiesFile = "src/main/resources/" + databaseType + "-database-config.properties";

        System.out.println("propertiesFile path " + propertiesFile);

        try (FileInputStream fis = new FileInputStream(propertiesFile)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading database configuration file: " + e.getMessage());
            return;
        }

        System.out.println("properties  " + properties);


        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");
        String databaseName = databaseType;

        if (url == null || username == null || password == null || driver == null || databaseName == null) {
            System.err.println("Database configuration is incomplete. Ensure url, username, password, driver, and name are provided for " + databaseType);
            return;
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (databaseExists(connection, databaseName)) {
                System.out.println("Database already exists for " + databaseType);
                return; // Skip creation
            }

            Statement statement = connection.createStatement();

            // Customize the database creation SQL based on the database type
            String createDatabaseSQL = "";
            switch (databaseType) {
                case "postgresql":
                    createDatabaseSQL = "CREATE DATABASE " + databaseName;
                    break;
                case "mysql":
                    createDatabaseSQL = "CREATE DATABASE " + databaseName;
                    break;
                case "sql":
                    createDatabaseSQL = "CREATE DATABASE " + databaseName;
                    break;
                case "sqlite":
                    // SQLite doesn't have a separate database creation step
                    break;
                default:
                    System.err.println("Unsupported database type: " + databaseType);
                    return;
            }

            statement.executeUpdate(createDatabaseSQL);

            System.out.println("Database created successfully for " + databaseType);
        } catch (SQLException e) {
            System.err.println("Error creating/checking database for " + databaseType + ": " + e.getMessage());
        }
    }

    private static boolean databaseExists(Connection connection, String databaseName) throws SQLException {
        // Check if the database already exists
        String checkDatabaseSQL = "SELECT 1 FROM pg_database WHERE datname='" + databaseName + "'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(checkDatabaseSQL)) {
            return resultSet.next();
        }
    }
}
