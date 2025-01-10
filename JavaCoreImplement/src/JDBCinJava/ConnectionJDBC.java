package JDBCinJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionJDBC {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password";

        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

// Hoặc sử dụng PreparedStatement
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
    preparedStatement.setInt(1, 1);


    //đóng lại khi ko sử dụng

        statement.close();
        connection.close();

    }
}
