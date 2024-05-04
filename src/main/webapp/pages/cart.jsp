<%@page import="java.sql.*, utils.StringUtils, java.io.*, javax.imageio.*, java.util.Base64"%>
<%@ page import="java.util.ArrayList" %>

<%
    HttpSession userSession = request.getSession();
    String currentUser = (String) userSession.getAttribute(StringUtils.USERNAME);
    String contextPath = request.getContextPath();
    String userRole = ""; 

    Connection conn = null;

    PreparedStatement roleStmt = null;
    ResultSet roleRs = null;
    try {
        conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
        String roleQuery = "SELECT id FROM users WHERE user_name = ?";
        roleStmt = conn.prepareStatement(roleQuery);
        roleStmt.setString(1, currentUser);
        roleRs = roleStmt.executeQuery();

        if (roleRs.next()) {
            userRole = roleRs.getString("id");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (roleRs != null) roleRs.close();
            if (roleStmt != null) roleStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ArrayList<String> productNames = new ArrayList<>();

    PreparedStatement prodStmt = null;
    ResultSet prodRs = null;
    try {
        conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
        String prodQuery = "SELECT product_name FROM cart WHERE userId = ?";
        prodStmt = conn.prepareStatement(prodQuery);
        prodStmt.setString(1, userRole);
        prodRs = prodStmt.executeQuery();

        while (prodRs.next()) {
            String productName = prodRs.getString("product_name");
            productNames.add(productName);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (prodRs != null) prodRs.close();
            if (prodStmt != null) prodStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String product = "";  
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart Page</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        img {
            height: 100px;
            width: 100px;
        }
    </style>
</head>
<body>
    <h2>Selected Product Details</h2>
    <div>
        <% for (int i = 0; i < productNames.size(); i++) {
             product = productNames.get(i).trim(); %>
            
            <h3>Product Name: <%= product %></h3>
            
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Product Image</th>
                    <th>Action</th>
                </tr>
                <% 
                    PreparedStatement stmt = null;
                    ResultSet rs = null;
                    try {
                        conn = DriverManager.getConnection(StringUtils.LOCALHOST_URL, StringUtils.LOCALHOST_USERNAME, StringUtils.LOCALHOST_PASSWORD);
                        stmt = conn.prepareStatement(StringUtils.QUERY_PRODUCT_DETAILS);
                        stmt.setString(1, product);
                        rs = stmt.executeQuery();
                        
                        while (rs.next()) {
                %>
                <tr>
                    <td><%= rs.getString("name") %></td>
                    <td>
                        <a href="./pages/SelectedProduct.jsp?name=<%= rs.getString("name") %>&path=<%= rs.getString("path") %>">
                            <img src="../assets/<%= rs.getString("path") %>" alt="<%= rs.getString("path") %> Image">
                        </a>
                    </td>
                    <td>
                        <form action="<%=contextPath%>/deleteProduct" method="post">
                            <input type="hidden" name="productName" value="<%= rs.getString("name") %>">
                            <input type="hidden" name="userID" value="<%= userRole %>">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (rs != null) rs.close();
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                %>
            </table>
        <% } %>
        
        
    </div>
    <a href='./Checkout.jsp'>Checkout</a>
</body>
</html>
