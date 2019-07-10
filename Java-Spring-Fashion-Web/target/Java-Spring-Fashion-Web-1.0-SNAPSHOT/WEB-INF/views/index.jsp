<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tramy
  Date: 2019-04-20
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="ResourcesBundle" var="lang"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resource/template/Styles/style.css"/>
    <title><fmt:message key="label.home" bundle="${lang}"/></title>
    <jsp:include page="taglib.jsp"/>
</head>
<body>

<div class="container-fluid" id="header">
    <nav class="navbar navbar-expand-md bg-dark navbar-dark" id="none-nav">
        <a class="navbar-brand" href="#" style="font-size: 16px"><fmt:message key="label.home" bundle="${lang}"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav navbar-center">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown open">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        San pham
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <c:forEach var="item" items="${listCatalog}">
                            <li style="text-align: center"><a href="/product/${item.catalogId}/${item.catalogName}" >${item.catalogName}</a></li>
                            <li role="separator" class="dropdown-divider"></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="label.index.service" bundle="${lang}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="label.index.contact" bundle="${lang}"/></a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">
                <li class="v-item" style="padding: 12px;"><a class="nav-link" href="login/"><fmt:message
                        key="label.login" bundle="${lang}"/></a>
                </li>
                <c:if test="${not empty staff}">
                    <li class="circle-avatar"><a>${staff.staffName}</a></li>
                </c:if>
                <li class="v-item"><a class="nav-link" href="cart.jsp"><img
                        src="<c:url value='/resource/template/image/icon-cart.png'/>"></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="event_header wow bounceInUp ">
        <span>Ngay 11/1 - 11/2</span><br/>
        <span style="font-size: 50px">Mua 1 tang 1</span><br/>
        <button>Xem ngay</button>

    </div>

</div>

<div id="info" class="container">
    <div class="row">
        <div class="col-12 col-sm-4 col-md-4 wow fadeInLeft data-wow-duration='1s'">
            <img class="icon" src="<c:url value="/resource/template/image/medal.png"/>"><br/>
            <span style="font-size: 32px; font-weight: 500"><fmt:message key="label.index.quality"
                                                                         bundle="${lang}"/></span><br/>
            <span><fmt:message key="label.index.quality.text" bundle="${lang}"/></span>
        </div>
        <div class="col-12 col-sm-4 col-md-4 wow fadeInUp data-wow-duration='1s' data-wow-delay='1s'">
            <img class="icon" src="<c:url value="/resource/template/image/icon_saving.png"/>"><br/>
            <span style="font-size: 32px; font-weight: 500"><fmt:message key="label.index.saving"
                                                                         bundle="${lang}"/></span><br/>
            <span><fmt:message key="label.index.saving.text" bundle="${lang}"/></span>
        </div>
        <div class="col-12 col-sm-4 col-md-4 wow fadeInDown data-wow-duration='1s' data-wow-delay='2s'">
            <img class="icon" src="<c:url value="/resource/template/image/icon_delivery.png"/>"><br/>
            <span style="font-size: 32px; font-weight: 500"><fmt:message key="label.index.delivery"
                                                                         bundle="${lang}"/></span><br/>
            <span><fmt:message key="laebl.index.delivery.text" bundle="${lang}"/></span>
        </div>
    </div>

    <div class="title-product container-fluid">
        <span><fmt:message key="label.index.product" bundle="${lang}"/></span>
        <div class="row" id="row-product">
            <c:forEach var="item" items="${listproduct}">
                <a href="/detail/${item.productId}">
                    <div class="col-md-6 col-xl-3 col-sm-6 wow zoomIn">
                        <div class="card " style="width: 18rem;">
                            <img class="card-img-top "
                                 src="<c:url value="/resource/template/image/${item.productImage}"/>"
                                 alt="Card image cap">
                            <div class="card-body">
                                <p class="card-text">${item.productName}.</p>
                                <h5 class="card-title price">${item.price}VND</h5>
                                <a href="#" class="btn btn-primary">Chi tiet</a>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>

        </div>
    </div>


</div>

<div class="container-fuild" id="footer">
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <p><span><fmt:message key="label.index.infomation" bundle="${lang}"/> </span></p>
            <span><fmt:message key="label.index.infomation.text" bundle="${lang}"/></span>
        </div>
        <div class="col-md-4 col-sm-4">
            <p><span><fmt:message key="label.index.contact" bundle="${lang}"/></span></p>
            <span><fmt:message key="label.index.contact.text" bundle="${lang}"/></span><br/>
            <span><fmt:message key="label.index.contact.text1" bundle="${lang}"/></span><br/>
            <span>123123123</span>
        </div>
        <div class="col-md-4 col-sm-4   ">
            <p><span><fmt:message key="label.index.feedback" bundle="${lang}"/></span></p>
            <input class="material-texinput" type="text" style="margin-bottom: 8px" placeholder="Email"/>
            <textarea rows="4" cols="50" placeholder="Nội dung" style="margin-bottom: 8px"></textarea>
            <button class="material-primary-button">Đồng ý</button>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
<script></script>
</body>
</html>
