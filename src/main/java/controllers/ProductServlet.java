package controllers;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import configs.Dbconnection;

import models.ProductModel;
//import models.ProductModel;
//import services.ProductServices;
import services.ProductServices;
import datasource.ProductDatasource;
import utils.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = {"/ProductServlet"})
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
            
            request.setAttribute("productList", allProducts);
            
            System.out.println(request.getContextPath());
            request.getRequestDispatcher(StringUtils.PRODUCT_LIST_PAGE).forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, perhaps display an error message
            System.out.println("errorrrr");
            
        }
    }

}