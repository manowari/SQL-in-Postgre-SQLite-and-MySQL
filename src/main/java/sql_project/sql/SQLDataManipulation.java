package sql_project.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDataManipulation {

    public static void createTable(String tableName, String createTableQuery) {
        try (Connection connection = SQLDatabaseConnection.getConnection()) {
            String query = String.format("CREATE TABLE IF NOT EXISTS %s %s", tableName, createTableQuery);
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(String tableName, String[] columnNames, Object[] values, int age) {
        if (columnNames.length != values.length) {
            throw new IllegalArgumentException("Column names and values must have the same length.");
        }

        try (Connection connection = SQLDatabaseConnection.getConnection()) {
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();

            for (int i = 0; i < columnNames.length; i++) {
                columns.append(columnNames[i]);
                placeholders.append("?");

                if (i < columnNames.length - 1) {
                    columns.append(", ");
                    placeholders.append(", ");
                }
            }

            String query = String.format("INSERT INTO %s (%s, age) VALUES (%s, ?)", tableName, columns.toString(), placeholders.toString());

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (int i = 0; i < values.length; i++) {
                    preparedStatement.setObject(i + 1, values[i]);
                }

                // Set the age parameter
                preparedStatement.setInt(values.length + 1, age);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Additional methods for updating, deleting, and querying data in a generic SQL manner
}
