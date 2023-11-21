package sql_project;

import sql_project.mysql.MySQLDataManipulation;
import sql_project.mysql.MySQLDatabaseConnection;
import sql_project.postgre.PostgreSQLDataManipulation;
import sql_project.postgre.PostgreSQLDatabaseConnection;
import sql_project.sql.SQLDataManipulation;
import sql_project.sql.SQLDatabaseConnection;
import sql_project.sqlite.SQLiteDataManipulation;
import sql_project.sqlite.SQLiteDatabaseConnection;

public class Main {
    public static void main(String[] args) {
        // Test the database connections
        testDatabaseConnection(SQLDatabaseConnection.class.getName());
        testDatabaseConnection(MySQLDatabaseConnection.class.getName());
        testDatabaseConnection(PostgreSQLDatabaseConnection.class.getName());
        testDatabaseConnection(SQLiteDatabaseConnection.class.getName());

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

        if(dbName!=null){

            try {
                Class.forName(dbName).getMethod("getConnection").invoke(null);
                System.out.println(dbName + " database connection established successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {

            System.out.println("db empty");

        }


    }
}
