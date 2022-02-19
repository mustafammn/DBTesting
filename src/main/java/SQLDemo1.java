import com.mysql.cj.xdevapi.Result;

import java.sql.*;

public class SQLDemo1 {
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

        try {
            connection = DriverManager.getConnection(dBUrl, userName, password);
            statement = connection.createStatement();
            result = statement.executeQuery("select * from person");
            while (result.next()){
                System.out.println(result.getString("id")+" "+result.getString("firstname")
                        +" "+result.getString("lastname")+" "+result.getString("age")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.closeResultSet(result);
            DBUtils.closeStatement(statement);
            DBUtils.closeConnection(connection);

        }

    }


}