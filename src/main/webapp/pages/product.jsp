
<%@ page import="controllers.AddToCartServlet" %>
<%@ page import="models.ProductModel" %>
<%@ page import="services.ProductServices" %>
<%@ page import="controllers.ProductServlet" %>
<%@ page import="datasource.ProductDatasource" %>
<%@ page import="utils.StringUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.*" %>
<%@page import ="java.sql.Connection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%

String contextPath = request.getContextPath();
String errMsg = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
List<ProductModel> productList = new ArrayList<ProductModel>();

%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=contextPath%>/stylesheets/product.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<title>Product</title>
</head>
<body>

<%-- Display error message if present --%>
<% if (errMsg != null && !errMsg.isEmpty()) { %>
    <div class="error-message">
        <p><%=errMsg%></p>
    </div>
<% } %>


    <section id="Home">
        <nav>
            <div class="logo">
                <img src="<%=contextPath%>/images/logo.png">
            </div>

            <ul>
                <li><a href="#Home">Home</a></li>
                <li><a href="#About">About</a></li>
                <li><a href="#Gallary">Gallery</a></li>
                <li><a href="#Order">Orders</a></li>
            </ul>

            <div class="icon">
                <i class="fa-solid fa-magnifying-glass"></i>
                <i class="fa-solid fa-heart"></i>
                <i class="fa-solid fa-cart-shopping"></i>
            </div>

        </nav>

    </section>
    
    <!--Menu-->
	<div class="menu" id="Menu">
	    <h1>SmartWatch</h1>
	
	    <div class="menu_box">
		    
			<c:forEach var="product" items="${productList}">
			    <div class="menu_card">
			        <div class="menu_image">
  						<img src="<%=contextPath%>/images/${product.imageUrlFromPart}" alt="LA LA">
			        </div>
			
			
			        <div class="menu_info">
			            <h2>${product.name}</h2>


			            <p>${product.description}</p>

			            <h3>Rs. ${product.price}</h3>
			            <div class="menu_icon">
			            	<i class="fa-solid fa-star"></i>
			            	<i class="fa-solid fa-star"></i>
			            	<i class="fa-solid fa-star"></i>
			                <i class="fa-solid fa-star"></i>
			                <i class="fa-solid fa-star-half-stroke"></i>
			            </div>
			            <form method="post" action="addToCartServlet">
			                <input type="hidden" name="productId" value="${product.id}">
			                <button type="submit" class="menu_btn">Add to cart</button>
			            </form>
			        </div>
			    </div>
			</c:forEach>
			<c:if test="${empty productList}">
		        <p>No products available. lala</p>
		    </c:if>
			        
	    </div>
	</div>


<!--     Gallary -->

    <div class="gallary" id="Gallary">
        <h1>Watch Gallary</h1>

        <div class="gallary_image_box">
            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery1.jpeg">
            </div>

            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery2.jpeg">
            </div>

            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery3.webp">
            </div>

            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery4.jpeg">
            </div>

            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery5.jpeg">
            </div>

            <div class="gallary_image">
                <img src="<%=contextPath%>/images/gallery6.webp">
            </div>
        </div>

    </div>


    <!-- Footer-->

    <footer>
        <div class="footer_main">

            <div class="footer_tag">
                <h2>Location</h2>
                <p>Sri Lanka</p>
                <p>USA</p>
                <p>India</p>
                <p>Japan</p>
                <p>Italy</p>
            </div>

            <div class="footer_tag">
                <h2>Quick Link</h2>
                <p>Home</p>
                <p>About</p>
                <p>Menu</p>
                <p>Gallary</p>
                <p>Order</p>
            </div>

            <div class="footer_tag">
                <h2>Contact</h2>
                <p>+94 12 3456 789</p>
                <p>+94 25 5568456</p>
                <p>johndeo123@gmail.com</p>
                <p>foodshop123@gmail.com</p>
            </div>

            <div class="footer_tag">
                <h2>Our Service</h2>
                <p>Fast Delivery</p>
                <p>Easy Payments</p>
                <p>24 x 7 Service</p>
            </div>

            <div class="footer_tag">
                <h2>Follows</h2>
                <i class="fa-brands fa-facebook-f"></i>
                <i class="fa-brands fa-twitter"></i>
                <i class="fa-brands fa-instagram"></i>
                <i class="fa-brands fa-linkedin-in"></i>
            </div>

        </div>

        <p class="end">Design by<span><i class="fa-solid fa-face-grin"></i> WT Master Code</span></p>

    </footer>



    
</body>
</html>