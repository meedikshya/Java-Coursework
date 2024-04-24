<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.StringUtils"%>
<link rel="stylesheet" type="text/css" href="./stylesheets/AddProducr.css" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        width: 400px;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin-bottom: 5px;
    }

    input[type="text"],
    input[type="number"],
    select {
        width: calc(100% - 16px);
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="file"] {
        width: calc(100% - 16px);
        padding: 8px;
        box-sizing: border-box;
    }

    button[type="submit"] {
        width: 100%;
        padding: 10px 20px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    button[type="submit"]:hover {
        background-color: #45a049;
    }

    .alert {
        background-color: #f44336;
        color: white;
        padding: 10px;
        text-align: center;
        margin-bottom: 20px;
        border-radius: 4px;
    }
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
