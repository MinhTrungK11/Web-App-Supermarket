<%-- 
    Document   : vouchers
    Created on : Nov 6, 2022, 11:33:50 PM
    Author     : mrtru
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Product Page - Admin HTML Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="./Admin/css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="./Admin/css/bootstrap.min.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="./Admin/css/templatemo-style.css">
        <link rel="stylesheet" href="./Admin/css/alert.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
                            <a class="nav-link active" href="ManageVoucher">
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
        <c:if test = "${msg == 'OK'}">
            <div class="alert">
                <span class="closebtn" onclick="this.parentElement.style.display = 'none';">&times;</span>
                <strong>Xử lý thành công!</strong>
            </div>
        </c:if>
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 col-md-12">
                                <h2 class="tm-block-title">Danh sách voucher</h2>
                                <input id="myInput" type="text" placeholder="Search..">
                                <br><br>
                                <div class="tm-product-table-container">
                                    <table class="table table-hover tm-table-small tm-product-table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Mã voucher</th>
                                                <th scope="col">Discount(%)</th>
                                                <th scope="col">Số lượng</th>
                                                <th scope="col">&nbsp;</th>
                                                <th scope="col">&nbsp;</th>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach items="${lsVoucher}" var="o">
                                                <tr>
                                                    <td>${o.maVoucher}</td>
                                                    <td class="tm-product-name">${o.phanTram}</td>
                                                    <td class="tm-product-name">${o.soLuong}</td>
                                                    <td>
                                                        <a id="${o.maVoucher}" class ="delete tm-product-delete-link">
                                                            <i class="far fa-trash-alt tm-product-delete-icon" style="color:red"></i>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <a href="EditVoucher?pid=${o.maVoucher}" class="tm-product-delete-link">
                                                            <i class="fas fa-edit"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- table container -->
                                <a href="AddVoucher" class="btn btn-primary btn-block text-uppercase mb-3 text-white">Add Now</a>
                            </div>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-dark mb-0 px-4 small">
                Copyright &copy; <b>2018</b> All rights reserved. 

                Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
            </p>
        </div>
    </footer>
    <script>
        function clicked(e)
        {
            if (!confirm('Are you sure?')) {
                e.preventDefault();
            }
        }
    </script>
    <script>
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $(".delete").click(function () {
                var id = $(this).attr('id');
                var confirmalert = confirm("Are you sure?");
                if (confirmalert == true)
                {
                    $.ajax({
                        url: "DeleteVoucher",
                        type: "get",
                        data: {
                            id: id,
                        },
                        success: function (data) {
                            alert(data);
                            location.reload();
                        },
                        error: function () {
                            alert("Xóa không thành công");
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>
