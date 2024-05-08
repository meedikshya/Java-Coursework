package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import configs.DbConnectionConfig;
import models.UserModel;
import services.UserManagementServices;
import services.UserProfileService;
import utils.StringUtils;

/**
 * Servlet implementation class UserProfileDeleteServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_USER_PROFILE_DELETE)
public class UserProfileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private final UserProfileService userProfileService; 
	 private final DbConnectionConfig dbObj;
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.userProfileService = new UserProfileService();   
        this.dbObj = new DbConnectionConfig();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String updateId = request.getParameter(StringUtils.UPDATE_ID);
		String deleteId = request.getParameter(StringUtils.DELETE_ID);

		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("put triggered");
		String idParam = req.getParameter(StringUtils.UPDATE_ID);
	    int id = Integer.parseInt(idParam);
	    UserModel user = userProfileService.getUserById(id);
		
	    req.setAttribute("User", user);
		req.getRequestDispatcher(StringUtils.PAGE_URL_USER_PROFILE).forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    System.out.println("delete triggered");
	    try {
	        String deleteIdParam = req.getParameter(StringUtils.DELETE_ID);
	        // Convert the deleteIdParam to an integer if needed
	        int deleteId = Integer.parseInt(deleteIdParam);
	        if (userProfileService.deleteUserInfo(deleteId) == 1) {
	            req.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_DELETE);
	            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
	        } else {
	            req.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_DELETE);
	            resp.sendRedirect(req.getContextPath() + StringUtils.URL_INDEX);
	        }
	    } catch (NumberFormatException e) {
	        // Handle the case where the DELETE_ID parameter cannot be parsed to an integer
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



}