package datasource;

public class ProductDataSource {
    // Define SQL queries for product-related operations
    public static final String INSERT_PRODUCT = "INSERT INTO product(product_name, Product_Category, unit_price, stock_quantity, product_image) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    public static final String QUERY_DELETE_PRODUCTS= "DELETE FROM product WHERE product_name = ?";
    public static final String COUNT_ALL_PRODUCTS =    "SELECT COUNT(*) as total FROM product";
    public static final String UPDATE_PRODUCTS = "UPDATE product SET product_name=?, Product_Category=?, unit_price=?, stock_quantity=? WHERE product_name=?";
}
