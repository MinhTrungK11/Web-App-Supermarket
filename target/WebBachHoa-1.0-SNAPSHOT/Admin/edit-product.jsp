<%-- 
    Document   : edit-product
    Created on : Oct 19, 2022, 9:13:19 PM
    Author     : mrtru
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Edit Product - Dashboard Admin Template</title>
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
        <script src="./Admin/js/jquery-3.3.1.min.js"></script>
        <!--        https://jquery.com/download/ -->
        <script src="./Admin/jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!--        https://jqueryui.com/download/ -->
        <script src="./Admin/js/bootstrap.min.js"></script>
        <!--        https://getbootstrap.com/ -->
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
                                <h2 class="tm-block-title d-inline-block">Edit Product</h2>
                            </div>
                        </div>
                        <div class="row tm-edit-product-row">
                            <div class="col-xl-6 col-lg-6 col-md-12">
                                <form action="EditProduct" method="post" class="tm-edit-product-form" enctype="multipart/form-data">
                                    <div class="form-group mb-3">
                                        <input type="text" id="id" value="${product.idSanPham}" name="id" class="form-control text-white" style="background-color: #54657d" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="name"
                                            >Tên sản phẩm
                                        </label>
                                        <input
                                            id="name"
                                            name="name"
                                            type="text"
                                            value="${product.tenSanPham}"
                                            class="form-control validate text-white"
                                            required
                                            />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="qty"
                                            >Số lượng
                                        </label>
                                        <input
                                            id="stock"
                                            name="qty"
                                            type="number"
                                            value="${product.soLuong}"
                                            class="form-control validate text-white"
                                            min="0"
                                            required
                                            />
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label
                                                for="expire_date"
                                                >Giá nhập
                                            </label>
                                            <input
                                                id="import_price"
                                                name="import_price"
                                                type="number"
                                                value="${product.giaNhap}"
                                                class="form-control validate text-white"
                                                min="0"
                                                required
                                                />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label
                                                for="price"
                                                >Giá bán
                                            </label>
                                            <input
                                                id="stock"
                                                name="price"
                                                type="number"
                                                value="${product.giaBan}"
                                                class="form-control validate text-white"
                                                min="0"
                                                required
                                                />
                                        </div>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="discount"
                                            >Discount
                                        </label>
                                        <input
                                            id="stock"
                                            name="discount"
                                            type="number"
                                            class="form-control validate text-white"
                                            value="${product.giamGia}"
                                            min="0"
                                            required
                                            />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label
                                            for="description"
                                            >Mô tả</label
                                        >
                                        <textarea                    
                                            class="form-control validate tm-small text-white"
                                            rows="5"
                                            name="description"
                                            >${product.moTa}</textarea>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="expiry">HSD</label>
                                        <input type="date" name="expiry" id="date" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${product.hsd}" type="date"></fmt:formatDate>" class="form-control validate hasDatepicker text-white">
                                        </div>
                                        <div class="form-group mb-3">
                                            <label
                                                for="group"
                                                >Nhóm hàng</label
                                            >
                                            <select
                                                class="custom-select tm-select-accounts"
                                                id="group"
                                                name="group"
                                                class='text-white'
                                                required
                                                >
                                            <c:forEach items="${lsNH}" var="o">
                                                <option value="${o.idNhomHang}" ${product.idNhomHang.idNhomHang == o.idNhomHang ? 'selected' : ''}>${o.tenNhomHang}</option> 
                                            </c:forEach>
                                        </select>
                                    </div> 
                                    <div class="form-group mb-3">
                                        <label
                                            for="category"
                                            >Loại hàng</label
                                        >
                                        <select
                                            class="custom-select tm-select-accounts"
                                            id="category"
                                            name="category"
                                            required
                                            >
                                            <c:forEach items="${lsLH}" var="o">
                                                <option value="${o.idLoaiHang}" ${product.idLoaiHoang.idLoaiHang == o.idLoaiHang ? 'selected' : ''}>${o.tenLoaiHang}</option> 
                                            </c:forEach>
                                        </select>
                                    </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                <div class="form-group mb-3">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Hình ảnh</th>
                                                <th scope="col">Chức năng</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="ctx" value="${pageContext.request.contextPath}" /> 
                                            <c:forEach items="${image}" var="o">
                                                <tr>
                                                    <th scope="row" style="color:black;" >${o.idPc}</th>
                                                    <td>
                                                        <img alt="picture" src="${ctx}/img/product/${o.picture}" style="width:100px;height:90px">
                                                    </td>
                                                    <td align='center'>       
                                                         <a class="delete tm-product-delete-link" id="${o.idPc}">
                                                             <i class="fa fa-trash" aria-hidden="true" style="color: red"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="form-group mb--3">
                                    <label for="picture" class="text-white">Hình ảnh</label>&emsp;
                                    <input type="file" multiple id="gallery-photo-add" name="picture">
                                    <div class="gallery"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-block text-uppercase" onclick="clicked(event)">Update Now</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-white mb-0 px-4 small">
                Copyright &copy; All rights reserved. 

                Design: <a rel="nofollow noopener" href="https://templatemo.com" class="tm-footer-link">Template Mo</a>
            </p>
        </div>
    </footer> 
    <script src="./Admin/js/jquery-3.3.1.min.js"></script>
    <script src="./Admin/jquery-ui-datepicker/jquery-ui.min.js"></script>
    <script src="./Admin/js/bootstrap.min.js"></script>
    <script>
                                $(function () {
                                    // Multiple images preview in browser
                                    var imagesPreview = function (input, placeToInsertImagePreview) {

                                        if (input.files) {
                                            var filesAmount = input.files.length;

                                            for (i = 0; i < filesAmount; i++) {
                                                var reader = new FileReader();

                                                reader.onload = function (event) {
                                                    $($.parseHTML('<img style="width:100px;height:90px;padding:3px;">')).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
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
        $(document).ready(function () {
            // crating new click event for save button
            $(".delete").click(function () {
                var id = +this.id;
                var confirmalert = confirm("Are you sure?");
                if (confirmalert == true)
                {
                    $.ajax({
                        url: "DeletePicture",
                        type: "post",
                        data: {
                            id: id,
                        },
                        success: function () {
                            alert('Xóa thành công');
                            location.reload();
                        }
                    });
                }
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $("#group").change(function () {
                var id = $("#group option:selected").attr('value');
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

