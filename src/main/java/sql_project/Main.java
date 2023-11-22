package sql_project;

import sql_project.mysql.MySQLDataManipulation;
import sql_project.mysql.MySQLDatabaseConnection;
import sql_project.postgre.PostgreSQLDataManipulation;
import sql_project.postgre.PostgreSQLDatabaseConnection;
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
    private static final String psqlProperties = "postgresql-database-config.properties";


    public static void main(String[] args) throws SQLException {



  // retrieveData(PostgreSQLDatabaseConnection.getConnection());

String className = PostgreSQLDatabaseConnection.class.getName();

Connection connection = getConnection(className);


        try{
            DatabaseAccessor databaseAccessor = new DatabaseAccessorImpl();
            //
            databaseAccessor.dropTable( DatabaseConfigLoader.getConnection(psqlProperties), "table1");

        }catch (Exception e){

        }





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

    static Connection  getConnection(String classStr ){

        Connection connection = null;
try {
    connection= (Connection) Class.forName(classStr).getMethod("getConnection").invoke(null);
    System.out.println(classStr + " database connection established successfully.");

return connection;
}catch (Exception e){
    e.printStackTrace();

}
        System.out.println(" "+connection);

        return connection;
    }

    private static void handleSQLException(SQLException e) {
        System.out.println("SQL Error: " + e.getMessage());
        e.printStackTrace();
    }

}