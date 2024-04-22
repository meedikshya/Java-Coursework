package configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionConfig {
	public Connection getDbConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/o'clock";
			String userName = "root";
			String password = "";
			
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException ex) {
            // JDBC driver not found, handle the error
            ex.printStackTrace();
            throw new SQLException("JDBC driver not found", ex);
        } catch (SQLException ex) {
            // Error occurred while establishing the connection, handle it
            ex.printStackTrace();
            throw ex; // Rethrow the SQLException to indicate connection failure
        }
		return conn;
	}
}
