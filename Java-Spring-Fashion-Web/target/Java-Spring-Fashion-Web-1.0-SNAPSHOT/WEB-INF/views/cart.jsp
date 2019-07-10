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
                    <a class="nav-link" href="#">
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
    <div class="row">
        <div class="col-md-8 col-sm-12">
            <h3>List product in cart</h3>
            <table class="table" style="font-size: 14px">
                <thead>
                <tr >
                    <td>Ten san pham </td>
                    <td>Mau san pham</td>
                    <td>Size san pham</td>
                    <td>So luong con</td>
                    <td>Gia san pham</td>

                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cart}">
                    <tr>
                        <td class="product-name-cart" data-productId="${item.productId}">
                                ${item.productName}
                        </td>
                        <td class="color" data-color="${item.productColorId}">
                                ${item.colorName}
                        </td >
                        <td class="size" data-size="${item.productSizeId}">
                                ${item.sizeName}
                        </td>
                        <td>
                            <input class="deal-cart" min="1" type="number" value="${item.deal}">
                        </td>
                        <td class="price price-cart"  data-price="${item.price}">
                                ${item.price}
                        </td>
                        <td class=" btn btn-danger deleta-product-cart" >
                            Delete
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h2>Total price: <span id="total-price" style="color: red"> </span></h2>
        </div>
        <div class="col-md-4 col-sm-12">
            <h3>Infonation buyer / consignee </h3>
            <div class="form-group">
                <form action="/cart" method="post">

                    <label for="buyer">Name buyer / consignee</label>
                    <input id="buyer" class="form-control"  name="customerName"><br/>

                    <label for="phone" >Phone number</label>
                    <input id="phone" class="form-control" name="phoneNumber"><br/>

                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="typeReceiving" value="Door-to-door delivery service">Door-to-door delivery service
                        </label><br/>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" checked="" class="form-check-input" name="typeReceiving" value="Receiving at shoop">Receiving at shoop
                        </label><br/>
                    </div>

                    <label for="address" >Address receiving</label>
                    <input id="address" class="form-control" name="deliveryAddress"><br/>

                    <label for="note" >Note</label>
                    <textarea class="form-control" rows="5" id="note" name="note"></textarea><br/>

                    <input type="submit" class="btn btn-primary" value="Order">

                </form>
            </div>

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
