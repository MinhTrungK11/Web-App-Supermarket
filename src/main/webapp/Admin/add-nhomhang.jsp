<%-- 
    Document   : add-product
    Created on : Oct 19, 2022, 9:12:33 PM
    Author     : mrtru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Add Product - Dashboard HTML Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="./Admin/css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="./Admin/jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
        <!-- http://api.jqueryui.com/datepicker/ -->
        <link rel="stylesheet" href="./Admin/css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="./Admin/css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
        <link rel="stylesheet" href="./Admin/css/popup.css">
    </head>

    <body>
        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="index.jsp">
                    <h1 class="tm-site-title mb-0">Product Admin</h1>
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
                            <a class="nav-link active" href="ManageCategory">
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

                        <li class="nav-item">
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

        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title middle">Thêm nhóm hàng</h2>
                            </div>
                        </div>
                        <div class="row tm-edit-product-row middle">
                            <div class="col-xl-6 col-lg-6 col-md-12">
                                <form action="AddNhomHang" class="tm-edit-product-form"  method="post">
                                    <div class="form-group mb-3">
                                        <label for="id">Id</label>
                                        <input id="id" name="id" type="text" class="form-control validate" value="${id}" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Tên nhóm hàng</label>
                                        <input id="name" name="name" type="text" class="form-control validate" required/>
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary btn-block text-uppercase text-white" onclick="clicked(event)">Thêm nhóm hàng</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="tm-footer row tm-mt-small">
            <div class="col-12 font-weight-light">
                <p class="text-center mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved. 

                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                </p>
            </div>
        </footer> 

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script>
                                        function clicked(e)
                                        {
                                            if (!confirm('Are you sure?')) {
                                                e.preventDefault();
                                            }
                                        }
        </script>
    </body>
</html>

