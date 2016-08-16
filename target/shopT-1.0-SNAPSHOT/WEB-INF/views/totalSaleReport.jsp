<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Общий почасовой отчет</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>
    <h1>Отчет по продажам</h1>
    <table border="1px">
        <th>Дата</th>
        <th>Продано</th>
        <th>Сумма</th>
        <th>Средний чек</th>
        <th>Сумма скидок</th>
        <c:forEach var="saleReport" items="${totalSaleReport}" varStatus="status">
            <tr>
                <td>${saleReport.saleDate}</td>
                <td>${saleReport.saleCount}</td>
                <td>${saleReport.saleSum}</td>
                <td>${saleReport.averageCheck}</td>
                <td>${sumDiscountByRangeList.get(status.index).sumDiscount}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="/saleTime">Почасовой отчет по товарам</a>
</body>
</html>
