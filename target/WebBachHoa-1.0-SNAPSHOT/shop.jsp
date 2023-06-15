<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/error"%>


<!DOCTYPE html>
<html lang="zxx">


<head>
    <%@include file="/header.jsp" %>
</head>

<body>
    <%@include file="/bar.jsp" %>
    
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>G11 Shop</h2>
                        <div class="breadcrumb__option">
                            <a href="/home">Home</a>
                            <span>Shop</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-5">
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Department</h4>
                            <ul>
                                <li><a href="/FreshMeat">Fresh Meat</a></li>
                                <li><a href="/Vegettable">Vegetables</a></li>
                                <li><a href="/Fruit">Fruit & Nut Gifts</a></li>
<!--                                <li><a href="#">Fresh Berries</a></li>-->
<!--                                <li><a href="#">Ocean Foods</a></li>-->
<!--                                <li><a href="/FreshOnion">Butter & Eggs</a></li>-->
                                <li><a href="/FastFood">Fastfood</a></li>
                                <li><a href="/FreshOnion">Fresh Onion</a></li><!--
                                <li><a href="#">Papayaya & Crisps</a></li>-->
                                <li><a href="/Oatmeal">Oatmeal</a></li>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Price</h4>
                            <div class="price-range-wrap">
                                <form action="FilterbyPrice">
                                    <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                        data-min="12000" 
                                        data-max="200000">
                                        <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
                                        <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                        <span tabindex="0" class="ui-slider-handle ui-corner-all ui-state-default"></span>
                                    </div>
                                    <div class="range-slider">
                                        <div class="price-input">
                                            <input name="txtmin" type="text" id="minamount">
                                            <input name="txtmax" type="text" id="maxamount"> 
                                        </div>
                                    </div>
                                    <button type="submit" class="site-btn">Filter</button>
                                </form>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Latest Products</h4>
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
                                                    <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</h5>
                                                </div>
                                            </a>
                                        </c:forEach>                                  
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Sale Off</h2>
                        </div>
                        <div class="row">
                            <div class="product__discount__slider owl-carousel">
                            <c:forEach items="${ListGG}" var="i"  >   
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="img/product/${i.idSanPham}.jpg">
                                            <div class="product__discount__percent">-<fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${i.giamGia/i.giaBan*100}"/>%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="/detailproduct?idSP=${i.idSanPham}"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="/addcart?idSP=${i.idSanPham}&gia=${i.giaBan-i.giamGia}" ${ i.soLuong == 0  ? 'onclick="return false;"' : ''}><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">  
                                            <h6><a href="/detailproduct?idSP=${i.idSanPham}">${i.tenSanPham}</a></h6>
                                            <div class="product__item__price"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan-i.giamGia}"/>đ 
                                                <span><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan}"/>đ</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach> 
                            </div>
                        </div>
                    </div>
                    <div class="filter__item">
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort">
                                    <span>Sort By</span>
                                    <select>
                                        <option value="0">Default</option>
                                        <option value="0">Default</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found">
                                    <h6><span>16</span> Products found</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <span class="icon_grid-2x2"></span>
                                    <span class="icon_ul"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <c:forEach items="${ListSP}" var="i" >
                            <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__discount__item">
                                    <div class="product__discount__item__pic set-bg"
                                        data-setbg="img/product/${i.idSanPham}.jpg">
                                         <c:if test="${i.giamGia > 0}">
                                            <div class="product__discount__percent">-<fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${i.giamGia/i.giaBan*100}"/>%</div>
                                        </c:if>
                                        <ul class="product__item__pic__hover">
                                            <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                            <li><a href="/detailproduct?idSP=${i.idSanPham}"><i class="fa fa-info-circle"></i></a></li>
                                            <li><a href="/addcart?idSP=${i.idSanPham}&gia=${i.giaBan-i.giamGia}" ${ i.soLuong == 0  ? 'onclick="return false;"' : ''}><i class="fa fa-shopping-cart"></i></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__discount__item__text">  
                                        <h6><a href="/detailproduct?idSP=${i.idSanPham}">${i.tenSanPham}</a></h6>
                                        <h5><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${i.giaBan-i.giamGia}"/>đ</h5>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                        
                    </div>
                    <div class="product__pagination" style="text-align: center;">
                        <c:forEach begin="1"  end="${Endtotal}" var="i">                            
                                <a href="PhanTrang?index=${i}">${i}</a>
                        </c:forEach>         
                     </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="./index.html"><img src="img/logo.png" alt=""></a>
                        </div>
                        <ul>
                            <li>Address: 60-49 Road 11378 New York</li>
                            <li>Phone: +65 11.188.888</li>
                            <li>Email: hello@colorlib.com</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
                    <div class="footer__widget">
                        <h6>Useful Links</h6>
                        <ul>
                            <li><a href="#">About Us</a></li>
                            <li><a href="#">About Our Shop</a></li>
                            <li><a href="#">Secure Shopping</a></li>
                            <li><a href="#">Delivery infomation</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Our Sitemap</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">Who We Are</a></li>
                            <li><a href="#">Our Services</a></li>
                            <li><a href="#">Projects</a></li>
                            <li><a href="#">Contact</a></li>
                            <li><a href="#">Innovation</a></li>
                            <li><a href="#">Testimonials</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-12">
                    <div class="footer__widget">
                        <h6>Join Our Newsletter Now</h6>
                        <p>Get E-mail updates about our latest shop and special offers.</p>
                        <form action="#">
                            <input  type="text" placeholder="Enter your mail">
                            <button type="submit" class="site-btn">Subscribe</button>
                        </form>
                        <div class="footer__widget__social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer__copyright">
                        <div class="footer__copyright__text"><p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p></div>
                        <div class="footer__copyright__payment"><img src="img/payment-item.png" alt=""></div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->
   
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
<script>
    var rangeSlider = $(".price-range"),
        minamount = $("#minamount"),
        maxamount = $("#maxamount"),
        minPrice = rangeSlider.data('min'),
        maxPrice = rangeSlider.data('max');
    rangeSlider.slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [minPrice, maxPrice],
        slide: function (event, ui) {
            minamount.val(ui.values[0]);
            maxamount.val(ui.values[1]);
        }
    });
    minamount.val(rangeSlider.slider("values", 0));
    maxamount.val(rangeSlider.slider("values", 1));
</script>
