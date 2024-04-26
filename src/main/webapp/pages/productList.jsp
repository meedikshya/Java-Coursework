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
  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/ProductList.css" />
</head>
<body>

<sql:setDataSource var="Dbconnection" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/o'clock" user="root" password=""/>
<sql:query var="products" dataSource="${Dbconnection}">
    SELECT *, COUNT(*) as total FROM product;
</sql:query>

<h1>List of Products</h1>

<sql:setDataSource var="Dbconnection" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/o'clock" user="root" password=""/>
<sql:query var="productCount" dataSource="${Dbconnection}">
    SELECT COUNT(*) as total FROM product;
</sql:query>

<sql:query var="products" dataSource="${Dbconnection}">
    SELECT * FROM product;
</sql:query>



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
                            <th>ID</th>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:set var="totalCount" value="0"/>
                        <c:forEach var="row" items="${products.rows}">
                            <tr>
                                <td>${row.product_id}</td>
                                <td>${row.product_name}</td>
                                <td>${row.Product_Category}</td>
                                <td>${row.unit_price}</td>
                                <td>${row.stock_quantity}</td>
                                <td><img src="data:image/png;base64,${models.ProductModel.getProductImageBase64(row.product_image)}" alt="Product Image"></td>
                                <td>
                                <form method="post" action="<%= contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>">
							    <input type="hidden" name="<%= StringUtils.UPDATE_ID %>" value="${row.product_id}" />
							    <button type="submit" class="btn btn-update">Update</button>
								</form>
								<form id="deleteForm-${row.product_id}" method="post" action="<%= contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>">
							    <input type="hidden" name="<%= StringUtils.DELETE_ID %>" value="${row.product_id}" />
							    <button type="button" class="btn btn-delete" onclick="confirmDelete('${row.product_id}')">Delete</button>
								</form>
								</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
         <p>Total Products: ${productCount.rows[0].total}</p>
      
    </c:otherwise>
</c:choose>

</body>
<script>
	function confirmDelete(userName) {
		if (confirm("Are you sure you want to delete this user: " + userName
				+ "?")) {
			document.getElementById("deleteForm-" + userName).submit();
		}
	}
</script>
</html>