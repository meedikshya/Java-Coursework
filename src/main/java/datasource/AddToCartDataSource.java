package datasource;

public class AddToCartDataSource {
	 public static final String INSERT_CART = "INSERT INTO Cart(username,productId) VALUES (?, ?)";
	   
	 public static final String SELECT_CART ="SELECT * FROM cart where username = ? ";
	 
	 public static final String SELECT_CART_PRODUCT ="SELECT * FROM product where product_id = ? ";
	 
	 
}
