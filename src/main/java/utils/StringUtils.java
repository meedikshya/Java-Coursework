package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StringUtils {
    // Utility methods for database operations

    public static final String SERVLET_URL_ADD_PRODUCT = "/ProductServlet";
    public static final String ADD_PRODUCT_PAGE = "pages/AddProduct.jsp";
    public static final String PRODUCT_LIST_PAGE = "/pages/productList.jsp";

    // Message constants for adding products
    public static final String ADD_PRODUCT_SUCCESS_MESSAGE = "Product added successfully!";
    public static final String ADD_PRODUCT_ERROR_MESSAGE = "Failed to add product. Please try again.";

    public static final String MESSAGE_SUCCESS = "successMessage";
    public static final String MESSAGE_ERROR = "errorMessage";
    
    public static final String IMAGE_ROOT_PATH = "Users\\User\\eclipse-workspace\\ProductCrud\\src\\main\\webapp\\Resources\\images";
    public static final String IMAGE_DIR_PRODUCT = "C:/" + IMAGE_ROOT_PATH + "product\\";
	public static final String IMAGE_DIR_USER = "C:/" + IMAGE_ROOT_PATH + "user\\";

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
