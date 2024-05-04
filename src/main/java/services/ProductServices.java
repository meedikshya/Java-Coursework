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
    	
//        List<ProductModel> productList = new ArrayList<ProductModel>();
        
    	try (Connection conn = dbObj.getDbConnection()){
             PreparedStatement st = conn.prepareStatement(ProductDatasource.GET_ALL_PRODUCTS);
             ResultSet rs = st.executeQuery();
    		
    		 List<ProductModel> productList = new ArrayList<ProductModel>();

            while (rs.next()) {
            	ProductModel allProducts = new ProductModel();
            	
            	allProducts.setId(rs.getInt("p_id"));
            	allProducts.setName(rs.getString("name"));
            	allProducts.setDescription(rs.getString("description"));
            	allProducts.setPrice(rs.getDouble("price"));
            	allProducts.setImage(rs.getString("image"));
            	
            	
            	
            	
//            	  int id = rs.getInt("id");
//                String name = rs.getString("name");
//                double price = rs.getDouble("price");
//                String image= rs.getString("image");
//                
//                System.out.println("Retrieved product: " + name); 
//                
//                ProductModel product = new ProductModel(id,name,price,image);
                productList.add(allProducts);
            }
            return productList;
//            System.out.println("List of products: " + productList.size());
            
        } catch (SQLException ex) {
        	
        	ex.printStackTrace();
        	return null;
        	
//            System.err.println("Error retrieving products: " + ex.getMessage());
        }
//        return productList;
    }
   
}
