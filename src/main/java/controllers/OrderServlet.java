package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.OrderDetailModel;
import models.OrderModel;
import models.UserProductModel;
import services.AddToCartServices;
import services.OrderServices;
import utils.StringUtilsCart;
import utils.UserHelper;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/OrderServlet" })
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession us = request.getSession();
		String username = UserHelper.getGlobalUser();
		
		List<UserProductModel> allProducts;
		try {
			allProducts = AddToCartServices.getAllCartProducts(username);
			int totalItems = allProducts.size();
			
			float grandTotal = 0.0f;
			for (UserProductModel product : allProducts) {
			 grandTotal += product.getPrice() * product.getQuantity();
			}
			
			if (!allProducts.isEmpty()) {
				 //using loop in allProducts
				 
				 OrderModel orders = new OrderModel();
				 orders.setUsername(username);
				 orders.setTotalItems(totalItems);
				 orders.setGrandTotal(grandTotal);
				 orders.setOrderStatus("Pending");
				 
				 var result = OrderServices.setOrder(orders);
				 
				 	if(result != 0) {
						 for(UserProductModel product : allProducts) {
							 OrderDetailModel orderDetails = new OrderDetailModel();
							 orderDetails.setOrderId(result);
							 orderDetails.setProductId(product.getId());
							 orderDetails.setQuantity(product.getQuantity());
							 orderDetails.setPrice(product.getPrice());
							 orders.setOrderStatus("Pending");

							 
							 var results = OrderServices.setOrderDetails(orderDetails);
						 }		 
				 	}
				 	
				 	var deleteCart = OrderServices.deleteCart(username);
				 
	                request.getRequestDispatcher(StringUtilsCart.ORDER_PAGE).forward(request, response);
	            } else {
	                System.out.println("No products found.");
	                // Handle case where no products are found, perhaps display an error message
	            }
			//response.getWriter().append("Served at: "+allProducts).append(request.getContextPath());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
