<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/error"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String dataForPieChart = (String) request.getAttribute("dataForPieChart");
%>
<!DOCTYPE html>
<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product Admin - Dashboard HTML Template</title>
        <link rel="stylesheet" type="text/css" href="Admin/css/account.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link href="https://use.fontawesome.com/releases/v5.0.1/css/all.css" rel="stylesheet">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="Admin/css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="Admin/css/bootstrap.min.css">
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="Admin/css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
        <script type="text/javascript">
            window.onload = function () {
                CanvasJS.addColorSet("customColorSet",
                [//colorSet Array
                "#F7604D",
                "#4ED6B8",
                "#A8D582",            
                ]);
                var pieChart = new CanvasJS.Chart("pieChartContainer", {
                    theme: "light2",
                    animationEnabled: true,
                    height: 480,
                    backgroundColor: "#435c70",
                    legend: {
                        verticalAlign: "bottom",
                        horizontalAlign: "center",
                        fontColor: "white",
                        fontSize: 20
                    },
                    data: [{
                            type: "pie",
                            showInLegend: true,
                            legendText: "{label}",
                            toolTipContent: "{label}: <strong>{y}%</strong>",
                            indexLabel: "{label} {y}%",
                            indexLabelFontColor: "white",
                            indexLabelFontSize: 15,
                            dataPoints: <%out.print(dataForPieChart);%>
                        }]
                });
                pieChart.render();

            }
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
                            <a class="nav-link active" href="AdminDashboard">
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
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block">
                            <h2 class="tm-block-title text-white">LATEST HITS</h2>
                            <canvas id="lineChart"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block">
                            <h2 class="tm-block-title text-white">PERFORMANCE</h2>
                            <canvas id="barChart"></canvas>
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller">
                            <h2 class="tm-block-title text-white" style="padding-bottom: 25px">THỐNG KÊ LỢI NHUẬN</h2>
                            <div class="tm-bg-primary-dark" id="pieChartContainer"></div>                        
                        </div>
                    </div>
                    <div class="col-sm-12 col-md-12 col-lg-6 col-xl-6 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-overflow">
                            <h2 class="tm-block-title">Notification List</h2>
                            <div class="tm-notification-items">
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-01.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Jessica</b> and <b>6 others</b> sent you new <a href="#"
                                                                                                           class="tm-notification-link">product updates</a>. Check new orders.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-02.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Oliver Too</b> and <b>6 others</b> sent you existing <a href="#"
                                                                                                                   class="tm-notification-link">product updates</a>. Read more reports.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-03.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Victoria</b> and <b>6 others</b> sent you <a href="#"
                                                                                                        class="tm-notification-link">order updates</a>. Read order information.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-01.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Laura Cute</b> and <b>6 others</b> sent you <a href="#"
                                                                                                          class="tm-notification-link">product records</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-02.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Samantha</b> and <b>6 others</b> sent you <a href="#"
                                                                                                        class="tm-notification-link">order stuffs</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-03.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Sophie</b> and <b>6 others</b> sent you <a href="#"
                                                                                                      class="tm-notification-link">product updates</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-01.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Lily A</b> and <b>6 others</b> sent you <a href="#"
                                                                                                      class="tm-notification-link">product updates</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-02.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Amara</b> and <b>6 others</b> sent you <a href="#"
                                                                                                     class="tm-notification-link">product updates</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                                <div class="media tm-notification-item">
                                    <div class="tm-gray-circle"><img src="Admin/img/notification-03.jpg" alt="Avatar Image" class="rounded-circle"></div>
                                    <div class="media-body">
                                        <p class="mb-2"><b>Cinthela</b> and <b>6 others</b> sent you <a href="#"
                                                                                                        class="tm-notification-link">product updates</a>.</p>
                                        <span class="tm-small tm-text-color-secondary">6h ago.</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 tm-block-col">
                        <div class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
                            <h2 class="tm-block-title text-white">ĐƠN HÀNG</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="tm-bg-primary-green text-white">TÀI KHOẢN</th>
                                        <th class="tm-bg-primary-green text-white">ĐỊA CHỈ</th>
                                        <th class="tm-bg-primary-green text-white">SỐ ĐIỆN THOẠI</th>
                                        <th class="tm-bg-primary-green text-white">TỔNG THANH TOÁN</th>
                                        <th class="tm-bg-primary-green text-white">PHƯƠNG THỨC</th>
                                        <th class="tm-bg-primary-green text-white">TÌNH TRẠNG</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listDonHang}" var="i">
                                        <tr>
                                            <td>${i.taiKhoan}</td>
                                            <td>${i.diaChi}</td>
                                            <td>${i.sdt}</td>
                                            <td>${i.tongThanhToan}</td>
                                            <td>${i.thanhToan}</td>
                                            <td>${i.tinhTrang}</td>
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

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="js/moment.min.js"></script>
        <!-- https://momentjs.com/ -->
        <script src="js/Chart.min.js"></script>
        <!-- http://www.chartjs.org/docs/latest/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <script src="js/tooplate-scripts.js"></script>

    </body>

</html>
