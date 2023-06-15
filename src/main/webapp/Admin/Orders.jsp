<%-- 
    Document   : product
    Created on : Oct 19, 2022, 9:09:59 PM
    Author     : mrtru
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Product Page - Admin HTML Template</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

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
        <link rel="stylesheet" href="./Admin/css/popup.css">
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
                            <a class="nav-link active" href="ManageOrder">
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
                <div class="col-xl-15 col-lg-15 col-md-15 col-sm-15">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-xl-15 col-lg-15 col-md-12">
                                <h2 class="tm-block-title">Danh sách đơn hàng</h2>
                                <input id="myInput" type="text" placeholder="Search..">
                                <br><br>
                                <div class="row" style="gap: 0 20px; padding-left: 15px">
                                    <p>
                                        <label class="text-white">Ngày bắt đầu:</label>
                                        <input type="date" name="ngayBatDau" id="ngayBatDau">
                                    </p>
                                    <p>
                                        <label class="text-white">Ngày kết thúc:</label>
                                        <input type="date" name="ngayKetThuc" id="ngayKetThuc">
                                    </p>
                                    <p>
                                        <button style="background-color: #54657d">Filter</button>
                                    </p>
                                </div>
                                <div class="tm-product-table-container">  
                                    <table class="table table-hover tm-table-small tm-product-table">
                                        <thead>
                                            <tr>
                                                <th scope="col" style="text-align: center" >Tài khoản</th>
                                                <th scope="col" style="text-align: center" width="20%" >Họ tên</th>
                                                <th width="30%" scope="col" style="text-align: center">Địa chỉ</th>
                                                <th scope="col" style="text-align: center" >SĐT</th>
                                                <th scope="col" style="text-align: center" >Tổng tiền</th>
                                                <th scope="col" style="text-align: center" >Thanh toán</th>
                                                <th scope="col" style="text-align: center" >Tình trạng</th>
                                                <th scope="col" style="text-align: center"width="20%" >Ngày đặt</th>
                                                <th scope="col" style="text-align: center" >Chú thích</th>
                                                <th scope="col">&nbsp;</th>
                                                <th scope="col">&nbsp;</th>
                                            </tr>
                                        </thead>
                                        <tbody id="myTable">
                                            <c:forEach items="${lsDonhang}" var="o">
                                                <tr>
                                                    <td>${o.taiKhoan}</td>
                                                    <td>${o.hoVaTen}</td>
                                                    <td>${o.diaChi}</td>
                                                    <td>${o.sdt}</td>
                                                    <td>${o.tongThanhToan}</td>
                                                    <td>${o.thanhToan}</td>
                                                    <td>
                                                        <c:set var = "theString" value = "${o.tinhTrang}"/>
                                                        <c:choose>
                                                            <c:when test="${fn:contains(theString,'Hoàn tất')}">
                                                                <a>
                                                                    <i class="fa fa-check-circle" style="color:blue"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:when test="${fn:contains(theString,'Đã nhận')}">
                                                                <a>
                                                                    <i class="fa fas fa-box" style="color:green"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a>
                                                                    <i class="fas fa-shipping-fast" style="color:red"></i>
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>                                        
                                                    </td>
                                                    <td><fmt:formatDate pattern = "MM-dd-yyyy" value = "${o.ngayDat}"></fmt:formatDate></td>
                                                    <td>${o.chuThich}</td>
                                                    <td>
                                                        <a type="button" id="${o.maDonHang}" class="details tm-product-delete-link">
                                                            <i class="fas fa-eye" style="color:cyan"></i>
                                                        </a>
                                                    </td>
                                                    <td>
                                                        <a type="button" id="${o.maDonHang}" class="confirm tm-product-delete-link">
                                                            <i class="fas fa-check" style="color:cyan"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- table container -->
                            </div>
                        </div>
                    </div>
                </div>
            </div
        </div>
    </div>                    

    <div class="loginPopup">
        <div class="formPopup tm-bg-primary-dark" id="popupForm">
            <a style="color:red" class="close" onclick="closeForm()">&times;</a>
            <h2 style="color: black;text-align: center; background-color: #54657d">Chi tiết đơn hàng</h2>
            <div class="content">
                <table class="table table-hover tm-table-small tm-product-table">
                    <thead>
                        <tr>
                            <th scope="col" style="text-align: center" >Mã sản phẩm</th>
                            <th scope="col" style="text-align: center" >Tên sản phẩm</th>
                            <th scope="col" style="text-align: center" >Số lượng</th>
                            <th scope="col" style="text-align: center" >Thành tiền</th>
                        </tr>
                    </thead>
                    <tbody id="Popupcontent">

                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <footer class="tm-footer row tm-mt-small">
        <div class="col-12 font-weight-light">
            <p class="text-center text-white mb-0 px-4 small">
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
                function openForm() {
                    document.getElementById("popupForm").style.display = "block";
                }
                function closeForm() {
                    document.getElementById("popupForm").style.display = "none";
                }
    </script>
    <script>
        $(document).ready(function () {
            // crating new click event for save button
            $(".details").click(function () {
                var id = +this.id;
                $.ajax({
                    url: "DetailOrder",
                    type: "get",
                    data: {
                        id: id,
                    },
                    success: function (data) {
                        var row = document.getElementById("Popupcontent");
                        row.innerHTML = data;
                        openForm();
                    }
                });
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            // crating new click event for save button
            $(".confirm").click(function () {
                var id = +this.id;
                var confirmalert = confirm("Are you sure?");
                if (confirmalert == true)
                {
                    $.ajax({
                        url: "AdminConfirmOrder",
                        type: "get",
                        data: {
                            id: id,
                        },
                        success: function (data) {
                            alert(data);
                            location.reload();
                        },
                        error: function () {
                            alert("Cập nhật trạng thái không thành công");
                        }
                    });
                }
            });
        });
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
            // crating new click event for save button
            $("button").click(function () {
                var ngayBatDau = $("#ngayBatDau").val();
                var ngayKetThuc = $("#ngayKetThuc").val();
                if (new Date(ngayBatDau).getTime() <= new Date(ngayKetThuc).getTime()) {
                    $.ajax({
                        url: "FilterDonHangByDate",
                        type: "get",
                        data: {
                            ngayBatDau: ngayBatDau,
                            ngayKetThuc: ngayKetThuc
                        },
                        success: function (data) {
                            var row = document.getElementById("myTable");
                            row.innerHTML = data;
                            //Xem chi tiết đơn hàng
                            $(document).ready(function () {
                                // crating new click event for save button
                                $(".details").click(function () {
                                    var id = +this.id;
                                    $.ajax({
                                        url: "DetailOrder",
                                        type: "get",
                                        data: {
                                            id: id,
                                        },
                                        success: function (data) {
                                            var row = document.getElementById("Popupcontent");
                                            row.innerHTML = data;
                                            openForm();
                                        }
                                    });
                                });
                            });
                            //Xác nhận đơn hàng
                            $(document).ready(function () {
                                // crating new click event for save button
                                $(".confirm").click(function () {
                                    var id = +this.id;
                                    var confirmalert = confirm("Are you sure?");
                                    if (confirmalert == true)
                                    {
                                        $.ajax({
                                            url: "AdminConfirmOrder",
                                            type: "get",
                                            data: {
                                                id: id,
                                            },
                                            success: function (data) {
                                                alert(data);
                                                location.reload();
                                            },
                                            error: function () {
                                                alert("Cập nhật trạng thái không thành công");
                                            }
                                        });
                                    }
                                });
                            });
                        },
                        error: function () {
                            alert("Thao tác thực hiện thất bại");
                        }
                    });
                } else {
                    alert("Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết kết thúc!");
                }
            });
        });
    </script>
</body>
</html>
