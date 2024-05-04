package datasource;

public class ProductDatasource {
	public static final String INSERT_PRODUCT = "INSERT INTO products"
			+ "(p_id,name,category,price,image)"
			+"VALUES(?,?,?,?,?)";
	
	public static final String GET_ALL_PRODUCTS = "SELECT * FROM products";
	
	public static final String GET_PRODUCT_ID  = "SELECT count(*) FROM products WHERE p_id = ?";
	
	public static final String GET_PRODUCT_NAME  = "SELECT count(*) FROM products WHERE name = ?";
	
	public static final String GET_PRODUCT_CATEGORY = "SELECT count(*) FROM products WHERE category = ?";
	
	public static final String GET_PRICE  = "SELECT count(*) FROM products WHERE price = ?";
	
	public static final String GET_IMAGE  = "SELECT count(*) FROM products WHERE image = ?";
	
	public static final String DELETE_PRODUCT = "SELECT FROM products WHERE p_id = ?";	
	
	public static final String USER_NAME = "SELECT id FROM users WHERE user_name = ?";
	
	public static final String CART_PRODUCT = "SELECT product_name FROM cart WHERE userId = ?";
	
	

}
