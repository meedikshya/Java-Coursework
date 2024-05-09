package controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import models.UserModel;
import services.UserProfileService;
import utils.StringUtils;
import utils.UserHelper;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_USER_PROFILE_UPDATE)
public class UserProfileUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserProfileService userProfileService;

    public UserProfileUpdateServlet() {
        super();
        this.userProfileService = new UserProfileService();
    }

		
		  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    	String username = UserHelper.getGlobalUser();
				HttpSession us = request.getSession();
				
				 String userIdParam = request.getParameter("userId");
				    int userId = 0; // Default value or error handling if needed

				    // Check if userIdParam is not null or empty before parsing
				    if (userIdParam != null && !userIdParam.isEmpty()) {
				        try {
				            userId = Integer.parseInt(userIdParam);
				        } catch (NumberFormatException e) {
				            // Handle the case where userIdParam is not a valid integer
				            e.printStackTrace(); // Or log the error
				        }
				    }
				
				 UserModel user = UserProfileService.getUserById(userId);
				 
				   request.setAttribute("users", user);
			        request.getRequestDispatcher("/pages/userProfile.jsp").forward(request, response);
				
				System.out.println(userId);
				System.out.println(user);
				
		
      
    }

 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String updateId = request.getParameter(StringUtils.UPDATE_ID);
        if (updateId != null && !updateId.isEmpty()) {
            updateUserProfile(request, response, updateId);
        } else {
            response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
        }
    }

    private void updateUserProfile(HttpServletRequest request, HttpServletResponse response, String updateId) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(updateId);
            String username = request.getParameter(StringUtils.USERNAME);
            String fullName = request.getParameter(StringUtils.FULL_NAME);
            String gender = request.getParameter(StringUtils.GENDER);
            LocalDate birthday = LocalDate.parse(request.getParameter(StringUtils.BIRTHDAY));
            String phoneNumber = request.getParameter(StringUtils.PHONE_NUMBER);
            String email = request.getParameter(StringUtils.EMAIL);
            String address = request.getParameter(StringUtils.ADDRESS);
            String password = request.getParameter(StringUtils.PASSWORD);
            Part imagePart = request.getPart(StringUtils.PROFILE_IMAGE);

            String imageUrlFromPart = ""; // Initialize imageUrlFromPart

            // If imagePart is not null, set the imageUrlFromPart using the UserModel method
            if (imagePart != null) {
                imageUrlFromPart = new UserModel().getImageUrl(imagePart);
            }
            UserModel updatedUser = new UserModel();
            updatedUser.setUserId(userId);
            updatedUser.setUserName(username);
            updatedUser.setFullName(fullName);
            updatedUser.setGender(gender);
            updatedUser.setBirthday(birthday);
            updatedUser.setPhoneNumber(phoneNumber);
            updatedUser.setEmail(email);
            updatedUser.setAddress(address);
            updatedUser.setPassword(password);
            updatedUser.setImageUrlFromDB(imageUrlFromPart);

            int result = userProfileService.updateUserInfo(updatedUser, updateId);
            if (result == 1) {
                response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
            } else {
                response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
            }
        } catch (NumberFormatException ex) {
            response.sendRedirect(request.getContextPath() + StringUtils.URL_INDEX);
        }
    }
}
