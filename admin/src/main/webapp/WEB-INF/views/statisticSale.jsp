<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Sale</title>
    <style>
        <%@include file="css/main.css" %>
        <%@include file="css/pagination.css"%>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>

    <h1>Отчет по продажам (почасовой)</h1>
    <form:form modelAttribute="paginator" action="${url}" id="filterForm">
        <label>С </label>
        <input name="fromDate" value="<fmt:formatDate value="${paginator.fromDate}" pattern="yyyy-MM-dd"/>" type="date">
        <label> До </label>
        <input name="toDate" value="<fmt:formatDate value="${paginator.toDate}" pattern="yyyy-MM-dd" />" type="date">
    <%@include file="include/paginationFilterHeader.html"%>

    <table border="1px">
        <tr>
            <th>Наименование</th>
            <th>Дата</th>
            <th>Продано</th>
            <th>Сумма</th>
            <th>Средний чек</th>
            <th>Продано по скидке</th>
            <th>Сумма скидок</th>
        </tr>
        <c:forEach var="statisticSale" items="${statisticSale}">
            <tr>
                <td>
                    ${statisticSale.productName}
                </td>
                <td>
                    ${statisticSale.salePeriod}
                </td>
                <td>
                    ${statisticSale.countSaleProduct}
                </td>
                <td>
                    ${statisticSale.sumSaleProduct}
                </td>
                <td>
                    ${statisticSale.averageCheck}
                </td>
                <td>
                    ${statisticSale.countSaleProductWithDiscount}
                </td>
                <td>
                    ${statisticSale.sumSpreadAmount}
                </td>
            </tr>
        </c:forEach>
        <tr>
            <th colspan="2">Итого</th>
            <td>${finalStatisticSaleForPeriod.sumCountSaleProduct}</td>
            <td>${finalStatisticSaleForPeriod.fullSumSaleProduct}</td>
            <td>${finalStatisticSaleForPeriod.averageCheckOnPeriod}</td>
            <td>${finalStatisticSaleForPeriod.sumCountSaleProductWithDiscount}</td>
            <td>${finalStatisticSaleForPeriod.fullSumSpreadAmount}</td>
        </tr>
    </table>
    <%@include file="include/paginationFilterFooter.html"%>
    </form:form>
</body>
</html>
