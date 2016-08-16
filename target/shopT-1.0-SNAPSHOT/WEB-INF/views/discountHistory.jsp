<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Discount history</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>

    <h1>История скидок</h1>

    <table border="1px">
        <tr>
            <th>№</th>
            <th>Наименование</th>
            <th>Скидка</th>
            <th>Цена без скидки</th>
            <th>Цена со скидкой</th>
            <th>Разница</th>
            <th>Дата</th>
        </tr>
        <c:forEach var="productsDiscount" items="${productsDiscount}" varStatus="status">
                <tr>
                    <td>${status.index}</td>
                    <td>${productsDiscount.product.productName}</td>
                    <td>${productsDiscount.discount_value}</td>
                    <td>${productsDiscount.product.productPrice}</td>
                    <td>${productsDiscount.productDiscountPrice}</td>
                    <td>${productsDiscount.discountPriceSpread}</td>
                    <td>${productsDiscount.discount_date}</td>
                </tr>
        </c:forEach>
    </table>
</body>
</html>
