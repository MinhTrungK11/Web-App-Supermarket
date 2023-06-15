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
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-white">Add Product</h2>
                            </div>
                        </div>
                        <div class="row tm-edit-product-row">
                            <div class="col-xl-6 col-lg-6 col-md-12">
                                <form action="AddProduct" class="tm-edit-product-form" enctype="multipart/form-data" method="post" >
                                    <div class="form-group mb-3">
                                        <label for="name" >Tên sản phẩm</label>
                                        <input id="name" name="name" type="text" class="form-control validate text-white" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="qty" >Số lượng</label>
                                        <input id="qty" name="qty" type="number" class="form-control validate text-white" min="0" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Mô tả</label>
                                        <textarea class="form-control validate text-white" rows="3" name="description" required ></textarea>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="imPrice" >Giá nhập</label>
                                        <input id="imPrice" name="imPrice" type="number" class="form-control validate text-white" min="0" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name" >Giá bán</label>
                                        <input id="price" name="price" type="number" class="form-control validate text-white" min="0" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="expiry" >HSD</label>
                                        <input type="date" id="expiry" name="expiry" type="text" class="form-control validate text-white" min="2002-03-15" max="9998-12-31" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="discount" >Discount</label>
                                        <input id="discount" name="discount" type="number" class="form-control validate text-white" min="0" required/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="group" >Nhóm hàng</label>
                                        <select name="group"class="custom-select tm-select-accounts" id="group" required>
                                            <<option value >Chọn nhóm hàng</option>
                                            <c:forEach items="${lsNH}" var="o">
                                                <option value="${o.idNhomHang}" >${o.tenNhomHang}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="category" >Loại hàng</label>
                                        <select name="category" class="custom-select tm-select-accounts" id="category" required>

                                        </select>
                                    </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-lg-4">                            
                                <div class="form-group">
                                    <label for="picture" class="text-white" >Hình ảnh</label>&emsp;
                                    <input type="file" multiple id="gallery-photo-add" name="picture" class="text-white">
                                    <div class="gallery"></div>
                                </div>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary btn-block text-uppercase text-white" onclick="clicked(event)">Add Product Now</button>
                            </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="tm-footer row tm-mt-small">F
            <div class="col-12 font-weight-light">
                <p class="text-center mb-0 px-4 small">
                    Copyright &copy; <b>2018</b> All rights reserved. 

                    Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
                </p>
            </div>
        </footer> 
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script>
            $(function () {
                // Multiple images preview in browser
                var imagesPreview = function (input, placeToInsertImagePreview) {

                    if (input.files) {
                        var filesAmount = input.files.length;

                        for (i = 0; i < filesAmount; i++) {
                            var reader = new FileReader();

                            reader.onload = function (event) {
                                $($.parseHTML('<img style="width:170px;height:150px;padding:3px;">')).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
                            }

                            reader.readAsDataURL(input.files[i]);
                        }
                    }

                };

                $('#gallery-photo-add').on('change', function () {
                    imagesPreview(this, 'div.gallery');
                });
            });
        </script>
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
                $("#group").change(function () {
                    var id = $("#group :selected").attr('value');
                    $.ajax({
                        url: "GetLoaiHangByNhomHang",
                        type: "get",
                        data: {
                            id: id,
                        },
                        success: function (data) {
                            var row = document.getElementById("category");
                            row.innerHTML = data;
                        },
                        error: function () {
                            alert("Không thành công");
                        }
                    });
                });
            });
        </script>
    </body>
</html>

