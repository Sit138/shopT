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
            <th colspan="2">Период проведения</th>
        </tr>
        <c:forEach var="productsDiscount" items="${productsDiscount}" varStatus="status">
                <tr>
                    <td>${status.index}</td>
                    <td>${productsDiscount.productName}</td>
                    <td>${productsDiscount.value}</td>
                    <td>${productsDiscount.startDate}</td>
                    <c:choose>
                        <c:when test="${productsDiscount.endDate == null}">
                            <td>Активна</td>
                        </c:when>
                        <c:otherwise>
                            <td>${productsDiscount.endDate}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
        </c:forEach>
    </table>
    <c:forEach begin="0" end="${maxNumberPage}" varStatus="stat">
        <c:choose>
            <c:when test="${numberPage == stat.index}">
                <a style="color: red" href="/discountHistory?num=${stat.index}">${stat.index + 1} | </a>
            </c:when>
            <c:otherwise>
                <a href="/discountHistory?num=${stat.index}">${stat.index + 1} | </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</body>
</html>
