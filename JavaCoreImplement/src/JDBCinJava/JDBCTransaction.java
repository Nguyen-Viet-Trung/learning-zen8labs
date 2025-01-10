package JDBCinJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class JDBCTransaction {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "password";
        Connection conn = DriverManager.getConnection(url, user, password);
        Savepoint savepoint1 = conn.setSavepoint("Savepoint1");
    try{
   //Assume a valid connection object conn
   conn.setAutoCommit(false);
   Statement stmt = conn.createStatement();
   //set a Savepoint
   
   String SQL = "INSERT INTO Employees  " +
                "VALUES (106, 20, 'Rita', 'Tez')";
   stmt.executeUpdate(SQL);  
   //Submit a malformed SQL statement that breaks
   String SQL2 = "INSERTED IN Employees  " + "VALUES (107, 22, 'Sita', 'Singh')";
   stmt.executeUpdate(SQL2);
   // If there is no error.
   conn.commit();
    }catch(SQLException se){
   // If there is any error.
        conn.rollback(savepoint1);
    }
}
}
