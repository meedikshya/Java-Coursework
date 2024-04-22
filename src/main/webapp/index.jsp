<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <link rel="stylesheet" href="./stylesheets/style.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
    <title>Navbar</title>
</head>

<body>
    <div class="head">
        <div>
            <input type="checkbox" id="user">
            <input type="checkbox" id="form">
            <label for="form">
                <div class="form-wrapper">
                    <div class="form-wrapper__content">
                        <label for="user">
                            <div class="navigation"><i class="far fa-times-circle"></i></div>
                        </label>
                      
                    </div>
                </div>
            </label>
            <nav>
                <div class="logo">o'clock<span class="smile">&#9787;</span>
                </div>
                <input type="checkbox" id="mobile-search">
                <label for="mobile-search">
                    <div class="search-icon"> <i class="fa fa-search"></i></div>
                </label>

                <input type="checkbox" id="user">
                <label for="user">
                    <div class="user-btn">
                    <a href = "./pages/login.jsp">Login</a>
                     <i class="far fa-user">
                     </i></div>
                </label>

                <div class="search_wrapper">

                    <div class='search__container'>
                        <form method="GET">
                            <input id="search-field" class="input-field" type="search" name="keyword"
                                placeholder="Enter keywords Here" autocomplete="off">
                            <button class="btn-search" type="submit">
                                <i class="fa fa-search"></i></button>
                        </form>
                    </div>

                    <div class="extra-icons">
                        <a href="#"><i class="fas fa-user"></i></a>
                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                    </div>
                </div>
                <input type="checkbox" id="check">
                <label for="check">
                    <div class="checkbtn"><i class="fas fa-bars"></i></div>
                </label>

                <input type="checkbox" id="toggle">
                <input type="checkbox" id="form">

                <ul>
                    <li> <label for="check-10" class="mobile-view">Home</label>
                        <a href="#">Home</i></a>
                    </li>

                    <li>
                        <label for="check-9" class="mobile-view">Movies
                        </label>
                        <a href="#">About Us</a>
                    </li>
                    <li>
                        <input type="checkbox" id="check-4">
                        <label for="check-4" class="mobile-view"> <i class="fas fa-caret-down"></i>
                        </label>
                        <a href="#">Product Categories <i class="fas fa-caret-down"></i></a>
                    
                        <ul class="submenu-list">

                            <li class="submenu-heading">
                                <a href="">
                                    <h1>Watch Categories <i class="fas fa-clock"></i></h1>
                                </a>
                            </li>

                            <li class="submenu-block">
                                <ul class="submenu-block__content">
                                    <li class="submenu-list__item">
                                        <input type="checkbox" id="check-3">
                                        <label for="check-3" class="mobile-view"><span
                                                style="margin-left: -10px;">Brands</span><i
                                                class="fas fa-chevron-right"></i></label>
                                        <a href="#">Brands</a>

                                        <ul class="submenu-list__drop">
                                            <li> <a href="#">Apple</a> </li>
                                            <li> <a href="#">Samsung</a> </li>
                                            <li> <a href="#">Fitbit</a> </li>
                                            <li> <a href="#">Huawei</a> </li>
                                            <li> <a href="#">Garmin</a> </li>
                                        </ul>
                                    </li>

                                    <li class="submenu-list__item">
                                        <input type="checkbox" id="check-2">
                                        <label for="check-2" class="mobile-view"><span
                                                style="margin-left: -10px;">Types</span><i
                                                class="fas fa-chevron-right"></i></label>
                                        <a href="#">Types</a>

                                        <ul class="submenu-list__drop">
                                            <li> <a href="#"> Fitness Tracker </a> </li>
                                            <li> <a href="#"> Advanced technology</a> </li>
                                            <li> <a href="#"> Hybrid </a> </li>
                                        </ul>
                                    </li>

                                    <li class="submenu-list__item">
                                        <input type="checkbox" id="check-1">
                                        <label for="check-1" class="mobile-view"><span
                                                style="margin-left: -10px;">Countries</span> <i
                                                class="fas fa-chevron-right"></i></label>
                                        <a href="#">Target Groups</a>

                                        <ul class="submenu-list__drop">
                                            <li> <a href="#">Fitness Enthusists</a> </li>
                                            <li> <a href="#">Tech Experts</a> </li>
                                            <li> <a href="#">Busy professionals</a> </li>
                                        </ul>
                                    </li>

                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul> 
            </nav>


            <div class="cover__page">
                <section  class="h-text">
                    <span>Time</span>
                    <h1>It's Moon o'clock</h1>
                    <br>
                    <a href="#">Visit Now</a>

                </section>
        </div>
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="footer-col">
                        <h4>company</h4>
                        <ul>
                            <li><a href="#">about us</a></li>
                            <li><a href="#">our services</a></li>
                            <li><a href="#">privacy policy</a></li>
                            <li><a href="#">affiliate program</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>get help</h4>
                        <ul>
                            <li><a href="#">FAQ</a></li>
                            <li><a href="#">shipping</a></li>
                            <li><a href="#">returns</a></li>
                            <li><a href="#">order status</a></li>
                            <li><a href="#">payment options</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>online shop</h4>
                        <ul>
                            <li><a href="#">watch</a></li>
                            <li><a href="#">bag</a></li>
                            <li><a href="#">shoes</a></li>
                            <li><a href="#">dress</a></li>
                        </ul>
                    </div>
                    <div class="footer-col">
                        <h4>follow us</h4>
                        <div class="social-links">
                            <a href="#"><i class="fab fa-facebook-f"></i></a>
                            <a href="#"><i class="fab fa-twitter"></i></a>
                            <a href="#"><i class="fab fa-instagram"></i></a>
                            <a href="#"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
       </footer>

</body>
</html>