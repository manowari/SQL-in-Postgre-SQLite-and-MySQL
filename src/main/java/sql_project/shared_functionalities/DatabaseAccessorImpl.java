package sql_project.shared_functionalities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessorImpl implements DatabaseAccessor {

    // ... (previous code)
@Override
    public List<String> getAllTables(Connection connection) {
        List<String> tables = new ArrayList<>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }

    @Override
    public List<List<Object>> selectAllData(Connection connection) {
        List<List<Object>> result = new ArrayList<>();

        List<String> tableNames = getAllTables(connection);

        for (String tableName : tableNames) {
            try {
                System.out.println("Checking permissions for table: " + tableName);

                // Check permissions for the table
                if (!hasPermission(connection, tableName)) {
                    System.out.println("Skipping table due to lack of permissions: " + tableName);
                    continue;
                }

                // Retrieve data from each table
                List<List<Object>> tableData = selectAllDataFromTable(connection, tableName);

                // Add table name as a header in the result
                if (!tableData.isEmpty()) {
                    List<Object> header = new ArrayList<>();
                    header.add("Table: " + tableName);
                    result.add(header);
                    result.addAll(tableData);
                }
            } catch (SQLException e) {
                System.err.println("Error checking permissions for table: " + tableName);
                e.printStackTrace();  // Log the exception for debugging
            }
        }

        return result;
    }

    private boolean hasPermission(Connection connection, String tableName) throws SQLException {
        boolean hasPermission = false;
        String currentUser = getCurrentUser(connection);

        try (Statement statement = connection.createStatement()) {
            // Check if the current user has SELECT privilege on the table
            ResultSet resultSet = statement.executeQuery(
                    "SELECT 1 FROM information_schema.table_privileges " +
                            "WHERE table_name = '" + tableName + "' AND grantee = '" + currentUser + "' AND privilege_type = 'SELECT'"
            );

            hasPermission = resultSet.next();
        }

        return hasPermission;
    }

    private String getCurrentUser(Connection connection) throws SQLException {
        // Retrieve the current user in a database-specific way
        // This example assumes PostgreSQL, adjust for other databases
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT current_user");
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                throw new SQLException("Unable to determine the current user");
            }
        }
    }


@Override
    public void createTable(Connection connection, String tableName){

System.out.println("creating");

    try {
        Statement statement;
        //Serial  Auto increment

        String query = "CREATE TABLE" + tableName + "empID SERIAL, name VARCHAR(200), PRIMARY KEY(empID)";
        statement = connection.createStatement();
        statement.executeQuery(query);
        System.out.println("Table Created");

    }catch (Exception e){

    }






    }

    @Override
   public  List<List<Object>> selectAllDataFromTable(Connection connection, String tableName) {
        List<List<Object>> tableData = new ArrayList<>();

        System.out.println(" selecting .... " );

        try {
            String query = "SELECT * FROM " + tableName + ";";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    int columnCount = resultSet.getMetaData().getColumnCount();

                    // Retrieve column names as the first row
                    List<Object> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(resultSet.getMetaData().getColumnName(i));
                    }
                    tableData.add(columnNames);

                    // Retrieve data rows
                    while (resultSet.next()) {
                        List<Object> row = new ArrayList<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.add(resultSet.getObject(i));
                        }
                        tableData.add(row);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableData;
    }

}
