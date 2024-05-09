package datasource;

public class OrderDataSource {
	public static final String INSERT_ORDERS= "INSERT INTO orders (username,grandTotal,totalItems,orderStatus) VALUES (?,?,?,?)";
	
	 public static final String ORDER_DETAILS= "INSERT INTO order_details (orderId, productId,price,quantity) VALUES (?, ?, ?,?)";
}
