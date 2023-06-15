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
                        <h2>Checkout</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Checkout</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h6><span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click here</a> to enter your code
                    </h6>
                </div>
            </div>
            <div class="checkout__form">
                <h4>Billing Details</h4>
                <form action="/doneorder?tongthanhtoan=${total+shipcost-total*voucher.phanTram/100}&voucher=${voucher.maVoucher}" method="post" enctype="application/x-www-form-urlencoded">
                    <div class="row">
                        <div class="col-lg-6 col-md-6">
                            <div class="checkout__input">
                                <p>Họ và tên<span>*</span></p>
                                <input type="text" name="hovaten" value="${sessionScope.acc.name}" class="checkout__input__add" required>
                            </div>
                            <div class="checkout__input">
                                <p>Địa chỉ<span>*</span></p>
                                <input type="text" name="diachi" value="${sessionScope.acc.address}" class="checkout__input__add" required>
                            </div>
                            <div class="checkout__input">
                                <p>SĐT<span>*</span></p>
                                <input type="text" name="sdt" value="${sessionScope.acc.phonenumber}" class="checkout__input__add" required>
                            </div>
                            <div class="checkout__input">
                                <p>Email<span>*</span></p>
                                <input type="email" name="email" value="${sessionScope.acc.email}" class="checkout__input__add" required>
                            </div>
                            <div class="checkout__input">
                                <p>Chú thích<span>*</span></p>
                                <input type="text" name="chuthich">
                            </div>
                        </div>
                        <div class="col-lg-6 col-md-6">
                            <div class="checkout__order">
                                <h4>Đơn hàng của bạn</h4>
                                <div class="checkout__order__products">Sản Phẩm <span>Tổng</span></div>
                                <ul>
                                    <c:forEach items="${ListSP}" var="i">
                                        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                                        url = "jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_52a89ac073ae5e7?zeroDateTimeBehavior=CONVERT_TO_NULL"
                                        user = "b76102f2bc56ab"  password = "615044fc"/>
                                        <sql:query dataSource = "${snapshot}" var = "result">
                                            SELECT tenSanPham from sanpham where idSanPham=?
                                            <sql:param value="${i.giohangPK.idSanPham}"/>
                                        </sql:query>
                                        <li>${result.rows[0].tenSanPham}<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.gia*i.soLuong}"/>đ</span></li>
                                    </c:forEach>
                                </ul>
                                <div class="checkout__order__subtotal">Tổng tiền hàng<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total}"/>đ</span></div>
                                <c:if test = "${voucher.phanTram>0}">
                                <div class="checkout__order__subtotal">Giảm giá<span>- <fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total*voucher.phanTram/100}"/>đ</span></div>
                                </c:if>
                                <c:if test="${total>=100000}">
                                    <div class="checkout__order__subtotal">Phí ship<span>Miễn phí</span></div>
                                </c:if>
                                <c:if test="${total<100000}">
                                    <div class="checkout__order__subtotal">Phí ship<span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${shipcost}"/>đ</span></div>
                                </c:if>
                                <div class="checkout__order__total">Tổng thanh toán <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${total+shipcost-total*voucher.phanTram/100}"/>đ</span></div>
                                
                                <div class="checkout__input__checkbox">
                                </div>
                                <button type="submit" class="site-btn">THANH TOÁN KHI NHẬN HÀNG</button>
                                <button type="submit" formaction="/vnpay_payment?tongthanhtoan=${total+shipcost-total*voucher.phanTram/100}&voucher=${voucher.maVoucher}" class="site-btn">THANH TOÁN BẰNG VNPAY</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
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