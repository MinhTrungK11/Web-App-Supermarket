<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Accounts - Product Admin Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.0.1/css/all.css">
        <link rel="stylesheet" href="Admin/css/fontawesome.min.css" />
        <link rel="stylesheet" type="text/css" href="Admin/css/bootstrap.min.css" >
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" type="text/css" href="Admin/css/templatemo-style.css">
        <link rel="stylesheet" type="text/css" href="Admin/css/account.css">
    </head>
    <body>
        <div class="" id="home">
            <nav class="navbar navbar-expand-xl">
                <div class="container h-100">
                    <a class="navbar-brand" href="/AdminDashboard">
                        <h1 class="tm-site-title mb-0">ADMIN</h1>
                    </a>
                    <button
                        class="navbar-toggler ml-auto mr-0"
                        type="button"
                        data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                        >
                        <i class="fas fa-bars tm-nav-icon"></i>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mx-auto h-100">
                            <li class="nav-item">
                                <a class="nav-link" href="AdminDashboard">
                                    <i class="fas fa-tachometer-alt"></i> Dashboard
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ManageProduct">
                                    <i class="fas fa-shopping-cart"></i> Products
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ManageCategory">
                                    <i class="fas fa-clipboard-list"></i> Category
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="AdminManageComment">
                                    <i class="fas fa-comments"></i>
                                    Comments
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ManageVoucher">
                                    <i class="fas fa-gift "></i>
                                    Voucher
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ManageOrder">
                                    <i class="fas fa-shopping-bag"></i> Order
                                </a>
                            </li>

                            <li class="nav-item active">
                                <a class="nav-link" href="AdminManageAccount">
                                    <i class="far fa-user"></i> Accounts
                                </a>
                            </li>
                        </ul>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link d-block" href="AdminLogout">
                                    ${sessionScope.adminAccount.name}, <b>Logout</b>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="container mt-5">
                <div class="row tm-content-row">
                    <div class="col-12 tm-block-col tm-bg-primary-dark" style="padding-bottom: 20px;">
                        <div class="tm-block tm-block-h-auto row" style="margin: 0; padding-bottom: 10px">
                            <h2 class="tm-block-title text-white" style="padding: 0; margin: 0">Lọc tài khoản</h2>
                        </div>
                        <form method="POST" style="padding-bottom: 0px" action="/AdminManageAccount" style="gap: 0 20px">
                            <input id ="filterByUsername" name="filterByUsername" class="col-5"  style="height: 50px; background-color: none !important;" value="${filterByUsername}" />
                            <button class="none tm-bg-primary-green text-white col-3" type="submit" style="height: 50px; border-radius: 4px;">TÌM KIẾM</button>
                        </form>
                        <div class="tm-block tm-block-h-auto" style="padding-bottom: 0px">
                            <h2 class="tm-block-title text-white" style="margin-bottom: 10px">Danh sách tài khoản</h2>
                        </div>

                        <div class="scrollable" style="margin-bottom: 30px">
                            <table id="AccountTable" class="table table-bordered text-lg-left">
                                <thead> 
                                    <tr>
                                        <th class="tm-bg-primary-green text-white">Họ tên</th>
                                        <th class="tm-bg-primary-green text-white">Email</th>
                                        <th class="tm-bg-primary-green text-white">Số điện thoại</th>
                                        <th class="tm-bg-primary-green text-white">Địa chỉ</th>
                                        <th class="tm-bg-primary-green text-white">Role</th>
                                        <th class="tm-bg-primary-green text-white"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${ListAccount}" var="i">
                                        <tr>
                                            <td>${i.name}</td>
                                            <td>${i.email}</td>
                                            <td>${i.phonenumber}</td>
                                            <td>${i.address}</td>
                                            <td>${i.role}</td>
                                            <td style="display: flex; justify-content: space-between">
                                                <form name="AdjustForm" method="POST" action="/AdminAdjustAccount">
                                                    <input type="hidden" name="username" value=${i.username} />
                                                    <button class="none" type="submit"><i class="fas fa-edit" style="color: blue"></i></button>
                                                </form>
                                                <input id="${i.username}Role" type="hidden" value="${i.role}"/>
                                                <button id="${i.username}" class="none cd-popup-trigger" type="button" style="color: red !important;"><i class="fas fa-minus-circle"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- row -->

            </div>
            <footer class="tm-footer row tm-mt-small">
                <div class="col-12 font-weight-light">
                    <p class="text-center text-white mb-0 px-4 small">
                        Copyright &copy; <b>2018</b> All rights reserved. 

                        Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                    </p>
                </div>
            </footer>
        </div>

        <div class="cd-popup">
            <div class="cd-popup-container">
                <p><span id="add_text"></span></p>
                <ul class="cd-buttons">
                    <li><button class="delete-button" type="button">YES</button></li>
                </ul>
                <a class="cd-popup-close img-replace"></a>
            </div> <!-- cd-popup-container -->
        </div> <!-- cd-popup -->

        <script src="../js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="../js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script>
            jQuery(document).ready(function ($) {
                //open popup
                $('.delete-button').on('click', function (event) {
                    var usernameNeedDelete = this.id;
                    var filterByUsername = document.getElementById('filterByUsername').value;
                    console.log(usernameNeedDelete);
                    console.log(filterByUsername);
                    $.ajax({
                        url: "AdminManageAccount",
                        type: "post",
                        data: {
                            usernameNeedDelete: usernameNeedDelete,
                            filterByUsername: filterByUsername
                        },
                        success: function () {
                            location.reload();
                        },
                        error: function () {
                            $('p').children('span').text("Xóa tài khoản không thành công");
                            $('.delete-button').removeClass('is-visible-button');
                        }
                    });
                }),
                        $('.cd-popup-trigger').on('click', function (event) {
                    var role = document.getElementById(this.id + "Role").value;
                    event.preventDefault();
                    $('.cd-popup').addClass('is-visible');
                    if (role == 'admin') {
                        $('p').children('span').text("Không thể xóa tài khoản Admin");
                    } else {
                        $('.cd-popup').addClass('is-visible');
                        $('.delete-button').attr('id', this.id);
                        $('.delete-button').addClass('is-visible-button');
                        $('p').children('span').text("Bạn có chắc xóa tài khoản này không ?");
                    }
                }),
                        //close popup
                        $('.cd-popup').on('click', function (event) {
                    if ($(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup')) {
                        event.preventDefault();
                        $(this).removeClass('is-visible');
                    }
                });
                //close popup when clicking the esc keyboard button
                $(document).keyup(function (event) {
                    if (event.which === '27') {
                        $('.cd-popup').removeClass('is-visible');
                    }
                });
            });
        </script>

        <input id="ListAccountStore" type="hidden" value="${requestScope.ListAccount}" />
    </body>

</html>

