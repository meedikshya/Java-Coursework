package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configs.DbConnectionConfig;
import datasource.AddToCartDataSource;
import datasource.OrderDataSource;
import models.OrderModel;
import models.OrderDetailModel;

public class OrderServices {

    public static int setOrder(OrderModel order) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order
            String query = OrderDataSource.INSERT_ORDERS;
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, order.getUsername());
            stmt.setDouble(2, order.getGrandTotal());
            stmt.setInt(3, order.getTotalItems());
            stmt.setString(4, order.getOrderStatus());
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            result = auto_id;
           
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static int setOrderDetails(OrderDetailModel orderDetail) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order detail
            String query = OrderDataSource.ORDER_DETAILS;
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, orderDetail.getOrderId());
            stmt.setInt(2, orderDetail.getProductId());
            stmt.setDouble(3, orderDetail.getPrice());
            stmt.setInt(4, orderDetail.getQuantity());
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    public static int deleteCart( String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int result = 0;

        try {
            DbConnectionConfig dbObj = new DbConnectionConfig();
            conn = dbObj.getDbConnection();
            
            // Prepare the SQL statement to insert an order detail
            String query = AddToCartDataSource.DELETE_CART;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            
            // Execute the SQL statement
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
}
