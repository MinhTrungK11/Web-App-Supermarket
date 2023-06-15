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
                        <h2>GIỎ HÀNG</h2>
                        <div class="breadcrumb__option">
                            <a href="./home">Trang chủ </a>
                            <span>Giỏ hàng</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <form action="/updatecart" method="post">
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
                                <c:forEach items="${ListSP}" var="i">
                                <tr>
                                    <td class="shoping__cart__item">
                                        <img src="img/product/${i.giohangPK.idSanPham}.jpg" width="100" height="100" alt="">
                                        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                                        url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_52a89ac073ae5e7?zeroDateTimeBehavior=CONVERT_TO_NULL"
                                        user = "b76102f2bc56ab"  password = "615044fc"/>
                                        <sql:query dataSource = "${snapshot}" var = "result">
                                            SELECT tenSanPham from sanpham where idSanPham=?
                                            <sql:param value="${i.giohangPK.idSanPham}"/>
                                        </sql:query>                                 
                                        <h5>${result.rows[0].tenSanPham}</h5>                                                                                
                                    </td>
                                    <td class="shoping__cart__price">
                                        <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.gia}"/>đ
                                    </td>
                                    <td class="shoping__cart__quantity">
                                        <div class="quantity">
                                            <div class="pro-qty">
                                                <input type="text" name="quantity" value="${i.soLuong}">
                                            </div>
                                        </div>
                                    </td>
                                    <td class="shoping__cart__total">
                                        <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.gia*i.soLuong}"/>đ
                                    </td>
                                    <td class="shoping__cart__item__close">
                                        <a href="deletecart?idSP=${i.giohangPK.idSanPham}" onclick="return confirm('Xóa sản phẩm khỏi giỏ?');"><span class="icon_close"></span></a>
                                    </td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <input type="submit" class="primary-btn cart-btn cart-btn-right" value="Cập nhật">
                    </div>
                </div>
            </div>
            </form>
            <div class="row">
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            <h5>Mã giảm giá</h5>
                            <form action="/cart">
                                <input type="text" name="voucher" value="${voucher.maVoucher}">
                                <button type="submit" class="site-btn">Áp dụng</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <c:set var="total" value="${0}"/>
                            <c:forEach var="i" items="${ListSP}">
                                <c:set var="total" value="${total + i.gia*i.soLuong}" />
                            </c:forEach>
                            <li>Tổng tiền hàng<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}"/>đ</span></li>
                            <c:if test="${total>=100000}">
                                <li>Tiền ship<span>Miễn phí</span></li>
                            </c:if>
                            <c:if test="${total<100000}">
                                <li>Tiền ship<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${shipcost}"/>đ</span></li>
                            </c:if>
                            <li>Voucher<span>- <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total*voucher.phanTram/100}"/>đ</span></li>
                            <c:if test="${total>=100000}">
                                <li>Tổng thanh toán <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total-total*voucher.phanTram/100}"/>đ</span></li>
                            </c:if>
                            <c:if test="${total<100000}">
                                <li>Tổng thanh toán <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total+shipcost-total*voucher.phanTram/100}"/>đ</span></li>
                            </c:if>
                            
                        </ul>
                        <a href="/checkout?total=${total}&voucher=${voucher.maVoucher}" class="primary-btn" ${ ListSP.size() == 0  ? 'onclick="return false;"' : ''}>ĐẶT HÀNG</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Shoping Cart Section End -->
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