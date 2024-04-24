package configs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Dbconnection {
    public Connection getDbConnection() throws SQLException {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/o'clock";
            String userName = "root";
            String password = "";
            
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Failed connection to the database");
        }
        return conn;
    }
}
