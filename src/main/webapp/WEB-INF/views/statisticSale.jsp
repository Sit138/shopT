<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Sale</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>
    <h1>Отчет по продажам (почасовой)</h1>
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
    <c:forEach begin="0" end="${maxNumberPage}" varStatus="stat">
        <c:choose>
            <c:when test="${numberPage == stat.index}">
                <a style="color: red" href="/statisticSale?num=${stat.index}">${stat.index + 1} | </a>
            </c:when>
            <c:otherwise>
                <a href="/statisticSale?num=${stat.index}">${stat.index + 1} | </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</body>
</html>
