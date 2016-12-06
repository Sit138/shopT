<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Новая роль</title>
    <style>
        <%@include file="../css/main.css" %>
    </style>
</head>
<body>

<%@include file="../include/headerAdmin.html"%>
<table>
    <h1>Новая роль</h1>
    <form:form action="saveRole" method="post" modelAttribute="roleDTO">
        <form:hidden path="id"/>
        <tr>
            <th>Имя роли</th>
            <td><form:input path="nameRole"/></td>
            <td><form:errors path="nameRole" cssClass="error"></form:errors> </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить изменения"></td>
        </tr>
    </form:form>
</table>

</body>
</html>
