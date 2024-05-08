package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import utils.StringUtils;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession userSession = request.getSession();
        String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
        String contextPath = request.getContextPath();
        String userproduct = request.getParameter("keyword");
        String userRole = "";

        Connection conn = null;

        PreparedStatement roleStmt = null;
        ResultSet roleRs = null;
        try {
        	DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            String roleQuery = "SELECT product_name from product where product_name LIKE ?";
            roleStmt = conn.prepareStatement(roleQuery);
            roleStmt.setString(1,"%" +  userproduct + "%");
            roleRs = roleStmt.executeQuery();

            if (roleRs.next()) {
            	userRole = roleRs.getString("product_name");
                System.out.println(userRole);
            }

            // Set userRole as an attribute in the request object
            request.setAttribute("userRole", userRole);

            // Forward the request to the JSP
            request.getRequestDispatcher("./pages/search.jsp").forward(request, response);
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

}