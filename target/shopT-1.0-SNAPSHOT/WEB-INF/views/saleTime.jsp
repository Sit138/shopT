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
    <h1>Отчет по продаже товара (почасовой)</h1>
    <table border="1px">
        <tr>
            <th>Наименование</th>
            <th>Дата</th>
            <th>Количество</th>
            <th>Сумма</th>
        </tr>
        <c:forEach var="saleProduct" items="${sale}">
            <tr>
                <td>
                    ${saleProduct.productName}
                </td>
                <td>
                    ${saleProduct.saleDateStringFormat}
                </td>
                <td>
                    ${saleProduct.saleCount}
                </td>
                <td>
                    ${saleProduct.saleAmount}
                </td>
            </tr>
        </c:forEach>
    </table>
<h2>${maxPageId}</h2>
<c:forEach begin="0" end="${maxPageId}" varStatus="stat">
    <a href="/saleTime?pageId=${stat.index}">${stat.index + 1} | </a>
</c:forEach>
</body>
</html>
