<%-- 
    Document   : signup
    Created on : Oct 19, 2022, 8:50:17 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>G11 | ONLINE STORE</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">   
        <link rel="stylesheet" href="css/login&signup.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Humberger Begin -->
        <!-- Humberger End -->

        <!-- Header Section Begin -->
        <header class="header">
            <div class="header__top">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="header__top__left">
                                <ul>
                                    <li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
                                    <li>Free Shipping for all Order of $99</li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="header__top__right">
                                <div class="header__top__right__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-linkedin"></i></a>
                                    <a href="#"><i class="fa fa-pinterest-p"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="header__logo">
                            <a href="/home"><img src="img/logo.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <nav class="header__menu">
                            <ul>
                                <li><a href="/home">home</a></li>
                                <li ><a href="/login">LOGIN</a></li>
                                <li class="active"><a href="signup">SIGNUP</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="humberger__open">
                    <i class="fa fa-bars"></i>
                </div>
            </div>
        </header>
        <!-- Header Section End -->
        <!-- Login Form Bgin -->
        <div class="login-page">
            <div class="form"">
                <form class="register-form" action="signup" method="post">
                    <input value="${username}" name="username" type="text" pattern="^([a-zA-Z0-9]).{8,}$" placeholder="Tên tài khoản" title="Tên tài khoản phải có ít nhất 9 ký tự và không chứa ký tự đặc biệt." required/>
                    <input value="${password}" name="password" type="password" pattern="^([a-zA-Z0-9]).{7,12}$" placeholder="Mật khẩu mới" title="Mật khẩu có phải có 8 - 12 ký tự, bao gồm ký tự viết thường, viết hoa, chữ số và không chứa ký tự đặc biệt. " required/>
                    <input value="${name}" name="name" type="text" placeholder="Họ tên" required/>
                    <input value="${phonenumber}" name="phonenumber" type="tel" pattern="[0-9]{10,11}" placeholder="Số điện thoại" title="Số điện thoại bao gồm 10 - 11 chữ số" required/>
                    <input value="${email}" name="email" type="email" placeholder="Email"required/>
                    <button type="submit">TẠO TÀI KHOẢN</button>
                    <p class="message text-danger">${mess}</p>
                    <p class="message">Đã có tài khoản <a href="login.jsp">Đăng nhập</a></p>
                </form>
            </div>
        </div>
        <!-- Login Form End -->

        <!-- Footer Section Begin -->
        <%@include file="/footer.jsp" %>
        <!-- Footer Section End -->

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>

    </body>

</html>
