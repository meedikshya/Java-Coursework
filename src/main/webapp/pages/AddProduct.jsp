<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.StringUtils"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/stylesheets/AddProducr.css" />
<style>
   
</style>
</head>
<body>
    <%
    String msg = request.getParameter("msg");
    if ("done".equals(msg)) {
    %>
    <h3 class="alert">Product Added Successfully</h3>
    <%
    }
    %>
    <%
    if ("wrong".equals(msg)) {
    %>
    <h3 class="alert">Something Went Wrong! Try Again!</h3>
    <%
    }
    %>
    <div class="container">
        <div class="form-container">
            <form action="../ProductServlet" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" required>
                </div>

                <div class="form-group">
                    <label for="category">Product Category:</label>
                    <select id="category" name="category" required>
                        <option value="">Select Category</option>
                        <option value="Fitness">Fitness</option>
                        <option value="Advanced">Advanced</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="unitPrice">Unit Price:</label>
                    <input type="number" id="unitPrice" name="unitPrice" step="0.01" required>
                </div>

                <div class="form-group">
                    <label for="stockQuantity">Stock Quantity:</label>
                    <input type="number" id="stockQuantity" name="stockQuantity" required>
                </div>

                <div class="form-group">
                    <label for="productImage">Product Image:</label>
                    <input type="file" id="productImage" name="productImage" accept="image/*" required>
                </div>
                <div class="form-group">
                    <button type="submit">Add Product</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>