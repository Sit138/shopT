<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Новый пользователь</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>

<%@include file="../include/headerAdmin.html"%>

<table>
    <h1>Новый пользователь</h1>
    <form:form action="/admin/saveUser" method="post" modelAttribute="userDTO">
        <form:hidden path="id"/>
        <tr>
            <th>Имя пользователя</th>
            <td><form:input path="userName"/></td>
            <td><form:errors path="userName" cssClass="error"></form:errors> </td>
        </tr>
        <tr>
            <th>Пароль</th>
            <td><form:password path="password"/></td>
            <td><form:errors path="password" cssClass="error"></form:errors> </td>
        </tr>
        <tr>
            <th>Роль</th>
            <td><form:select path="nameRole">
                <c:forEach items="${roleList}" var="role">
                    <form:option value="${role.nameRole}"/>
                </c:forEach>
            </form:select></td>
        </tr>
        <tr>
            <th>Активность</th>
            <td><form:checkbox path="enabled"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить изменения"></td>
        </tr>
    </form:form>
</table>

</body>
</html>
