package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import configs.Dbconnection;
import datasource.ProductDataSource;
import models.ProductModel;
import services.ProductServices;
import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = {StringUtils.SERVLET_URL_ADD_PRODUCT})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductServices productServices = new ProductServices();

    public ProductServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement productStmt = null;
        ResultSet productRs = null;
        try {
            Dbconnection dbObj = new Dbconnection();
            conn = dbObj.getDbConnection();
            String productQuery = ProductDataSource.SELECT_ALL_PRODUCTS;
            productStmt = conn.prepareStatement(productQuery);
            productRs = productStmt.executeQuery();
            
            List<ProductModel> allProducts = new ArrayList<>();

            while (productRs.next()) {
                String name = productRs.getString("product_name");
                String category = productRs.getString("Product_Category");
                double price = productRs.getDouble("unit_price");
                int quantity = productRs.getInt("stock_quantity");
                String imageUrl = productRs.getString("product_image");

                ProductModel product = new ProductModel(name, category, price, quantity, imageUrl);
                allProducts.add(product);
            }

            if (!allProducts.isEmpty()) {
                request.setAttribute("productLists", allProducts);
                request.getRequestDispatcher(StringUtils.PRODUCT_LIST_PAGE).forward(request, response);
            } else {
                System.out.println("No products found.");
                // Handle case where no products are found, perhaps display an error message
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
            System.out.println("errorrrr");

        } finally {
            try {
                if (conn != null) conn.close();
                if (productStmt != null) productStmt.close();
                if (productRs != null) productRs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("productName");
        String category = request.getParameter("category");
        Part imagePart = request.getPart("productImage");
        String priceParam = request.getParameter("unitPrice");
        double price = parseDoubleOrDefault(priceParam, 0.0);
        String quantityParam = request.getParameter("stockQuantity");
        int quantity = parseIntOrDefault(quantityParam, 0);

        ProductModel product = new ProductModel(name, category, price, quantity, imagePart);

//        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH;
//	    String fileName = ProductModel.getImageUrlFromPart();
//	    if(!fileName.isEmpty() && fileName != null)
//    		imagePart.write(savePath + fileName);
//        
        
        int result = 0; 
        
        try {
            result = productServices.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
        	
        	// Get the image file name from the student object (assuming it was extracted earlier)
        	String fileName = product.getImageUrlFromPart();
        				
        	// Check if a filename exists (not empty or null)
        	if (!fileName.isEmpty() && fileName != null) {
        	// Construct the full image save path by combining the directory path and filename
        	String savePath = StringUtils.IMAGE_DIR_PRODUCT;
        	imagePart.write(savePath + fileName);  // Save the uploaded image to the specified path
        	}
        	
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.ADD_PRODUCT_SUCCESS_MESSAGE);
            response.sendRedirect(request.getContextPath() + StringUtils.SERVLET_URL_ADD_PRODUCT);

            List<ProductModel> addedProducts = new ArrayList<>();
            addedProducts.add(product);
            request.setAttribute("addedProducts", addedProducts);
            System.out.println("Added Products ArrayList: " + addedProducts);
        } else {
            request.setAttribute(StringUtils.MESSAGE_ERROR, StringUtils.ADD_PRODUCT_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
        }
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Double.parseDouble(value.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    private int parseIntOrDefault(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

//    private String saveImage(Part imagePart) throws IOException {
//        String savePath = StringUtils.IMAGE_DIR_SAVE_PATH; 
//        String fileName = StringUtils.generateUniqueFileName(); // Generates a unique file name
//        String filePath = savePath + File.separator + fileName;
//
//        try (InputStream inputStream = imagePart.getInputStream();
//             FileOutputStream outputStream = new FileOutputStream(filePath)) {
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//        }
//
//        return fileName; 
//    }
}