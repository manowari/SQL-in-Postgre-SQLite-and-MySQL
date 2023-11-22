package sql_project.shared_functionalities;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DatabaseAccessor {
//    List<String> executeParameterizedQuery(Connection connection, String query, String parameter) throws SQLException;


    List<List<Object>> selectAllDataFromTable(Connection connection, String tableName);
     List<String> getAllTables(Connection connection);
    public List<List<Object>> selectAllData(Connection connection);

    public void createTable(Connection connection, String tableName);
    public void dropTable(Connection connection, String tableName);

}
