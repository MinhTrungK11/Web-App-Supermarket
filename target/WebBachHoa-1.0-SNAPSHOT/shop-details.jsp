<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>
<!DOCTYPE html>
<html lang="vi">

    <link rel="stylesheet" href="css/comment.css" type="text/css">
    <%@include file="/header.jsp" %>

    <body>
        <%@include file="/bar.jsp" %>

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="breadcrumb__text">
                            <h2>Vegetable’s Package</h2>
                            <div class="breadcrumb__option">
                                <a href="./index.html">Home</a>
                                <a href="./index.html">Vegetables</a>
                                <span>Vegetable’s Package</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Product Details Section Begin -->
        <section class="product-details spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="product__details__pic">
                            <div class="product__details__pic__item">
                                <img class="product__details__pic__item--large"
                                     src="img/product/${SP.idSanPham}.jpg" alt="">
                            </div>
                            <div class="product__details__pic__slider owl-carousel">
                                <img data-imgbigurl="img/product/${SP.idSanPham}.jpg"
                                     src="img/product/${SP.idSanPham}.jpg" alt="">
                                <c:forEach var="i" begin="1" end="3" step="1">
                                    <img data-imgbigurl="img/product/${SP.idSanPham}.${i}.jpg"
                                         src="img/product/${SP.idSanPham}.${i}.jpg" alt="">
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="product__details__text">
                            <h3>${SP.tenSanPham}</h3>
                            <div class="product__details__rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star-half-o"></i>
                                <span>(${slDG} reviews)</span>
                            </div>
                            <div class="product__details__price">
                                <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${SP.giaBan-SP.giamGia}"/>đ</span>
                                <c:if test="${SP.giamGia > 0}">
                                    <h7><del><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${SP.giaBan}"/>đ</del></h7>
                                    </c:if>
                            </div>
                            <form method="post" action="/addcart?idSP=${SP.idSanPham}&gia=${SP.giaBan-SP.giamGia}">
                                <div class="product__details__quantity">
                                    <div class="quantity">
                                        <div class="pro-qty">
                                            <input type="text" name="soluong" value="1">
                                        </div>
                                    </div>
                                </div>
                                <button type="submit" class="site-btn" ${ SP.soLuong == 0   ? 'disabled="disabled"' : ''}>Thêm vào giỏ</button>
                            </form>
                            <!--                        <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>-->
                            <ul>
                                <c:if test="${SP.soLuong>0}"> 
                                    <li><b>Tình trạng</b> <span>Hàng có sẵn</span></li>
                                    </c:if>
                                    <c:if test="${SP.soLuong==0}"> 
                                    <li><b>Tình trạng</b> <span>Hết hàng</span></li>
                                    </c:if>
                                <li><b>Shipping</b> <span><samp>Giao hàng hỏa tốc </samp>nội địa HCM & Hà Nội</span></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="product__details__tab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                                       aria-selected="true">Mô Tả</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                                       aria-selected="false">Bình Luận</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                    <div class="product__details__tab__desc">
                                        <h6>Thông tin sản phẩm</h6>
                                        <p>${SP.moTa}</p>
                                    </div>
                                </div>
                                <div class="tab-pane" id="tabs-2" role="tabpanel">
                                    <div class="product__details__tab__desc">
                                        <div class="container mt-5">
                                            <div class="d-flex justify-content-center row">
                                                <div class="col-md-8">
                                                    <div class="d-flex flex-column comment-section">
                                                        <c:forEach items="${ListDG}" var="i">
                                                            <div class="bg-white p-2">
                                                                <div class="d-flex flex-row user-info"><img class="rounded-circle" src="/img/avt_comment.png" width="40">
                                                                    <div class="d-flex flex-column justify-content-start ml-2"><span class="d-block font-weight-bold name">@${i.taiKhoan.username}</span><span class="date text-black-50">${i.thoiGian}</span></div>
                                                                </div>
                                                                <div class="mt-2">
                                                                    <p class="comment-text">${i.noiDung}</p>
                                                                </div>
                                                            </div>
                                                            <div class="bg-white">
                                                                <div class="d-flex flex-row fs-12">
                                                                    <div class="like p-2 cursor"><i class="fa fa-thumbs-o-up"></i><span class="ml-1">Like</span></div>
                                                                    <div class="like p-2 cursor"><i class="fa fa-commenting-o"></i><span class="ml-1">Comment</span></div>
                                                                    <div class="like p-2 cursor"><i class="fa fa-share"></i><span class="ml-1">Share</span></div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Product Details Section End -->

        <!-- Related Product Section Begin -->
        <section class="related-product">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title related__product__title">
                            <h2>Sản phẩm tương tự</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${SPTT}" var="sptt">
                        <div class="col-lg-3 col-md-4 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="img/product/${sptt.idSanPham}.jpg">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="/detailproduct?idSP=${sptt.idSanPham}"><i class="fa fa-info-circle"></i></a></li>
                                        <li><a href="/addcart?idSP=${sptt.idSanPham}&gia=${sptt.giaBan}"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="#">${sptt.tenSanPham}</a></h6>
                                    <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${sptt.giaBan}"/>đ</h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Related Product Section End -->

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

</html><%-- 
    Document   : shop-details
    Created on : Oct 26, 2022, 12:54:00 PM
    Author     : letua
--%>
