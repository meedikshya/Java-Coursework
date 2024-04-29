package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import configs.DbConnectionConfig;
import datasource.UserRegistrationDataSource;
import utils.StringUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = StringUtils.SERVLET_URL_ADMIN)
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession();
		String currentUser = (String) userSession.getAttribute(StringUtils.USER_NAME);
        String contextPath = request.getContextPath();

        String userRole = "Admin";

        Connection conn = null;

        PreparedStatement roleStmt = null;
        ResultSet roleRs = null;
        try {
        	DbConnectionConfig dbObj = new DbConnectionConfig();
        	conn = dbObj.getDbConnection();
            String roleQuery =UserRegistrationDataSource.GET_USER;
             
            roleStmt = conn.prepareStatement(roleQuery);
            roleRs = roleStmt.executeQuery();

            if (roleRs.next()) {
            	userRole = roleRs.getString("user");
                System.out.println(userRole);
            }

            // Set userRole as an attribute in the request object
            request.setAttribute("userRole", userRole);

            // Forward the request to the JSP
            request.getRequestDispatcher(StringUtils.PAGE_URL_ADMIN_DASHBOARD).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (roleRs != null) roleRs.close();
                if (roleStmt != null) roleStmt.close();
                if (conn != null) conn.close(); // Close connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }	 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
