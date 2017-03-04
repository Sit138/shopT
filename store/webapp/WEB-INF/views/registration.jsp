<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Регистрация</title>
    <style>
        <%@include file="css/login.css" %>
    </style>
</head>
<body>

<%--<table>--%>
    <div id="auth-box">
    <h1>Регистрация</h1>
        <form:form name="loginForm" action="registration" method="post" modelAttribute="buyerDTO">
            <form:hidden path="id"/>
            <table>
                <tr>
                    <td colspan="2">
                        <c:choose>
                            <c:when test="${errorSave != null}">
                                <div class="error-box">
                                    <div class="error">${errorSave}</div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="error-box">
                                    <form:errors path="name" cssClass="error"></form:errors>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            <tr>
                <th>Имя:</th>
                <td><form:input path="name"/></td>

            </tr>
                <tr>
                <td colspan="2">
                    <div class="error-box">
                        <form:errors path="password" cssClass="error"></form:errors>
                    </div>
                </td>
                </tr>
            <tr>
                <th>Пароль:</th>
                <td><input name="password" type="password"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Зарегистрироваться"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="<%=request.getContextPath()%>/login">Я уже зарегистрирован</a>
                </td>
            </tr>
            </table>
        </form:form>
    </div>

<%--</table>--%>

</body>
</html>
