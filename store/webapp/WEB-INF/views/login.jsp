<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Логин</title>
    <style>
        <%@include file="css/login.css" %>
    </style>
</head>
<body onload='document.loginForm.username.focus();'>


<div id="login-box">
    <h1>Авторизация</h1>
    <h2>Введите имя пользователя и пароль</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="msg">${message}</div>
    </c:if>


    <c:url var="loginUrl" value="/login" />
    <form name='loginForm'
          action="${loginUrl}" method='POST'>

        <table>
            <tr>
                <td>Имя:</td>
                <td><input type='text' name='name'></td>
            </tr>
            <tr>
                <td>Пароль:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a href="<%=request.getContextPath()%>/registration">Зарегистрироваться</a>
                </td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>
</div>

</body>
</html>