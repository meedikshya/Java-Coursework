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

import configs.DbConnectionConfig;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchquery = request.getParameter("keyword");
    	String productName = request.getParameter("searchName");
        String maxPriceStr = request.getParameter("maxPrice");
        String productNameFound = "";
        double maxPrice = -1; // Default value if maxPrice is not provided
        if (maxPriceStr != null && !maxPriceStr.isEmpty()) {
            try {
                maxPrice = Double.parseDouble(maxPriceStr);
            } catch (NumberFormatException e) {
                // Handle parsing error if necessary
            }
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            String query = "SELECT product_name FROM product WHERE product_name LIKE ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + searchquery + "%");
            rs = stmt.executeQuery();

            if (rs.next()) {
                productNameFound = rs.getString("product_name");
                // Set the found product name as an attribute in the request object
            } else {
                // If no product is found, set an appropriate message
                request.setAttribute("productName", "No product found");
            }
            request.setAttribute("productName", productNameFound);

            // Forward the request to the JSP
            request.getRequestDispatcher("./pages/search.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
