<%-- 
    Document   : admin-adjust-account
    Created on : Nov 17, 2022, 1:56:03 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="Entity.Account"%>
<%@ page import="DAO.AdminAccountDAO"%>
<%@ page session="true" %>

<%  
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("utf-8");
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.1/css/all.css">
        <link rel="stylesheet" href="Admin/css/bootstrap.min.css" >
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="Admin/css/templatemo-style.css">
        <link rel="stylesheet" type="text/css" href="Admin/css/account.css">
        <link rel="stylesheet" href="Admin/css/fontawesome.min.css" />
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

                        <li class="nav-item">
                            <a class="nav-link active" href="AdminManageAccount">
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
                <div class="tm-block-col tm-col-avatar" style="padding-left: 0px">
                    <div class="tm-bg-primary-dark tm-block tm-block-avatar">
                        <h2 class="tm-block-title text-white">Change Avatar</h2>
                        <div class="tm-avatar-container">
                            <img
                                src="img/avatar.png"
                                alt="Avatar"
                                class="tm-avatar img-fluid mb-4"
                                />
                            <a href="#" class="tm-avatar-delete-link">
                                <i class="far fa-trash-alt tm-product-delete-icon"></i>
                            </a>
                        </div>
                        <button class="btn btn-primary btn-block text-uppercase text-white">
                            Upload New Photo
                        </button>
                    </div>
                </div>
                <div class="tm-block-col tm-col-account-settings" style="padding-right: 0px">
                    <div class="tm-bg-primary-dark tm-block tm-block-settings">
                        <h2 class="tm-block-title text-white" >Account Settings</h2>
                        <form action="/AdminAdjustAccount" class="tm-signup-form row" method="POST">
                            <input
                                id="username"
                                name="username"
                                type="hidden"
                                class="form-control validate text-white"
                                value="${username}"
                                />
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="name">Account Name</label>
                                <input
                                    id="name"
                                    name="name"
                                    type="text"
                                    class="form-control validate text-white"
                                    value="${name}"
                                    />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="email">Account Email</label>
                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    class="form-control validate text-white"
                                    value="${email}"
                                    />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="email">Address</label>
                                <input
                                    id="address"
                                    name="address"
                                    type="text"
                                    class="form-control validate text-white"
                                    value="${address}"
                                    />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="phone">Phone</label>
                                <input
                                    id="phone"
                                    name="phone"
                                    type="tel"
                                    class="form-control validate text-white"
                                    value="${phone}"
                                    />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="password">Password</label>
                                <input
                                    id="pass1"
                                    name="pass1"
                                    type="password"
                                    class="form-control validate text-white"
                                    value = ""
                                    />
                            </div>
                            <div class="form-group col-lg-6">
                                <label class="text-white" for="password2">Re-enter Password</label>
                                <input
                                    id="pass2"
                                    name="pass2"
                                    type="password"
                                    class="form-control validate text-white"
                                    value = ""
                                    />
                            </div> 
                            <div class="col-12">
                                <label class="tm-hide-sm">&nbsp;</label>
                                <button
                                    type="submit"
                                    class="btn btn-primary btn-block text-uppercase text-white"
                                    >
                                    Update Your Profile
                                </button>
                            </div>
                        </form>
                        <div class="status col-12"><c:out value="${status}"/></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
