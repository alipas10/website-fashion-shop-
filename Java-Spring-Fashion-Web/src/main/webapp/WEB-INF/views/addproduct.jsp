<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>Pooled Admin Panel Category Flat Bootstrap Responsive Web Template | Home :: w3layouts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Pooled Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link href="<c:url value="/resource/template/css/style/style.css" />" rel='stylesheet' type='text/css'/>
    <link rel="stylesheet" href="<c:url value="/resource/template/css/morris.css" />" type="text/css"/>
    <!-- Graph CSS -->
    <link href="<c:url value="/resource/template/css/font-awesome.css" />" rel="stylesheet">
    <!-- jQuery -->
    <script rel="stylesheet" src="https://code.jquery.com/jquery-3.4.0.min.js"></script>

    <!-- //jQuery -->
    <link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet'
          type='text/css'/>
    <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
    <!-- lined-icons -->
    <link rel="stylesheet" href="<c:url value="/resource/template/css/icon-font.min.css" />" type='text/css'/>
    <!-- //lined-icons -->
</head>
<body>
<div class="page-container" id="body-table">
    <!--/content-inner-->
    <div class="left-content container">
        <div class="row">

            <div class="col-md-5 col-md-12 col-lg-5 form-group">
                <form id="form-product">

                    <br/>
                    <label for="productName">Ten san pham</label>
                    <input type="text" id="productName" name="productName" class="form-control"
                           placeholder="Nhap vao ten san pham"/>

                    <label for="productPrice">Gia tien</label>
                    <input type="text" id="productPrice" name="productPrice" class="form-control"
                           placeholder="Nhap vao gia tien"/>

                    <label for="catalog">Danh muc</label>
                    <select class="form-control" id="catalog" name="catalog">
                        <c:forEach var="item" items="${listCatalog}">
                            <option value="${item.catalogId}">${item.catalogName}</option>
                        </c:forEach>
                    </select>

                    <label for="productDecription">Mo ta </label>
                    <textarea rows="5" type="text" id="productDecription" name="productDecription" class="form-control"
                              placeholder="Nhap vao mo ta"></textarea>

                    <label for="productImage">Hinh anh</label>
                    <input type="file" id="productImage" name="productImage" class="form-control"
                           placeholder="Chon hinh anh"/><br/>

                    <span>Danh cho</span>
                    <br/>
                    <label class="radio-inline">
                        <input type="radio" id="reserve-m" name="optioneRadio" value="nam"> Nam
                    </label>
                    <label class="radio-inline">
                        <input type="radio" id="reserve-w" name="optioneRadio" value="nu"> Nu
                    </label>

                    <br/>
                    <div id="clone-at">
                        <div class="productDetails">
                            <span>Chi tiet</span>
                            <select class="form-control" id="productColor" name="productColor">
                                <c:forEach var="item" items="${listProductColor}">
                                    <option value="${item.productColorId}">${item.productColorName}</option>
                                </c:forEach>
                            </select>
                            <br/>
                            <select class="form-control" id="productSize" name="productSize">
                                <c:forEach var="item" items="${listProductSize}">
                                    <option value="${item.productSizeId}">${item.productSizeName}</option>
                                </c:forEach>
                            </select>
                            <br/>

                            <input type="number" id="dealProduct" min="1" value="1" name="dealProduct"
                                   class="form-control"
                                   placeholder="So luong"/><br/>

                            <button class="btn btn-default btn-details">Them chi tiet</button>

                        </div>
                    </div>

                </form>

                <div id="productDetails" class="productDetails">
                    <span>Chi tiet</span>
                    <select class="form-control" id="productColor" name="productColor">
                        <c:forEach var="item" items="${listProductColor}">
                            <option value="${item.productColorId}">${item.productColorName}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <select class="form-control" id="productSize" name="productSize">
                        <c:forEach var="item" items="${listProductSize}">
                            <option value="${item.productSizeId}">${item.productSizeName}</option>
                        </c:forEach>
                    </select>
                    <br/>

                    <input type="number" min="1" value="1" id="dealProduct" name="dealProduct" class="form-control"
                           placeholder="So luong"/><br/>

                    <button class="btn btn-default btn-details">Them chi tiet</button>

                </div>
                <br/>
                <p>
                    <button class="btn btn-info " id="btn-add-product">Them</button>
                    <button class="btn btn-info hidden" id="btn-update">Cap nhat</button>
                    <button class="btn btn-info hidden " id="btn-cancel">Huy</button>

                </p>


            </div>


            <div class="col-md-7 col-lg-7 col-sm-12 ">
                <br/>
                <div style="float: right">
                    <button id="deleteProduct" class="btn btn-primary">Xoa</button>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            <div class="checkbox">
                                <label><input id="check-all" type="checkbox" value=""></label>
                            </div>
                        </th>
                        <th>
                            Ten san pham
                        </th>
                        <th>
                            Gia tien
                        </th>
                    </tr>
                    </thead>
                    <tbody id="body-table-product">
                    <c:forEach items="${listproduct}" var="item">
                        <tr>
                            <td>
                                <div class="checkbox">
                                    <label><input type="checkbox" value="${item.productId}"></label>
                                </div>
                            </td>

                            <td>
                                    ${item.productName}
                            </td>
                            <td>
                                    ${item.price}
                            </td>
                            <td class="updateProduct btn btn-dark" data-id="${item.productId}">Cap nhat

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <ul class="pagination ">
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                    <c:forEach var="i" begin="1" end="${totalPage}">
                        <li class="page-item"><a class="page-link " href="#">${i}</a></li>
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                </ul>
            </div>
        </div>

        <!--//content-inner-->
        <!--/sidebar-menu-->
        <c:import url="sidebar-menu.jsp"/>
        <div class="clearfix"></div>


    </div>

