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
            System.out.println("Table: " + tableName);

            // Retrieve data from each table
            List<List<Object>> tableData = selectAllDataFromTable(connection, tableName);

            // Add table name as a header in the result
            if (!tableData.isEmpty()) {
                List<Object> header = new ArrayList<>();
                header.add("Table: " + tableName);
                result.add(header);
                result.addAll(tableData);
            }
        }

        return result;
    }


    @Override
   public  List<List<Object>> selectAllDataFromTable(Connection connection, String tableName) {
        List<List<Object>> tableData = new ArrayList<>();

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
