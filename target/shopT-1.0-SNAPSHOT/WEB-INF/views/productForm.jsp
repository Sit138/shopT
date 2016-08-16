<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Продукт</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>

    <%@include file="include/header.html"%>

<table>
    <h1>Новый товар</h1>
    <form:form action="save" method="post" modelAttribute="product">
        <form:hidden path="id"/>
        <tr>
            <th>Наименование:</th>
            <td><form:input path="productName"/></td>
            <td><form:errors path="productName" cssClass="error"></form:errors> </td>
        </tr>
        <tr>
            <th>Цена:</th>
            <td><form:input path="productPrice"/></td>
            <td><form:errors path="productPrice" cssClass="error"></form:errors> </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Сохранить изменения"></td>
        </tr>
    </form:form>
</table>

</body>
</html>
