package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import configs.DbConnectionConfig;
import models.ProductModel;
import models.AddToCartModel;
import models.OrderDetailModel;
import models.OrderModel;
import utils.StringUtils;

/**
 * Servlet implementation class UploadCartServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final DbConnectionConfig dbController;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
		this.dbController = new DbConnectionConfig();
        
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession userSession = request.getSession();
	 	   String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
	 	   int odr = 0;
	 	   String productids = request.getParameter("productid");
	 	   int idd = 0;
	 	    Connection conn = null; // Declare the Connection variable only once
	 	   PreparedStatement usid = null;
	         ResultSet usrs = null;
	         
	         PreparedStatement ords = null;
	         ResultSet ordrs = null;

	         
	 	   try {
	 		  conn = dbController.getDbConnection();
	 	        
	 	       usid = conn.prepareStatement("SELECT user_id from user where user_name = ?");
	 	       usid.setString(1,currentUser);
	           usrs = usid.executeQuery();
	           
	           if(usrs.next()) {
	        	   idd = usrs.getInt("user_id");
	           } 
	           ords = conn.prepareStatement("SELECT COUNT(orderID) AS orders FROM orders WHERE userID = ?");
	           ords.setInt(1, idd);
	           ordrs = ords.executeQuery();

	           if (ordrs.next()) {
	               odr = ordrs.getInt("orders");
	           }
	 	   }catch (SQLException | ClassNotFoundException  e) {
	 	        e.printStackTrace();
	 	    } finally {
	 	        try {
	 	            if (usrs != null) usrs.close();
	 	            if (usid != null) usid.close();
	 	        } catch (SQLException e) {
	 	            e.printStackTrace();
	 	        }
	 	    }   
		 	String orderID = (odr+1) + currentUser;

	 	   String userId = String.valueOf(idd);
	        // Check if product_name is not null
	        if (userId != null) {
	            // Create a CartModel object with the retrieved parameters
	            OrderModel order = new OrderModel(orderID,userId);
	            
	            // Call the DBController to add the cart to the database
	            int result = dbController.setOrder(order);
	            
	            

		         // Remove the square brackets
		         String cleanString = productids.substring(1, productids.length() - 1);
	
		         // Split the string by commas
		         String[] elements = cleanString.split(",");
	
		         // Convert the string values to integers
		         int[] productid = new int[elements.length];
		         for (int i = 0; i < elements.length; i++) {
		             productid[i] = Integer.parseInt(elements[i].trim());
		             String prodid = String.valueOf(productid[i]);
		             OrderDetailModel odm = new OrderDetailModel(orderID,prodid);
			            
			          int res = dbController.setOrderDetail(odm);
		         }
	
		         

	            
	            // Handle the result accordingly
	            if (result == 1) {
	                request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.MESSAGE_SUCCESS_REGISTER);
	                response.sendRedirect("./IndexServlet");
	            } else if (result == 0) {
	                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_REGISTER);
	                request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            } else {
	                request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.MESSAGE_ERROR_SERVER);
	                request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	            }
	        } else {
	            // Handle case where product_name is null
	            request.setAttribute(StringUtils.MESSAGE_ERROR, "Product name cannot be null.");
	            request.getRequestDispatcher(StringUtils.PAGE_URL_REGISTER).forward(request, response);
	        }
		}




}