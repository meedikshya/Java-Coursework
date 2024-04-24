<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="models.ProductModel"%>
<%@page import="services.ProductServices"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Products</title>
</head>
<body>

<sql:setDataSource var="Dbconnection" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/o'clock" user="root" password=""/>
<sql:query var="products" dataSource="${Dbconnection}">
    SELECT * FROM product;
</sql:query>

<h1>List of Products</h1>

<c:choose>
    <c:when test="${empty products.rows}">
        <p>No products found.</p>
    </c:when>
    <c:otherwise>
        <div class="product-info">
            <div class="products">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Image</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${products.rows}">
                            <tr>
                                <td>${row.product_name}</td>
                                <td>${row.Product_Category}</td>
                                <td>${row.unit_price}</td>
                                <td>${row.stock_quantity}</td>
                                <td><img src="${row.product_image}" alt="Product Image" style="width: 100px; height: auto;"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>
