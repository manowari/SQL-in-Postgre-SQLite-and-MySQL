package sql_project.postgre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgreSQLDataManipulation {

    private static final String TABLE_NAME = "your_table_name";

    public static void createTable() {

        System.out.println("creating ..");
        try (Connection connection = PostgreSQLDatabaseConnection.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id SERIAL PRIMARY KEY, name VARCHAR(255), age INTEGER)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String name, int age) {
        createTable(); // Ensure the table exists

        System.out.println("Inserting data: Name - " + name + ", Age - " + age);


        try (Connection connection = PostgreSQLDatabaseConnection.getConnection()) {
            String query = "INSERT INTO " + TABLE_NAME + " (name, age) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for updating, deleting, and querying data in PostgreSQL
}
