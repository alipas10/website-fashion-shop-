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

<div class="container-fluid" style="background-color: black">
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
                        Dropdown link
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                    </div>
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
                <li class="v-item" id="id-cart">
                    <a class="nav-link" href="cart.jsp">
                        <img src="<c:url value='/resource/template/image/icon-cart.png'/>">
                        <div>
                            <c:if test="${not empty dealProductOnCart}">
                                <div class="dealCart">
                                    <span>
                                        ${dealProductOnCart}
                                    </span>
                                </div>
                            </c:if>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
    </nav>

</div>

<div class="container">
    <div class="row" style="margin-top: 16px">
        <div class="col-sm-2 col-md-2">
            <h3>Catalog Product</h3>
            <ul class="mymenu">
                <c:forEach var="item" items="${listCatalog}">
                    <li><a href="#">
                            ${item.catalogName}
                    </a>
                    </li>
                </c:forEach>
            </ul>

        </div>

        <div class="col-sm-8 col-md-8">
            <div class="row">
                <div class="col-sm-4 col-md-4" >
                    <img style="max-width: 100%"
                         src="<c:url value="/resource/template/image/${productById.productImage}"/> ">
                </div>
                <div class="col-sm-8 col-md-8">
                    <h3 id="product-name" data-productId="${productById.productId}">${productById.productName}</h3>
                    <h4 class="price" id="product-price" data-value="${productById.price}" style="color: red">${productById.price} VND</h4>
                    <table class="table">
                        <thead>
                            <tr>
                                <td><h5>Mau san pham</h5></td>
                                <td><h5>Size san pham</h5></td>
                                <td><h5>So luong con</h5></td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${productById.productDetailEntities}">
                            <tr>
                                <td class="color" data-color="${item.productColorEntity.productColorId}">${item.productColorEntity.productColorName}</td>
                                <td class="size" data-size="${item.productSizeEntity.productSizeId}">${item.productSizeEntity.productSizeName}</td>
                                <td class="deal" data-deal="${item.deal}">${item.deal}</td>
                                <td>
                                    <button data-productDI="${item.productDetailId}" class="btn btn-success btn-cart">Add Cart</button>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-sm-2 col-md-2">
            <span>${productById.productDescription} </span>
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
