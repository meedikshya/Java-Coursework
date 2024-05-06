package services;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import configs.Dbconnection;
import datasource.ProductDatasource;
import models.ProductModel;

public class ProductServices {
	
	Dbconnection dbObj = new Dbconnection();

    public List<ProductModel> getAllProducts() throws ClassNotFoundException {
    	
        List<ProductModel> productList = new ArrayList<ProductModel>();
        
    	try (Connection conn = dbObj.getDbConnection()){
             PreparedStatement st = conn.prepareStatement(ProductDatasource.GET_ALL_PRODUCTS);
             ResultSet rs = st.executeQuery();

            while (rs.next()) {
            	
            	int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String imageUrlFromPart= rs.getString("images");
                
                //System.out.println("Retrieved product: " + name); 
                
                ProductModel product = new ProductModel(id,name,description ,price,imageUrlFromPart);
                productList.add(product);
            }
            return productList;
            
        } catch (SQLException ex) {
        	
        	ex.printStackTrace();
        	return null;
        	
        }
    }
   
}
