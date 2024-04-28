package services;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configs.Dbconnection;
import datasource.ProductDataSource;
import models.ProductModel;

public class ProductServices {

    private final static Dbconnection dbObj = new Dbconnection();

    public int addProduct(ProductModel product) throws ClassNotFoundException {
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(ProductDataSource.INSERT_PRODUCT)) {

            st.setString(1, product.getName());
            st.setString(2, product.getCategory());
            st.setDouble(3, product.getPrice());
            st.setInt(4, product.getQuantity());
            st.setString(5, product.getImageUrlFromPart());

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            System.err.println("Error adding product: " + ex.getMessage());
            return -1;
        }
    }
    
    public List<ProductModel> getAllProducts() throws ClassNotFoundException {
        List<ProductModel> productList = new ArrayList<>();
        try (Connection conn = dbObj.getDbConnection();
             PreparedStatement st = conn.prepareStatement(ProductDataSource.SELECT_ALL_PRODUCTS);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("product_name");
                String category = rs.getString("Product_Category");
                double price = rs.getDouble("unit_price");
                int quantity = rs.getInt("stock_quantity");
                String imageUrl = rs.getString("product_image");
                
                System.out.println("Retrieved product: " + name); 
                
                ProductModel product = new ProductModel(name, category, price, quantity, imageUrl);
                productList.add(product);
            }
            System.out.println("List of products: " + productList.size());
        } catch (SQLException ex) {
            System.err.println("Error retrieving products: " + ex.getMessage());
        }
        return productList;
    }
    public static int deleteProduct(String productId) throws ClassNotFoundException {
        try (Connection con = dbObj.getDbConnection();
             PreparedStatement st = con.prepareStatement(ProductDataSource.QUERY_DELETE_PRODUCTS)) {
            st.setString(1, productId);
            return st.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error deleting product: " + ex.getMessage());
            return -1;
        }
    }
}