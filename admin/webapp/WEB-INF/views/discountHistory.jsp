<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Discount history</title>
    <style>
        <%@include file="css/main.css" %>
        <%@include file="css/pagination.css"%>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>

    <h1>История скидок</h1>
    <form:form modelAttribute="paginator" action="${url}" id="filterForm" method="POST">
        С
        <input name="startAt" value="<fmt:formatDate value="${startAt}" pattern="yyyy-MM-dd"/>" type="date">
        До
        <input name="endAt" value="<fmt:formatDate value="${endAt}" pattern="yyyy-MM-dd"/>" type="date">
        <%@include file="include/paginationFilterHeader.html"%>
        <%@include file="include/paginationFilterFooter.html"%>
    </form:form>

    <table border="1px">
        <tr>
            <th>№</th>
            <th>Наименование</th>
            <th>Скидка</th>
            <th colspan="2">Период проведения</th>
            <th>Вид скидки</th>
        </tr>
        <c:forEach var="productsDiscount" items="${productsDiscount}" varStatus="status">
                <tr>
                    <td>${status.index + (paginator.pageNumber*paginator.numberRowsOnPage)}</td>
                    <td>${productsDiscount.productName}</td>
                    <td><fmt:formatNumber value="${productsDiscount.value}" type="number" maxFractionDigits="2"/></td>
                    <td>
                        <fmt:formatDate value="${productsDiscount.startAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
                    </td>
                    <c:choose>
                        <c:when test="${productsDiscount.endAt == null}">
                            <td>Активна</td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <fmt:formatDate value="${productsDiscount.endAt}" pattern="dd/MM/yyyy HH:mm:ss"/>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td>${productsDiscount.type}</td>
                </tr>
        </c:forEach>
    </table>

</body>
</html>
