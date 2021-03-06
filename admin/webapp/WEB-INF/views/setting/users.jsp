<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
    <style>
        <%@include file="../css/main.css" %>
    </style>
</head>
<body>
<%@include file="../include/headerAdmin.html"%>
    <h1>Пользователи</h1>
    <table border="1px">
        <tr>
            <th>№</th>
            <th>Имя</th>
            <th>Роль</th>
            <th>Активновсть</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="user" items="${userDTOList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.userName}</td>
                <td>${user.nameRole}</td>
                <c:choose>
                    <c:when test="${user.enabled}">
                        <td>Активна</td>
                    </c:when>
                    <c:otherwise>
                    <td>Неактивна</td>
                </c:otherwise>
                </c:choose>
                <td>
                    <a href="/admin/setting/deleteUser?id=${user.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin/setting/newUser">Добавить нового пользователя</a>
</body>
</html>
