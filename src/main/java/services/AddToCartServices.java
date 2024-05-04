package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import configs.Dbconnection;
import datasource.ProductDatasource;
import models.CartItemModel;
import models.ProductModel;



public class AddToCartServices {
    private ArrayList<CartItemModel> cartItems = new ArrayList<>();

    public void addToCart(ProductModel product, int quantity) {
        // Check if the product already exists in the cart
        for (CartItemModel item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // If product is not in cart, add it as a new item
        cartItems.add(new CartItemModel(product, quantity));
    }

    public ArrayList<CartItemModel> getCartItems() {
        return cartItems;
    }
}
//public class AddToCartServices {
//	
//	Dbconnection dbObj = new Dbconnection();
//	
//	
//	public ArrayList<ProductModel> getAddtoCartDetails(){
//		try(Connection conn = dbObj.getDbConnection()){
//			PreparedStatement st = conn.prepareStatement(ProductDatasource.CART_PRODUCT);
//			ResultSet rs = st.executeQuery();
//			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
//			while (rs.next()) {
//				ProductModel product = new ProductModel();
//				
//				product.setUse
//			}
//			
//		}
//		
//	}
//   // public ArrayList<String> productNames = new ArrayList<>();
//
//    PreparedStatement prodStmt = null;
//    ResultSet prodRs = null;
//    try {
//        conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
//        String prodQuery = "SELECT product_name FROM cart WHERE userId = ?";
//        prodStmt = conn.prepareStatement(prodQuery);
//        prodStmt.setString(1, userRole);
//        prodRs = prodStmt.executeQuery();
//
//        while (prodRs.next()) {
//            String productName = prodRs.getString("product_name");
//            productNames.add(productName);
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } finally {
//        try {
//            if (prodRs != null) prodRs.close();
//            if (prodStmt != null) prodStmt.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
