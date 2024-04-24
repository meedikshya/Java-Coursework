package datasource;

public class ProductDataSource {
    // Define SQL queries for product-related operations
    public static final String INSERT_PRODUCT = "INSERT INTO product(product_name, Product_Category, unit_price, stock_quantity, product_image) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
}
