<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Информация о заказе</title>
    <style>
        <%@include file="css/main.css" %>
    </style>
</head>
<body>
<%@include file="include/header.html"%>
<h1>Заказ номер ${saleId}</h1>
<table border="1px">
    <tr>
        <th>Наименование</th>
        <th>Количество</th>
        <th>Цена</th>
        <th>Скидка</th>
    </tr>
    <c:forEach var="soldProd" items="${orderProducts}">
        <tr>
            <td>${soldProd.name}</td>
            <td>${soldProd.amount}</td>
            <td>${soldProd.price}</td>
            <td>${soldProd.discount}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
