package sql_project.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLDataManipulation {

    public static void createTable() {
        try (Connection connection = MySQLDatabaseConnection.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS your_table_name (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String name, int age) {
        createTable(); // Ensure the table exists

        try (Connection connection = MySQLDatabaseConnection.getConnection()) {
            String query = "INSERT INTO your_table_name (name, age) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for updating, deleting, and querying data in MySQL
}
