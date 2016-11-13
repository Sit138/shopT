<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Роли</title>
    <style>
        <%@include file="../css/main.css" %>
    </style>
</head>
<body>
<%@include file="../include/headerAdmin.html"%>
    <h1>Роли</h1>
    <table border="1px">
        <tr>
            <th>№</th>
            <th>Имя роли</th>
            <th>Действие</th>
        </tr>
        <c:forEach var="roleDTO" items="${roleDTOList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${roleDTO.nameRole}</td>
                <td>
                    <c:choose>
                        <c:when test="${roleDTO.nameRole == 'ROLE_ADMIN'}">
                            --
                        </c:when>
                        <c:otherwise>
                            <a href="/admin/deleteRole?id=${roleDTO.id}">Удалить</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin/newRole">Добавить новую роль</a>
</body>
</html>
