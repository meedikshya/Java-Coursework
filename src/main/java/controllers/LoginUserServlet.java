package controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserLoginModel;
import services.UserServices;
import utils.StringUtils;
import utils.ValidationUtil;

@WebServlet(urlPatterns = StringUtils.SERVLET_URL_LOGIN, asyncSupported = true)
public class LoginUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final UserServices userServices;

    public LoginUserServlet() {
        this.userServices = new UserServices();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter(StringUtils.USERNAME);
        String password = request.getParameter(StringUtils.PASSWORD);
        String email = request.getParameter(StringUtils.EMAIL);

     // Implement data validation here
        if   !ValidationUtil.isAlphanumeric(userName) || 
                !ValidationUtil.isEmail(email) ||
				!ValidationUtil.isNumbersOnly(password) 
				 ) {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_INCORRECT_DATA);
            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
            return; // Stop further execution
        }
        UserLoginModel userLoginModel = new UserLoginModel(userName,email, password);

        int loginResult = userServices.getUserLoginInfo(userLoginModel);

        if (loginResult == 1) {
            HttpSession userSession = request.getSession();
            userSession.setAttribute(StringUtils.USERNAME, userName);
            userSession.setMaxInactiveInterval(30*60);
            
            Cookie userCookie= new Cookie(StringUtils.USER, userName);
            userCookie.setMaxAge(30*60);
            response.addCookie(userCookie);
            
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_LOGIN);
            response.sendRedirect(request.getContextPath() + StringUtils.PAGE_URL_WELCOME);
        } else if (loginResult == 0) {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_LOGIN);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else if (loginResult == -1) {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_CREATE_ACCOUNT);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        } else {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
            request.setAttribute(StringUtils.USERNAME, userName);
            request.getRequestDispatcher(StringUtils.PAGE_URL_LOGIN).forward(request, response);
        }
    }
}
