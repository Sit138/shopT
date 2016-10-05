<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Discount history</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
        <%@include file="css/pagination.css"%>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>

    <h1>История скидок</h1>
    <form:form modelAttribute="paginator" action="${url}" id="filterForm">
    <%@include file="include/paginationFilterHeader.html"%>

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
    <%@include file="include/paginationFilterFooter.html"%>
    </form:form>
</body>
</html>
