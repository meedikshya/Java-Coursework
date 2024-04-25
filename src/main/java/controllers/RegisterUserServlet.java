package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserLoginModel;
import models.UserModel;
import utils.StringUtils;
import utils.ValidationUtil;
import services.UserServices;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_REGISTER })
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final UserServices userServices; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
    	this.userServices = new UserServices();// Initialize UserServices object
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Extract user information from request parameters
	        String fullName = request.getParameter(StringUtils.FULL_NAME);
	        String username = request.getParameter(StringUtils.USERNAME);
	        LocalDate birthday = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
	        String gender = request.getParameter(StringUtils.GENDER);
	        String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
	        String address = request.getParameter(StringUtils.ADDRESS);
	        String email = request.getParameter(StringUtils.EMAIL);
	        String password = request.getParameter(StringUtils.PASSWORD);
	        String confirmPassword = request.getParameter(StringUtils.RETYPE_PASSWORD);
	        String userRole = request.getParameter(StringUtils.USERROLE);
	     

	     // Validate if password and confirm password match
	        if (!password.equals(confirmPassword)) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_PASSWORD_UNMATCHED);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            return;
	        }
	        
	        // Implement data validation for all fields
	        if (!ValidationUtil.isTextOnly(fullName) || !ValidationUtil.isAlphanumeric(username) || 
	                !ValidationUtil.isEmail(email) || 
	                !ValidationUtil.isNumbersOnly(phoneNumber) ||
	                !ValidationUtil.isGenderMatches(gender) ||
	                !ValidationUtil.isTextOnly(address) 
	                 ) {  
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            return; // Stop further execution
	        }
	        
	     //Call UserService to register the user with Create a UserModel object to hold user information 
	        UserModel user = new UserModel();
	        user.setFullName(fullName);
	        user.setDob(birthday);
	        user.setGender(gender);
	        user.setPhoneNumber(phoneNumber);
	        user.setEmail(email);
	        user.setAddress(address);
	        user.setUserName(username);
	        user.setPassword(password);
	        user.setUserRole(userRole);
	         
	         
	        // Call UserService to register the user
	        int result = userServices.getUserRegisterInfo(user);
	        System.out.println(result);
	        if (result == 1) {
	        	 
	            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
	            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_LOGIN + "?success=true");
	            
	            
	        } else if (result == 0) {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	        } else {
	            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	        }
	    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