</div>
<script>
    var toggle = true;

    $(".sidebar-icon").click(function () {
        if (toggle) {
            $(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
            $("#menu span").css({"position": "absolute"});
        } else {
            $(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
            setTimeout(function () {
                $("#menu span").css({"position": "relative"});
            }, 400);
        }

        toggle = !toggle;
    });
</script>
<!--js -->
<script src="<c:url value='/resource/template/JS/jquery.nicescroll.js'/>"></script>
<script src="<c:url value='/resource/template/JS/scripts.js'/>"></script>

<!-- Bootstrap Core JavaScript -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script><!-- /Bootstrap Core JavaScript -->
<!-- morris JavaScript -->
<script src="<c:url value='/resource/template/JS/raphael-min.js'/>"></script>
<script src="<c:url value='/resource/template/JS/morris.js'/>"></script>
<script>
    $(document).ready(function () {
        //BOX BUTTON SHOW AND CLOSE
        jQuery('.small-graph-box').hover(function () {
            jQuery(this).find('.box-button').fadeIn('fast');
        }, function () {
            jQuery(this).find('.box-button').fadeOut('fast');
        });
        jQuery('.small-graph-box .box-close').click(function () {
            jQuery(this).closest('.small-graph-box').fadeOut(200);
            return false;
        });

        //CHARTS
        function gd(year, day, month) {
            return new Date(year, month - 1, day).getTime();
        }

        graphArea2 = Morris.Area({
            element: 'hero-area',
            padding: 10,
            behaveLikeLine: true,
            gridEnabled: false,
            gridLineColor: '#dddddd',
            axes: true,
            resize: true,
            smooth: true,
            pointSize: 0,
            lineWidth: 0,
            fillOpacity: 0.85,
            data: [
                {period: '2014 Q1', iphone: 2668, ipad: null, itouch: 2649},
                {period: '2014 Q2', iphone: 15780, ipad: 13799, itouch: 12051},
                {period: '2014 Q3', iphone: 12920, ipad: 10975, itouch: 9910},
                {period: '2014 Q4', iphone: 8770, ipad: 6600, itouch: 6695},
                {period: '2015 Q1', iphone: 10820, ipad: 10924, itouch: 12300},
                {period: '2015 Q2', iphone: 9680, ipad: 9010, itouch: 7891},
                {period: '2015 Q3', iphone: 4830, ipad: 3805, itouch: 1598},
                {period: '2015 Q4', iphone: 15083, ipad: 8977, itouch: 5185},
                {period: '2016 Q1', iphone: 10697, ipad: 4470, itouch: 2038},
                {period: '2016 Q2', iphone: 8442, ipad: 5723, itouch: 1801}
            ],
            lineColors: ['#ff4a43', '#a2d200', '#22beef'],
            xkey: 'period',
            redraw: true,
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['All Visitors', 'Returning Visitors', 'Unique Visitors'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        });


    });
</script>
<script src="<c:url value='/resource/template/JS/custome.js'/>"></script>

</body>
</html>