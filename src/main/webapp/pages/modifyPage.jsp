<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="utils.StringUtils"%>
<%@page import="datasource.ProductDataSource"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/ModifyPage.css" />
</head>
<body>
	<form action="./UpdateProductServlet" method="post">
		<label> Product Name: </label>
		<input type="text" id="update-product-name" name="update-product-name" value="${product.name}"/> 
		
		<label> Category: </label>
		<input type="text" id="update-product-category" name="update-product-category" value="${product.category}"/> 
		
		<label> Unit Price: </label>
		<input type="text" id="update-product-price" name="update-product-price" value="${product.price}"/> 
		
		<label> Product Quantity: </label>
		<input type="text" id="update-product-quantity" name="update-product-quantity" value="${product.quantity}"/> 
		
		<input type="hidden" name="updateId" value="${product.name}" />
	
		<button type="submit">Update</button> 
	</form>

</body>
</html>