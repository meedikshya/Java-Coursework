package utils;

public class StringUtils {
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String LOCALHOST_URL ="jdbc:mysql://localhost:3306/o'clock";
	public static final String LOCALHOST_USERNAME = "root";
	public static final String LOCALHOST_PASSWORD = "";
	
	// Start: Parameter names
		public static final String USERNAME = "username";
		public static final String USER_NAME = "user_name";
		public static final String FULL_NAME = "fullName";
		public static final String BIRTHDAY = "dob";
		public static final String GENDER = "gender";
		public static final String EMAIL = "email";
		public static final String PHONE_NUMBER = "phoneNumber";
		public static final String ADDRESS = "address";
		public static final String USERROLE = "UserRole";
		public static final String PASSWORD = "password";
		public static final String RETYPE_PASSWORD = "confirm-password";
		public static final String DEFAULT_USER_ROLE = "User";
		
		// End: Parameter names

		// Start: Validation Messages
		// RegisterUserServlet Page Messages
		public static final String MESSAGE_SUCCESS_REGISTER = "Successfully Registered!";
		public static final String MESSAGE_ERROR_REGISTER = "Please correct the form data.";
		public static final String MESSAGE_ERROR_USERNAME = "Username is already registered.";
		public static final String MESSAGE_ERROR_EMAIL = "Email is already registered.";
		public static final String MESSAGE_ERROR_PHONE_NUMBER = "Phone number is already registered.";
		public static final String MESSAGE_ERROR_PASSWORD_UNMATCHED = "Password is not matched.";
		public static final String MESSAGE_ERROR_INCORRECT_DATA = "Please correct all the fields.";
		 
		// Login Page Messages
		public static final String MESSAGE_SUCCESS_LOGIN = "Successfully LoggedIn!";
		public static final String MESSAGE_ERROR_LOGIN = "Either username or password is not correct!";
		public static final String MESSAGE_ERROR_CREATE_ACCOUNT = "Account for this username is not registered! Please create a new account.";

		// Other Messages
		public static final String MESSAGE_ERROR_SERVER = "An unexpected server error occurred.";
		public static final String MESSAGE_SUCCESS_DELETE = "Successfully Deleted!";
		public static final String MESSAGE_ERROR_DELETE = "Cannot delete the user!";

		public static final String MESSAGE_SUCCESS = "successMessage";
		public static final String MESSAGE_ERROR = "errorMessage";
		
		public static final String ADD_USER_SUCCESS_MESSAGE ="User Added Successfully..";
		public static final String ADD_USER_ERROR_MESSAGE ="Users failed to add...";
		// End: Validation Messages

		// Start: JSP Route
		public static final String PAGE_URL_LOGIN = "/pages/login.jsp";
		public static final String PAGE_URL_REGISTER = "/pages/register.jsp";
		public static final String PAGE_URL_WELCOME = "/pages/welcome.jsp";
		public static final String PAGE_URL_FOOTER = "pages/footer.jsp";
		public static final String PAGE_URL_HEADER = "pages/header.jsp";
		public static final String URL_LOGIN = "/login.jsp";
		public static final String URL_INDEX =  "/index.jsp";
		public static final String PAGE_URL_ADMIN_DASHBOARD = "/pages/adminDashboard.jsp";
		public static final String PAGE_URL_ADD_USER = "/pages/addUser.jsp";
		public static final String PAGE_URL_USER_LIST = "/pages/userList.jsp";

		// End: JSP Route

		// Start: Servlet Route
		public static final String SERVLET_URL_LOGIN = "/LoginUserServlet";
		public static final String SERVLET_URL_REGISTER = "/RegisterUserServlet";
		public static final String SERVLET_URL_LOGOUT = "/LogoutUserServlet";
		public static final String SERVLET_URL_HOME = "/HomeServlet";
		public static final String SERVLET_URL_USER_MANAGEMENT = "/UserManagementServlet";

		// End: Servlet Route

		// Start: Normal Text
		public static final String USER = "user";
		public static final String SUCCESS = "success";
		public static final String TRUE = "true";
		public static final String JSESSIONID = "JSESSIONID";
		public static final String LOGIN = "Login";
		public static final String LOGOUT = "Logout";
		public static final String USER_MODEL = "UserModel";
		public static final String USER_LISTS = "userLists";
		public static final String SLASH= "/";
		public static final String DELETE_ID= "deleteId";
		public static final String UPDATE_ID= "updateId";
		// End: Normal Text
		 
}
