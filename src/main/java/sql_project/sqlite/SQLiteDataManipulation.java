package sql_project.sqlite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteDataManipulation {

    public static void createTable() {
        try (Connection connection = SQLiteDatabaseConnection.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS your_table_name (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String name, int age) {
        createTable(); // Ensure the table exists

        try (Connection connection = SQLiteDatabaseConnection.getConnection()) {
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

    // You can implement additional methods for updating, deleting, and querying data in SQLite
}