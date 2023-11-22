package sql_project;

import sql_project.mysql.MySQLDataManipulation;
import sql_project.mysql.MySQLDatabaseConnection;
import sql_project.postgre.PostgreSQLDataManipulation;
import sql_project.postgre.PostgreSQLDatabaseConnection;
import sql_project.setups.DatabaseSetup;
import sql_project.sql.SQLDataManipulation;
import sql_project.sql.SQLDatabaseConnection;
import sql_project.sqlite.SQLiteDataManipulation;
import sql_project.sqlite.SQLiteDatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
//        // Setup databases
//        DatabaseSetup.createPostgreSQLDatabase();
//        DatabaseSetup.createMySQLDatabase();
//        DatabaseSetup.createSQLiteDatabase();
//        DatabaseSetup.createSQLDatabase();

        // Test the database connections
//        testDatabaseConnection(SQLDatabaseConnection.class.getName());
//        testDatabaseConnection(MySQLDatabaseConnection.class.getName());
//        testDatabaseConnection(SQLiteDatabaseConnection.class.getName());


        retrieveData(DatabaseConfigLoader.getConnection("postgresql-database-config.properties"));


        // Test the insert operations
        String[] columns = {"column_name"};
        Object[] values = {'a'};

        // Test the insert operations
        SQLDataManipulation.insertData("John Doe", columns, values, 25);
        MySQLDataManipulation.insertData("Jane Smith", 30);
        PostgreSQLDataManipulation.insertData("Bob Johnson", 28);
        SQLiteDataManipulation.insertData("Alice Brown", 22);
    }

    private static void testDatabaseConnection(String dbName) {
        if (dbName != null) {
            try {
                Class.forName(dbName).getMethod("getConnection").invoke(null);
                System.out.println(dbName + " database connection established successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("db empty");
        }
    }

    private static void retrieveData(Connection connection) {
        String selectQuery = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Age: " + age);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private static void handleSQLException(SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }

}
