<%-- 
    Document   : product
    Created on : Oct 19, 2022, 9:09:59 PM
    Author     : mrtru
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
        <link rel="stylesheet" href="./Admin/css/alert.css">
    </head>

    <body id="reportsPage">
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
                            <a class="nav-link active" href="ManageProduct">
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
                <div class="col-xl-15 col-lg-15 col-md-15 col-sm-15">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto tm-bg-primary-dark">
                        <div class="row">
                            <div class="col-xl-15 col-lg-15 col-md-12 tm-bg-primary-dark">
                                <h2 class="tm-block-title">Danh sách sản phẩm</h2>
                                <input id="myInput" type="text" placeholder="Search.." style="border-radius: 4px;">
                                <br><br>
                                <div class="tm-product-table-container">
                                    <table class="table table-hover tm-table-small tm-product-table" style="min-width: 1000px">
                                        <thead>
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Tên sản phẩm</th>
                                                <th scope="col" >Số lượng</th>
                                                <th scope="col">Giá bán</th>
                                                <th scope="col">Giá nhập</th>
                                                <th width="10%" scope="col">Ngày hết hạn</th>
                                                <th width="30%" scope="col">Mô tả</th>
                                                <th scope="col">Giảm giá</th>
                                                <th scope="col" width="5%">&nbsp;</th>
                                                <th scope="col" width="5%">&nbsp;</th>
                                            </tr> 
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach items="${lsSanPham}" var="o">
                                                <tr>
                                                    <td>${o.idSanPham}</td>
                                                    <td class="tm-product-name">${o.tenSanPham}</td>
                                                    <td>${o.soLuong}</td>                                              
                                                    <td>${o.giaBan}</td>
                                                    <td>${o.giaNhap}</td>
                                                    <td ><fmt:formatDate pattern = "MM-dd-yyyy" value = "${o.hsd}"></fmt:formatDate></td>
                                                    <td class="start-text-align">${o.moTa}</td>
                                                    <td> ${o.giamGia}</td>
                                                    <td>
                                                        <a id="${o.idSanPham}" class="delete tm-product-delete-link">
                                                            <i class="far fa-trash-alt tm-product-delete-icon" style="color:red"></i>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <a href="EditProduct?pid=${o.idSanPham}" class="tm-product-delete-link">
                                                            <i class="fas fa-edit"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- table container -->
                                <a
                                    href="AddProduct"
                                    class="btn btn-primary btn-block text-uppercase mb-3 text-white">Add new product</a>
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
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
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
                // crating new click event for save button
                $(".delete").click(function () {
                    var id = +this.id;
                    var confirmalert = confirm("Are you sure?");
                    if (confirmalert == true)
                    {
                        $.ajax({
                            url: "DeleteProduct",
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
