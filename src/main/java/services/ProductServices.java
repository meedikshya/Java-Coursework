package services;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.Part;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import configs.Dbconnection;
//import controller.List;
import datasource.ProductDataSource;
import models.ProductModel;

public class ProductServices {

    private final Dbconnection dbObj = new Dbconnection();

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
            ex.printStackTrace();
            return -1;
        }
    }
    
    public List<ProductModel> getAllProducts() throws ClassNotFoundException {
        List<ProductModel> productList = new ArrayList<>();
        try {
            System.out.println("Fetching list of products...");
            Connection conn = dbObj.getDbConnection();
            PreparedStatement st = conn.prepareStatement(ProductDataSource.SELECT_ALL_PRODUCTS);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String name = rs.getString("product_name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String imageUrl = rs.getString("image");
                
                ProductModel product = new ProductModel(name, category, price, quantity, imageUrl);
                productList.add(product);
            }

            System.out.println("List of products: " + productList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return productList;
    }

}
