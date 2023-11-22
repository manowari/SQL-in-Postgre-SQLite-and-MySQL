package sql_project;

import sql_project.mysql.MySQLDataManipulation;
import sql_project.mysql.MySQLDatabaseConnection;
import sql_project.postgre.PostgreSQLDataManipulation;
import sql_project.postgre.PostgreSQLDatabaseConnection;
import sql_project.setups.DatabaseSetup;
import sql_project.shared_functionalities.DatabaseAccessor;
import sql_project.shared_functionalities.DatabaseAccessorImpl;
import sql_project.sql.SQLDataManipulation;
import sql_project.sql.SQLDatabaseConnection;
import sql_project.sqlite.SQLiteDataManipulation;
import sql_project.sqlite.SQLiteDatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        // Setup databases
//        DatabaseSetup.createPostgreSQLDatabase();
//        DatabaseSetup.createMySQLDatabase();
//        DatabaseSetup.createSQLiteDatabase();
//        DatabaseSetup.createSQLDatabase();

        // Test the database connections
//        testDatabaseConnection(SQLDatabaseConnection.class.getName());
//        testDatabaseConnection(MySQLDatabaseConnection.class.getName());
//        testDatabaseConnection(SQLiteDatabaseConnection.class.getName());


        retrieveData(PostgreSQLDatabaseConnection.getConnection());


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
        DatabaseAccessor databaseAccessor = new DatabaseAccessorImpl();

        List<List<Object>> allTablesData = databaseAccessor.selectAllData(connection);

// Process the results as needed
        for (List<Object> tableData : allTablesData) {
            for (Object rowData : tableData) {
                System.out.println(rowData);
            }
        }


    }

    private static void handleSQLException(SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }

}