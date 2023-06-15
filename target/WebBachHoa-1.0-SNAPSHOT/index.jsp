<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--<%@include file="/modalimage.jsp" %>--%>
<html lang="vi">

    <%@include file="/header.jsp" %>

<body>
     <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

     <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="img/logo.png" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__auth">
                <a href="login.html"><i class="fa fa-user"></i> Login</a>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="/home">Home</a></li>
                <li><a href="/shopgrid">Shop</a></li>
                <li><a href="#">Pages</a>
                    <ul class="header__menu__dropdown">
                        <li><a href="/shopgrid">Shoping Cart</a></li>
                        <li><a href="./checkout.html">Check Out</a></li>
                        <li><a href="./blog-details.html">Blog Details</a></li>
                    </ul>
                </li>
                <li><a href="./blog.html">Blog</a></li>
                <li><a href="./contact.html">Contact</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="facebook.com/anhleom10"><i class="fa fa-facebook"></i></a>
            <a href="facebook.com/anhleom10"><i class="fa fa-twitter"></i></a>
            <a href="facebook.com/anhleom10"><i class="fa fa-linkedin"></i></a>
            <a href="facebook.com/anhleom10"><i class="fa fa-pinterest-p"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> onlinestoreg11@gmail.com</li>
                <li>Miễn phí vẫn chuyển từ 100.000đ</li>
            </ul>
        </div>
    </div>
     <!--Humberger End -->

     <!--Header Section Begin --> 
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> onlinestoreg11@gmail.com</li>
                                <li>Miễn phí vẫn chuyển từ 100.000đ</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="https://www.facebook.com/anhleom10/"><i class="fa fa-facebook"></i></a>
                                <a href="https://www.facebook.com/anhleom10/"><i class="fa fa-twitter"></i></a>
                                <a href="https://www.facebook.com/anhleom10/"><i class="fa fa-linkedin"></i></a>
                                <a href="https://www.facebook.com/anhleom10/"><i class="fa fa-pinterest-p"></i></a>
                            </div>
                            <div class="header__top__right__auth">
                                <c:if test="${sessionScope.acc != null}">
                                    <a href="/profile"><i class="fa fa-user"></i> Xin chào ${sessionScope.acc.name}</a>
                                </c:if>
                                <c:if test="${sessionScope.acc == null}">
                                    <a href="/login"><i class="fa fa-user"></i> Login</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="/home"><img src="img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="/home">Home</a></li>
                            <li><a href="/shopgrid">Shop</a></li>
                            <li><a href="#">Pages</a>
                                <ul class="header__menu__dropdown">
                                    <li><a href="/shopgrid">Shoping Cart</a></li>
                                    <li><a href="">Check Out</a></li>
                                    <li><a href="">Blog Details</a></li>
                                </ul>
                            </li>
                            <li><a href="">Blog</a></li>
                            <li><a href="">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="/cart"><i class="fa fa-shopping-bag"></i> <span>${slTrongGio}</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
     <!-- Header Section End --> 
     
     <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>Danh Mục</span>
                        </div>
                        <ul>
                            <c:forEach items="${ListNH}" var="i">
                            <li><a href="#${i.idNhomHang}" data-bs-toggle="collapse">${i.tenNhomHang}</a>
                                <div class="collapse" id="${i.idNhomHang}">
                                    <c:forEach items="${ListLH}" var="t">
                                        <c:if test="${t.idNhomHang.idNhomHang == i.idNhomHang}">
                                            <span><a href="category?idLoaiHang=${t.idLoaiHang}&tenLH=${t.tenLoaiHang}">‣ ${t.tenLoaiHang}</a></span>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </li>
                            </c:forEach>
                            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="search">
                                <div class="hero__search__categories">
                                    All Categories
                                    <span class="arrow_carrot-down"></span>
                                </div>
                                <input name="txt" type="text" placeholder="What do yo u need?">
                                <button type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>0397542892</h5>
                                <span>Hỗ trợ 24/7</span>
                            </div>
                        </div>
                    </div>
                    <div class="hero__item set-bg" data-setbg="img/hero/banner.jpg">
                        <div class="hero__text">
                            <span>Hoa Quả Tươi</span>
                            <h2>Rau <br />Sạch 100%</h2>
                            <p>Miễn phí vẫn chuyển từ 100.000đ</p>
                            <a href="/shopgrid" class="primary-btn">MUA NGAY</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                    <c:forEach items="${ListNH}" var="i">
                        <div class="col-lg-3">
                            <div class="categories__item set-bg" data-setbg="img/categories/cat-${i.idNhomHang}.jpg">
                                <h5><a href="groupsp?idNhomHang=${i.idNhomHang}&tenNH=${i.tenNhomHang}">${i.tenNhomHang}</a></h5>
                            </div>
                        </div>
                    </c:forEach> 
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Sản Phẩm Nổi Bật</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                            <li><a href="/all">All</a></li>
                            <li> <a href="/orange">Oranges</a> </li>
                            <li><a href="/Freshmeat">Fresh Meat</a></li>
                            <li><a href="/vegetable">Vegetables</a></li>
                            <li><a href="/fastfood">Fastfood</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <c:forEach items="${ListSP}" var="i" begin="0" end="7" step="1">
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
                                <li><a href="/addcart?idSP=${i.idSanPham}&gia=${i.giaBan-i.giamGia}" ${ i.soLuong == 0  ? 'onclick="return false;"' : ''}><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="/detailproduct?idSP=${i.idSanPham}">${i.tenSanPham}</a></h6>
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
    <!-- Featured Section End -->

    <!-- Banner Begin -->
    <div class="banner">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-1.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="banner__pic">
                        <img src="img/banner/banner-2.jpg" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Banner End -->

    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Sản Phảm mới</h4>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                                <c:forEach items="${List}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="latest-prdouct__slider__item">
                                <c:forEach items="${List}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Top đánh giá</h4>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                                <c:forEach items="${ListTop}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="latest-prdouct__slider__item">
                                <c:forEach items="${ListTop}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <span><h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5></span>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Top giảm giá</h4>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                               <c:forEach items="${ListGG}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <span><h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5></span>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="latest-prdouct__slider__item">
                                <c:forEach items="${ListGG}" var="i" begin="0" end="2" step="1" >
                                    <a href="/detailproduct?idSP=${i.idSanPham}" class="latest-product__item">
                                        <div class="latest-product__item__pic">
                                            <img src="img/product/${i.idSanPham}.jpg" alt="">
                                        </div>
                                        <div class="latest-product__item__text">
                                            <h6>${i.tenSanPham}</h6>
                                            <span><h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5></span>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Latest Product Section End -->

    <!-- Blog Section Begin -->
    <!-- Blog Section End -->
        <!-- Hero Section End -->
        <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Liên Hệ Với Chúng Tôi</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Liên Hệ</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_phone"></span>
                        <h4>SĐT</h4>
                        <p>0397542892</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_pin_alt"></span>
                        <h4>Địa chỉ</h4>
                        <p>128, Hàn Thuyên, Thủ Đức</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_clock_alt"></span>
                        <h4>Open time</h4>
                        <p>6:00am - 23:00pm</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_mail_alt"></span>
                        <h4>Email</h4>
                        <p>onlinestoreg11@gmail.com</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->
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