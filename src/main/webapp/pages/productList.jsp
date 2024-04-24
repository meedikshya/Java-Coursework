<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Products</title>
</head>
<body>
    <h1>List of Products</h1>
    
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
            <c:forEach items="${allProducts}" var="product">
                <tr>
                    <td>${product.product_name}</td>
                    <td>${product.category}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                    <td><img src="${product.image}" alt="Product Image" style="width: 100px; height: auto;"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
