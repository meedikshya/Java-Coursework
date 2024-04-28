<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="models.ProductModel"%>
<%@page import="services.ProductServices"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utils.StringUtils"%>
<%@page import="datasource.ProductDataSource"%>
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
<h1>List of Products</h1>
<a href="/ProductCrud/ProductServlet">Click here to go to the servlet</a>

<c:out value="${productLists}" />
<c:choose>
    <c:when test="${empty productLists}">
        <p>Empty ProductList</p>
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
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="row" items="${productLists}">
                            <tr>
                                <td>${row.name}</td>
                                <td>${row.category}</td>
                                <td>${row.price}</td>
                                <td>${row.quantity}</td>
                                <td>
                                  <img src="<%=contextPath%>/Resources/images/${row.getImageUrlFromPart()}" alt="Product Image">
                                </td>
                                <td>
                                <form method="post"
							action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER%>">
							<input type="hidden" name="<%=StringUtils.UPDATE_ID %>" value="" />
							<button type="submit">Update</button>
						</form>
						<form id="deleteForm-${student.username}" method="post"
							action="<%=contextPath + StringUtils.SERVLET_URL_MODIFY_USER %>">
							<input type="hidden" name="<%=StringUtils.DELETE_ID %>" value="" />
							<button type="button"
								onclick="confirmDelete('${student.username}')">Delete</button>
						</form>
						</td>
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