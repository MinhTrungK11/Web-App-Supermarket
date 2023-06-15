<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>
<!DOCTYPE html>
<html lang="vi">

    <%@include file="/header.jsp" %>

    <body>
        <%@include file="/bar.jsp" %>

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>ĐƠN HÀNG</h2>
                            <div class="breadcrumb__option">
                                <a href="./index.html">Trang chủ </a>
                                <span>Đơn hàng</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Shoping Cart Section Begin -->
        <div class="col-lg-12">
            <div class="product__details__tab">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                           aria-selected="true">Hoàn thành</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                           aria-selected="false">Đang giao</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="tabs-1" role="tabpanel">
                        <div class="product__details__tab__desc">
                            <c:if test = "${listDH_HoanTat.size() > 0}">
                                <c:forEach var = "i" begin = "0" end = "${listDH_HoanTat.size()-1}">
                                    <section class="shoping-cart spad">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="shoping__cart__table">
                                                        <table>
                                                            <thead>
                                                                <tr>
                                                                    <th class="shoping__product">Sản phẩm</th>
                                                                    <th>Giá</th>
                                                                    <th>Tổng tiền</th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${listCTDH_HoanTat[i]}" var="d">
                                                                    <tr>
                                                                        <td class="shoping__cart__item">
                                                                            <img src="img/product/${d.chitietdonhangPK.idSanPham}.jpg" width="100" height="100" alt="">
                                                                            <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                                                                                               url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_52a89ac073ae5e7?zeroDateTimeBehavior=CONVERT_TO_NULL"
                                                                                               user = "b76102f2bc56ab"  password = "615044fc"/>
                                                                            <sql:query dataSource = "${snapshot}" var = "result">
                                                                                SELECT tenSanPham from sanpham where idSanPham=?
                                                                                <sql:param value="${d.chitietdonhangPK.idSanPham}"/>
                                                                            </sql:query>                                 
                                                                            <h5>${result.rows[0].tenSanPham}</h5>
                                                                            <h5 style="font-weight: bold; color: red"> x ${d.soLuong}</h5>
                                                                        </td>
                                                                        <td class="shoping__cart__price">
                                                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${d.thanhTien/d.soLuong}"/>đ
                                                                        </td>
                                                                        <td class="shoping__cart__total">
                                                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${d.thanhTien}"/>đ
                                                                        </td>
                                                                        <td
                                                                            <div class="shoping_received">
                                                                            <a class="primary-btn order-btn cart-btn-right" type="button" data-bs-toggle="modal" data-bs-target="#rating${d.chitietdonhangPK.idSanPham}">Đánh giá</a>
                                                                            </div>
                                                                        </td>
                                                                <div class="modal fade" id="rating${d.chitietdonhangPK.idSanPham}" tabindex="-1" aria-labelledby="ratingLabel" aria-hidden="true">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title" id="exampleModalLabel">Đánh giá sản phẩm</h5>
                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                            </div>
                                                                            <form action="/rating?idSP=${d.chitietdonhangPK.idSanPham}" method="post">
                                                                                <div class="modal-body">
                                                                                    <div class="mb-3">
                                                                                        <img src="img/product/${d.chitietdonhangPK.idSanPham}.jpg" width="100" height="100" alt=""/>
                                                                                        <h5 style="display: inline-block;">${result.rows[0].tenSanPham}</h5>
                                                                                    </div>
                                                                                    <div class="mb-3">
                                                                                        <label for="message-text" class="col-form-label">Nội dung:</label>
                                                                                        <textarea class="form-control" name="rating" required></textarea>
                                                                                    </div> 
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                                                                    <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                                                                                </div>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                            <thead>
                                                                <tr>
                                                                    <th class="shoping__product">Tổng đơn hàng: <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${listDH_HoanTat[i].tongThanhToan}"/>đ</th>
                                                                </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="tab-pane" id="tabs-2" role="tabpanel">
                        <div class="product__details__tab__desc">
                            <c:if test = "${listDH_DangGiao.size() > 0}">
                                <c:forEach var = "i" begin = "0" end = "${listDH_DangGiao.size()-1}">
                                    <section class="shoping-cart spad">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="shoping__cart__table">
                                                        <table>
                                                            <thead>
                                                                <tr>
                                                                    <th class="shoping__product">Sản phẩm</th>
                                                                    <th>Giá</th>
                                                                    <th>Số lượng</th>
                                                                    <th>Tổng tiền</th>
                                                                    <th></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${listCTDH_DangGiao[i]}" var="d">
                                                                    <tr>
                                                                        <td class="shoping__cart__item">
                                                                            <img src="img/product/${d.chitietdonhangPK.idSanPham}.jpg" width="100" height="100" alt="">
                                                                            <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                                                                                               url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_52a89ac073ae5e7?zeroDateTimeBehavior=CONVERT_TO_NULL"
                                                                                               user = "b76102f2bc56ab"  password = "615044fc"/>
                                                                            <sql:query dataSource = "${snapshot}" var = "result">
                                                                                SELECT tenSanPham from sanpham where idSanPham=?
                                                                                <sql:param value="${d.chitietdonhangPK.idSanPham}"/>
                                                                            </sql:query>                                 
                                                                            <h5>${result.rows[0].tenSanPham}</h5>                                                                                
                                                                        </td>
                                                                        <td class="shoping__cart__price">
                                                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${d.thanhTien/d.soLuong}"/>đ
                                                                        </td>
                                                                        <td class="shoping__cart__quantity">
                                                                            ${d.soLuong}
                                                                        </td>
                                                                        <td class="shoping__cart__total">
                                                                            <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${d.thanhTien}"/>đ
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                            <thead>
                                                                <tr>
                                                                    <th class="shoping__product">Tổng đơn hàng: <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${listDH_DangGiao[i].tongThanhToan}"/>đ</th>
                                                                </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="shoping_received">
                                                        <a href="/confirmorder?madh=${listDH_DangGiao[i].maDonHang}" class="primary-btn order-btn cart-btn-right">Đã nhận</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Shoping Order Section End -->
        <%@include file="/footer.jsp" %>
        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>


    </body>

</html>