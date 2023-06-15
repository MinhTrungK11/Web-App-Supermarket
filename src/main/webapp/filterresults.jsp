<%-- 
    Document   : search
    Created on : Nov 23, 2022, 9:14:18 PM
    Author     : letua
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <c:if test="${keyWord != null}">
                            <h2>Từ khóa: ${keyWord}</h2>
                        </c:if>
                        <c:if test="${tenLH != null}">
                            <h2>${tenLH}</h2>
                        </c:if>
                        <c:if test="${tenNH != null}">
                            <h2>${tenNH}</h2>
                        </c:if>
                        <c:if test="${min != null}">
                            <h2><fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${min}"/>đ - <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${max}"/>đ</h2>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Search Section Begin -->
        <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Kết quả</h2>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <c:forEach items="${ListSP}" var="i">
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                    <div class="featured__item">
                       <div class="featured__item__pic set-bg" data-setbg="img/product/${i.idSanPham}.jpg">
                           <c:if test="${i.giamGia > 0}">
                           <div class="product__discount__item"><div class="product__discount__item__pic"><div class="product__discount__percent">
                               -<fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${i.giamGia/i.giaBan*100}"/>%</div></div></div>
                           </c:if>
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="/detailproduct?idSP=${i.idSanPham}"><i class="fa fa-info-circle"></i></a></li>
                                <c:if test="${sessionScope.acc != null}">
                                    <li><a href="/addcart?idSP=${i.idSanPham}&gia=${i.giaBan-i.giamGia} ${ i.soLuong == 0  ? 'onclick="return false;"' : ''}"><i class="fa fa-shopping-cart"></i></a></li>
                                </c:if>
                                <c:if test="${sessionScope.acc == null}">
                                    <li><a href="/login"><i class="fa fa-shopping-cart"></i></a></li>
                                </c:if>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="/detailproduct?idSP=${i.idSanPham}">${i.tenSanPham}</a></h6>
                            <c:if test="${i.giamGia > 0}">
                                <h7><del><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</del></h7>
                            </c:if>
                            <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan-i.giamGia}"/>đ</h5>
                        </div>
                    </div>
                </div>
                </c:forEach> 
            </div>
            <div class="product__pagination" style="text-align: center;">
                <c:forEach begin="1"  end="${Endtotal}" var="i">                            
                        <a href="Page?index=${i}" >${i}</a>
                </c:forEach>         
            </div>
        </div>
    </section>
    <!-- Shoping Search Section End -->
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
