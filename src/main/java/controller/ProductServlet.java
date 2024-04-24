package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.ProductModel;
import services.ProductServices;
import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.SERVLET_URL_ADD_PRODUCT })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProductServices productServices = new ProductServices();

    public ProductServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProductModel> allProducts = productServices.getAllProducts();

            if (allProducts != null && !allProducts.isEmpty()) {
                System.out.println("Total number of products: " + allProducts.size());
                System.out.println("Product list attribute set: " + allProducts);
                for (ProductModel product : allProducts) {
                    System.out.println(product);
                }

                request.setAttribute("productList", allProducts);
                request.getRequestDispatcher(StringUtils.PRODUCT_LIST_PAGE).forward(request, response);
            } else {
                System.out.println("No products found.");
                // Handle case where no products are found, perhaps display an error message
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
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

        int result = 0; 
        try {
            result = productServices.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result > 0) {
            request.setAttribute(StringUtils.MESSAGE_SUCCESS, StringUtils.ADD_PRODUCT_SUCCESS_MESSAGE);
            response.sendRedirect(request.getContextPath() + StringUtils.PRODUCT_LIST_PAGE);

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
//        // Implement logic to save the image to a location on the server
//        // Return the file path or name where the image is saved
//        return null; // Placeholder, implement your logic here
//    }
}
