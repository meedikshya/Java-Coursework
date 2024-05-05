<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="configs.Dbconnection"%>
<%@page import="java.sql.Connection"%>

<%
//setting absolute path
String mainPath = request.getContextPath();
// invoking session username
%>
<!DOCTYPE html>
<html>
<head>
	<title>Product Page</title>
	<style>
	
nav {
  background-color: #333;
  overflow: hidden;
}

nav ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
}

nav li {
  float: left;
}

nav li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

nav li a:hover {
  background-color: #111;
}

	.product-container {
  margin-left:3cm;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width:75%;
  margin-top:1cm;
}



.product-details {
  text-align: center;
}

.product-name {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 10px;
}

.product-category,
.product-brand {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
}

.product-price {
  font-size: 20px;
  font-weight: bold;
}
	
	tr {
  display: flex;
  flex-direction: column;
  align-items: center;
}

td {
  margin: 10px 0;
  text-align: center;
}

td#1 {
  display: flex;
  justify-content: center;
  align-items: center;
}

#img {
  width: 5cm;
  height: 5cm;
  object-fit: contain;
}
	
		table {
			margin-left: 2cm;
			border-collapse: collapse;
			margin: 20px;
			margin-top: 2cm;
			width: 80%;
		}
		
		th, td {
			padding: 10px;
			border: 1px solid #ddd;
		}
		
		th {
			background-color: #f2f2f2;
		}
		
		tr:nth-child(even) {
			background-color: #f2f2f2;
		}
		.add-to-cart {
		
  background-color: #007bff;
  border: none;
  color: #fff;
  padding: 12px 24px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
  
 
  
}

.add-to-cart:hover {
  background-color: #0069d9;
}

  .view-cart-btn {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    cursor: pointer;
  }

  .view-cart-btn:hover {
    background-color: #3e8e41;
  }

  .view-cart-btn:active {
    background-color: #1e4620;
  }
</style>


</head>
<body>
<nav>
  <ul>
    <li><a href="Home.jsp">Home</a></li>
    <li><a href="product.jsp">Products</a></li>
    <li><a href="AboutUs.jsp">About Us</a></li>
  </ul>
</nav>

<button class="view-cart-btn" onclick="window.location.href='Cart.jsp';">View Cart</button>

	<%
	Dbconnection dbConnection = new Dbconnection();
	Connection con = dbConnection.getDbConnection();
	PreparedStatement st = con.prepareStatement("SELECT p_id, name, price, category, brand, image FROM product");
	ResultSet result = st.executeQuery();
	
	String imagePath =  "../uploads/productImage/";
	%>
		<%
		while (result.next()) {
			String name = result.getString("name");
			String price = result.getString("price");
			String category = result.getString("category");
			String brand = result.getString("brand");
			String imageName = result.getString("image");
			String id = result.getString("p_id");
			%>
		
	
	<div class="product-container">
	<div style="display:flex;">
	<div style="margin-left:1cm; float:left;">
  <img src="<%= imagePath + imageName %>"alt="Product Image" id='img'>
  </div>
  <div style="margin-left:5cm;">
    <h2 class="product-name">Product Name: <%= name %></h2>
    <p class="product-category">Category: <%= category %> </p>
    <p class="product-brand"> Brand: <%= brand %></p>
    <p class="product-price"> Price: <%= price %></p>
    <form method="post" action="../AddToCart">
    <input type="hidden" name="name" value="<%= name %>">
    <input type="hidden" name="category" value="<%= category %>">
    <input type="hidden" name="brand" value="<%= brand %>">
    <input type="hidden" name="price" value="<%= price %>">
    <input type="hidden" name="id" value="<%= id %>">
     <input type="hidden" name="image" value="<%= imageName %>">
    
     
    
    <button type="submit" class="add-to-cart">Add to Cart</button>
  </form>
    </div>
    
    
    </div>
  </div>

	<% 	}
		%>
		
	
</body>
</html>
