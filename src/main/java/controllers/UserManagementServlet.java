package controllers;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserModel;
import services.UserManagementServices;
import utils.StringUtils;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet(urlPatterns = StringUtils.SERVLET_URL_USER_MANAGEMENT, asyncSupported = true)
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final UserManagementServices userManagementServices;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
    	       this.userManagementServices = new UserManagementServices();
    	     }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<UserModel> allUsers = userManagementServices.getAllUsersInfo();

            if (allUsers != null && !allUsers.isEmpty()) {
                System.out.println("Total number of users: " + allUsers.size());
                System.out.println("User list attribute set: " + allUsers);
                for (UserModel user : allUsers) {
                    System.out.println(user);
                }

                request.setAttribute("userList", allUsers);
                request.getRequestDispatcher(StringUtils.PAGE_URL_USER_LIST).forward(request, response);
            } else {
                System.out.println("No users found. Add Some Users");
                // Handle case where no products are found, perhaps display an error message
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
     
        UserModel user = new UserModel(fullName,username,birthday, gender,phoneNumber,address,email,password,userRole);
        
        int result = 0; 
        
        try {
            result = userManagementServices.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (result > 0) {
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.ADD_USER_SUCCESS_MESSAGE);
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_USER_LIST);

            List<UserModel> addedUsers = new ArrayList<>();
            addedUsers.add(user);
            request.setAttribute("addedUsers", addedUsers);
            System.out.println("Added Users ArrayList: " + addedUsers);
        } else {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.ADD_USER_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADD_USER).forward(request, response);
        }
        
    }
    

    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
}
