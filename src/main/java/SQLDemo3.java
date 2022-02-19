import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLDemo3 {
    public static void main(String[] args) {
        String dBUrl = "jdbc:mysql://3.237.189.167:3306/syntaxhrm_mysql";
        String userName = "syntax_hrm";
        String password = "syntaxhrm123";
        Connection connection = null;
        //variable to hold the connection
        Statement statement = null;
        // helps us execute the queries on the database
        ResultSet result = null;
        // helps us store the results
        ResultSetMetaData resultSetMetaData = null;
        //object to give the information about table and its data
        try {
            connection = DriverManager.getConnection(dBUrl, userName, password);
            //getting the database connection from  driverManager class
            statement = connection.createStatement();
            // creating a statement to execute the queries
            result = statement.executeQuery("select * from person");
            // executing the query and storing the results in Resulset
            resultSetMetaData = result.getMetaData();
            //getting the resultSet object so that we fetch the column names and other info related to the table
            List<Map<String,String>> tableData=new ArrayList<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.print(resultSetMetaData.getColumnName(i) + " ");
                //printing the column names
            }
            System.out.println();
            while (result.next()) {
                Map<String,String> row=new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    //gets the column name passes it to teh getString method to get the data for that column using loops
                    row.put(resultSetMetaData.getColumnName(i),result.getString(resultSetMetaData.getColumnName(i)));
                }
                tableData.add(row);
            }
            System.out.println(tableData);
            // 11:50;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeResultSet(result);
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);

        }

    }
}
