/**
 * 
 */
package datasource;

/**
 * 
 */
public class UserRegistrationDataSource {

	/**
	 * 
	 */
	public static final String INSERT_USER = "INSERT INTO user"
			+ "(user_name, Fullname, Gender, Birthday, Phone_Number, Email, Address, Password)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String GET_LOGIN_USER_INFO = "SELECT * FROM user  WHERE user_name = ?"; 
	public static final String GET_ALL_USER_INFO = "SELECT * FROM user ";
	public static final String GET_USERNAME = "SELECT COUNT(*) FROM user WHERE user_name = ?"; 
	public static final String GET_PHONE = "SELECT COUNT(*) FROM user  WHERE phone_number = ?"; 
	public static final String GET_EMAIL = "SELECT COUNT(*) FROM user  WHERE email = ?";  
	
	// Adding SQL queries to check existence of email, phone number, and username
    
    
    public static final String SELECT_FIRST_NAME  = "SELECT first_name FROM user WHERE user_name = ?";
    public static final String SELECT_LAST_NAME  = "SELECT last_name FROM user WHERE user_name = ?";
	//End SQL Queries
	
	 
	}


