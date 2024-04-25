package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import models.PasswordEncryptionWithAes;
import models.UserModel;
import utils.StringUtils;

public class UserManagementServices {
	//making the object to create connection with the database
		DbConnectionConfig dbObj = new DbConnectionConfig();
		/**
		 * Establishes a connection to the database using pre-defined credentials and driver information.
		 * 
		 * @return A `Connection` object representing the established connection to the database.
		 * @throws SQLException if a database access error occurs.
		 * @throws ClassNotFoundException if the JDBC driver class is not found.
		 */
		public Connection getConnection() throws SQLException, ClassNotFoundException {

		    // Load the JDBC driver class specified by the StringUtils.DRIVER_NAME constant
		    Class.forName(StringUtils.DRIVER_NAME);

		    // Create a connection to the database using the provided credentials
		    return DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME,
		                                      StringUtils.LOCALHOST_PASSWORD);
		}
		
		public int addUser(UserModel user) throws ClassNotFoundException {
	        try (Connection conn = dbObj.getDbConnection();
	             PreparedStatement st = conn.prepareStatement(UserRegistrationDataSource.INSERT_USER)) {

	        	// Set the user information in the prepared statement
		        st.setString(1, user.getUserName()); // Assuming 'user_name' corresponds to 'username'
		        st.setString(2, user.getFullName());  
		        st.setString(3, user.getGender());
		        st.setDate(4, Date.valueOf(user.getDob()));
		        st.setString(5, user.getphoneNumber());
		        st.setString(6, user.getEmail());
		        st.setString(7, user.getAddress());
		        st.setString(8, PasswordEncryptionWithAes.encrypt(user.getUserName(), user.getPassword())); // Encrypt the password
		        st.setString(9, user.getUserRole() !=null ? user.getUserRole() : StringUtils.DEFAULT_USER_ROLE);
		        
	            int result = st.executeUpdate();
	            return result > 0 ? 1 : 0;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return -1;
	        }
	    }
		
		//adding users
		 public  List<UserModel> getAllUsersInfo() {
			  List<UserModel> users = new ArrayList<>();
			  try {
				  System.out.println("Fetching list of users...");
				  Connection conn = dbObj.getDbConnection();
			      PreparedStatement st  = conn.prepareStatement(UserRegistrationDataSource.GET_ALL_USER_INFO);
		            ResultSet rs = st .executeQuery();
		             
		            while (rs.next()) {
		            	String fullName = rs.getString(StringUtils.FULL_NAME);
		    	        String username = rs.getString(StringUtils.USERNAME);
		    	        LocalDate birthday =  rs.getDate(StringUtils.BIRTHDAY).toLocalDate();
		    	        String gender = rs.getString(StringUtils.GENDER);
		    	        String phoneNumber = rs.getString(StringUtils.PHONE_NUMBER);
		    	        String address = rs.getString(StringUtils.ADDRESS);
		    	        String email = rs.getString(StringUtils.EMAIL);
		    	        String password = rs.getString(StringUtils.PASSWORD);
		    	        //String confirmPassword = rs.getString(StringUtils.RETYPE_PASSWORD);
		    	        String userRole = rs.getString(StringUtils.USERROLE);
		    	      
		                // Set other attributes accordingly if needed
		    	        UserModel user = new UserModel(fullName,username,birthday,gender,phoneNumber,address,email,password, userRole);
		                users.add(user);
		            }
		            System.out.println("List of users: " + users);
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		            
		        }
		        return users;
		 }		 
}
