<%-- 
    Document   : profile
    Created on : Oct 19, 2022, 11:22:47 PM
    Author     : nguye
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>G11 | THÔNG TIN TÀI KHOẢN</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Noto+Serif:wght@200;300;400;600;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/profile.css" type="text/css">
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
                    <div class="col-lg-3">
                        <div class="header__logo">
                            <a href="/home"><img src="img/logo.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <nav class="header__menu">
                            <ul>
                                <li><a href="/home">home</a></li>
                                <li><a href="/profile">profile</a></li>
                                <li class="active"><a href="/editprofile">edit profile</a></li>
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
        <!-- Profile Bgin -->
        <div class="card">
            <div class="form"">
                <form class="register-form" action="editprofile" method="post">
                    <input value="${username}" name="username" type="text" pattern="^([a-zA-Z0-9]).{8,}$" placeholder="Tên tài khoản" title="Tên tài khoản phải có ít nhất 9 ký tự và không chứa ký tự đặc biệt." required disabled/>
                    <input value="${name}" name="name" type="text" placeholder="Họ tên" required/>
                    <input value="${phonenumber}" name="phonenumber" type="tel" pattern="[0-9]{10,11}" placeholder="Số điện thoại" title="Số điện thoại bao gồm 10 - 11 chữ số" required/>
                    <input value="${email}" name="email" type="email" placeholder="Email"required disabled/>
                    <input value="${address}" name="address" type="text" placeholder="Địa chỉ"/>
                    <p class="message text-danger">${mess}</p>
                    <button type="submit">LƯU</button>
                </form>
            </div>
        </div>

        <!-- Profile End -->

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