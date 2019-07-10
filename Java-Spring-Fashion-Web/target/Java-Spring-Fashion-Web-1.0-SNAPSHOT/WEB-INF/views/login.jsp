<%--
  Created by IntelliJ IDEA.
  User: tramy
  Date: 2019-04-24
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="ResourcesBundle" var="lang"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resource/template/Styles/style.css"/>
    <title><fmt:message key="label.login.title" bundle="${lang}" /> </title>
    <jsp:include page="taglib.jsp"/>
</head>
<body id="body-login">
    <div id="body-flex-login">
        <div id="container-login">
            <div id="container-login-left">
                <div id="header-top-left" class="header-login">
                    <span id="text-logo" >
                        <fmt:message key="label.login.welcome" bundle="${lang}"/><br/>
                    </span>
                    <span id="hind-text-logo">
                        <fmt:message key="label.login.header.test" bundle="${lang}"/>
                    </span>
                </div>
                <div id="header-bottom-left">
                    <p><img src="<c:url value="/resource/template/image/icon_circle.png"/>"/>
                        <span>
                            <fmt:message key="label.login.footer.test1" bundle="${lang}"/>
                        </span>
                    </p>
                    <p><img src="<c:url value="/resource/template/image/icon_circle.png"/>"/>
                        <span>
                            <fmt:message key="label.login.footer.test2" bundle="${lang}"/>
                        </span>
                    </p>
                    <p><img src="<c:url value="/resource/template/image/icon_circle.png"/>"/>
                        <span>
                            <fmt:message key="label.login.footer.test3" bundle="${lang}"/>
                        </span>
                    </p>
                </div>
           </div>
            <div id="container-login-right">
                <div id="header-top-right" class="header-login">
                    <span  class="active" id="login"><fmt:message key="label.login" bundle="${lang}"/></span> / <span id="singup"><fmt:message key="label.login.registration" bundle="${lang}"/></span>
                </div>
                <div class="container-center-login-right" >
                    <div class="login-form">
                        <form action="/login/checkLogin/" method="post">
                            <span class="checklogin">${messageResponse}</span>
                            <input id="input-icon-email" placeholder="Email"  name="email" type="text"><br/>
                            <input  id="input-icon-password" placeholder="Password"  name="password" type="password"><br/>
                            <input id="btn-login" class="material-primary-button" type="submit" value="<fmt:message key="label.login" bundle="${lang}" />"><br/>
                        </form>
                    </div>
                    <div class="singup-form" id="form-created-staff">
                        <span class="checklogin">${messageResponse}</span>
                        <form action=/login/createStaff/ method="post">
                        <input id="input-register-email" placeholder="Email"  name="emailSu" type="text"><br/>
                        <input class="input-register-password" placeholder="Password"  name="passwordSu" type="password"><br/>
                        <input class="input-register-password" placeholder="Retype Password"  name="passwordRSu" type="password"><br/>
                        <input class="material-primary-button" id="createStaffForm" type="submit" value="<fmt:message key="label.login" bundle="${lang}" />"><br/>
                        </form>
                    </div>
                </div>
                <div id="container-social-login">
                    <img src="<c:url value="/resource/template/image/facebook.png" />" />

                    <img src="<c:url value="/resource/template/image/search1.png" />" />
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
<script></script>
</body>
</html>
