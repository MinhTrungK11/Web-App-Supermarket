<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" errorPage="/error"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<%

    String dataForBarChart;
    List<String> dataForLineChart;
    List<String> listUsernameForLineChart;
    try {
        dataForBarChart = request.getAttribute("dataForBarChart").toString();
        dataForLineChart = (ArrayList<String>) request.getAttribute("dataForLineChart");
        listUsernameForLineChart = (ArrayList<String>) request.getAttribute("listUsernameForLineChart");
    } catch (Exception ex) {
        dataForBarChart = null;
        dataForLineChart = null;
        listUsernameForLineChart = null;
    }

%>


<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product Admin - Dashboard HTML Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link href="https://use.fontawesome.com/releases/v5.0.1/css/all.css" rel="stylesheet">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="Admin/css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="Admin/css/templatemo-style.css">
        <link rel="stylesheet" type="text/css" href="Admin/css/account.css">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"></script>
        <!-- BootStrap DatePicker JS -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"
        ></script>
        <!-- BootStrap DatePicker CSS -->
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
            />


        <script type="text/javascript">
            window.onload = function () {

            <% if (dataForBarChart != null) { %>
                var barChart = new CanvasJS.Chart("barChartContainer", {
                    animationEnabled: true,
                    exportEnable: true,
                    backgroundColor: "#435c70",
                    axisX: {
                        labelFontColor: "white",
                        titleFontColor: "white",
                        lineColor: "white",
                        gridColor: "white",
                        labelAngle: 89,
                        interval: 1
                    },
                    axisY: {
                        minimum: 0,
                        labelFontColor: "white",
                        titleFontColor: "white",
                        lineColor: "white",
                        gridColor: "white",
                        tickLength: 1,
                        interval: 1
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        stepSize: 3
                                    }
                                }]
                        }
                    },
                    data: [{
                            type: "column",
                            dataPoints: <%out.print(dataForBarChart);%>
                        }]
                });
                barChart.render();
            <% }%>;

                var lineChart = new CanvasJS.Chart("lineChartContainer", {
                    animationEnabled: true,
                    exportEnable: true,
                    backgroundColor: "#435c70",
                    axisX: {
                        labelFontColor: "white",
                        titleFontColor: "white",
                        lineColor: "white",
                        gridColor: "white",
                        labelAngle: 89,
                        interval: 1
                    },
                    axisY2: {
                        includeZero: true,
                        minimum: 0,
                        labelFontColor: "white",
                        titleFontColor: "white",
                        lineColor: "white",
                        gridColor: "white",
                        tickLength: 1,
                        interval: 1
                    },
                    legend: {
                        cursor: "pointer",
                        verticalAlign: "top",
                        horizontalAlign: "center",
                        dockInsidePlotArea: true,
                        itemclick: toogleDataSeries,
                        fontColor: "white",
                        fontSize: 20,
                        markerMargin: 8,
                    },
                    toolTip: {
                        shared: true
                    },
                    data: [{
                            type: "line",
                            axisYType: "secondary",
                            lineThickness: 3,
                            name: "${listUsernameForLineChart[0]}",
                            showInLegend: true,
                            markerSize: 0,
                            dataPoints: <%out.print(dataForLineChart.get(0));%>,
                            whiskerColor: "white",
                            color: "rgb(75, 192, 192)",
                            legendMarkerColor: "white",
                            legendMarkerBorderThickness: 4
                        },
                        {
                            type: "line",
                            axisYType: "secondary",
                            lineThickness: 3,
                            name: "${listUsernameForLineChart[1]}",
                            showInLegend: true,
                            markerSize: 0,
                            dataPoints: <%out.print(dataForLineChart.get(1));%>,
                            color: "rgba(255,99,132,1)"
                        },
                        {
                            type: "line",
                            axisYType: "secondary",
                            lineThickness: 3,
                            name: "${listUsernameForLineChart[2]}",
                            showInLegend: true,
                            markerSize: 0,
                            dataPoints: <%out.print(dataForLineChart.get(2));%>,
                            color: "rgba(153, 102, 255, 1)"
                        }]
                });
                lineChart.render();

                function toogleDataSeries(e) {
                    if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                        e.dataSeries.visible = false;
                    } else {
                        e.dataSeries.visible = true;
                    }
                    lineChart.render();
                }
            };

        </script>
    </head>
    <body id="reportsPage">
        <div class="" id="home">
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
                            <li class="nav-item active">
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
            <div class="container">
                <div class="row">
                    <div class="col">
                        <p class="text-white mt-5 mb-5">Welcome back, <b>Admin</b></p>
                    </div>
                </div>
                <!-- row -->
                <div class="row tm-content-row">
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col" style="position: relative;">
                        <div class="tm-bg-primary-dark tm-block pt-2 pb-2">
                            <form class="form-group mb-2" method="POST" action="/AdminManageComment">
                                <div class='input-group date'>
                                    <input id="${dateForChart}" class="datepicker form-control text centered valid" type="text" value="${dateForChart}" name="dateForChart" style="height: 35px; color: white; border-radius: 4px;" required />
                                    <input id="${date1}" class="datepicker form-control text centered valid" type="hidden" value="${date1}" name="date1" required />
                                    <input id="${date2}" class="datepicker form-control text centered valid" type="hidden" value="${date2}" name="date2" required />
                                    <button type="submit" class="none tm-bg-primary-green text-white col-3 text" style="border-radius: 4px;">Tìm kiếm</button>
                                </div>
                            </form>
                            <h2 class="tm-block-title mb-2" style="color: white">Thống kê các bình luận của các ngày tháng ${dateForChart}</h2>
                            <div id="barChartContainer" class="tm-bg-primary-dark"></div>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col" style="position: relative;">
                        <div class="tm-bg-primary-dark tm-block" style="padding-top: 50px">
                            <h2 class="tm-block-title mb-2" style="color: white">Top 3 người đánh giá nhiều nhất tháng ${dateForChart}</h2>
                            <div id="lineChartContainer" class="tm-bg-primary-dark"></div>
                        </div>
                    </div>
                    <div class="col-12 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll" style="max-height: 600px">
                            <form class="form-group mb-2" id="DeleteComment2DateForm" name="DeleteComment2DateForm" method="POST" action="/AdminManageComment">
                                <div class='input-group date' style="gap: 20px 20px;">
                                    <input type="hidden" value="find" name="findOrDelete" id="findOrDelete" required />
                                    <input class="datepickerfordelete form-control text centered valid" type="text" value="${date1}" name="date1" style="height: 35px; color: white; border-radius: 4px;" required />
                                    <input class="datepickerfordelete form-control text centered valid" type="text" value="${date2}" name="date2" style="height: 35px; color: white; border-radius: 4px;" required />
                                    <button id="findButton" onclick="getCommentBetweenTwoDate()"  class="none tm-bg-primary-green text-white col-3 text" style="border-radius: 4px; max-width: 200px">TÌM KIẾM</button>
                                    <button type="button"  class="none tm-bg-primary-green text-white col-3 text cd-popup-trigger-for-delete-between-two-date" style="border-radius: 4px; max-width: 200px">XÓA</button>
                                </div>
                            </form>
                            <h2 class="tm-block-title text-white">Các đánh giá của người dùng</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="tm-bg-primary-green text-white">TÀI KHOẢN</th>
                                        <th class="tm-bg-primary-green text-white">SẢN PHẨM</th>
                                        <th class="tm-bg-primary-green text-white">THỜI GIAN</th>
                                        <th class="tm-bg-primary-green text-white">NỘI DUNG</th>
                                        <th class="tm-bg-primary-green text-white"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listComment}" var="i">
                                        <tr>
                                            <td>${i.taiKhoan.username}</td>
                                            <td>${i.idSanPham.tenSanPham}</td>
                                            <td>${i.thoiGian}</td>
                                            <td>${i.noiDung}</td>
                                            <td style="display: flex; justify-content: space-between">
                                                <button id="${i.idDanhGia}" class="none cd-popup-trigger-for-delete" type="button" style="color: red !important;"><i class="fas fa-minus-circle"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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

        <script>
            function getCommentBetweenTwoDate() {
                if (${date1 != null || date2 != null}) {
                    document.getElementById("findOrDelete").value = "find";
                    document.getElementById("DeleteComment2DateForm").submit();
                }
            }
            function submitDeleteBetweenTwoDate() {
                if (${date1 != null || date2 != null}) {
                    document.getElementById("findOrDelete").value = "delete";
                    document.getElementById("DeleteComment2DateForm").submit();
                    $('.delete-button').prop('onclick', null).off('click');
                }
            }
            jQuery(document).ready(function ($) {
                //open popup
                $('.cd-popup-trigger-for-delete-between-two-date').on('click', function (event) {
                    if (${date1 != null && date2 != null}) {
                        event.preventDefault();
                        $('.cd-popup').addClass('is-visible');
                        $('p').children('span').text("Bạn có chắc xóa những comment trong khoảng ${date1} và ${date2}");
                        $('.delete-button').addClass('is-visible-button');
                        $('.delete-button').click(submitDeleteBetweenTwoDate);
                    }
                });

                $('.cd-popup-trigger-for-delete').on('click', function (event) {
                    event.preventDefault();
                    $('.cd-popup').addClass('is-visible');
                    $('.cd-popup').addClass('is-visible');
                    $('.delete-button').attr('id', this.id);
                    $('.delete-button').addClass('is-visible-button');
                    $('p').children('span').text("Bạn có chắc xóa đánh giá này không ?");
                }),
                        $('.delete-button').on('click', function (event) {
                    var idComment = this.id;
                    var dateForChart = document.getElementById("${dateForChart}").value;
                    var date1 = document.getElementById("${date1}").value;
                    var date2 = document.getElementById("${date2}").value;
                    console.log(idComment);
                    console.log(dateForChart);
                    console.log(date1);
                    console.log(date2);
                    $.ajax({
                        url: "AdminManageComment",
                        type: "post",
                        data: {
                            idComment: idComment,
                            dateForChart: dateForChart,
                            date1: date1,
                            date2: date2
                        },
                        success: function () {
                            $('.delete-button').prop('onclick', null).off('click');
                            location.reload();
                        },
                        error: function () {
                            $('p').children('span').text("Xóa đánh giá không thành công");
                            $('.delete-button').removeClass('is-visible-button');
                            $('.delete-button').prop('onclick', null).off('click');
                        }
                    });
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


        <script type="text/javascript">
            $('.datepicker').datepicker({
                format: "mm-yyyy",
                viewMode: "months",
                minViewMode: "months"
            });

            $('.datepickerfordelete').datepicker({
                format: "dd/mm/yyyy"
            });
        </script>
    </body>

</html>
