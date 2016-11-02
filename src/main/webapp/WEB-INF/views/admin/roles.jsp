<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Роли</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>
<%@include file="../include/headerAdmin.html"%>
    <h1>Роли</h1>
    <table border="1px">
        <tr>
            <th>№</th>
            <th>Имя роли</th>
        </tr>
        <c:forEach var="roleDTO" items="${roleDTOList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${roleDTO.nameRole}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/admin/newRole">Добавить новую роль</a>
</body>
</html>
