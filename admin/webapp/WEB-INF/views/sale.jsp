<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Продажи</title>
    <style>
        <%@include file="css/main.css" %>
    </style>
</head>
<body>
<%@include file="include/header.html"%>
<h1>Продажи</h1>
<table border="1px">
    <tr>
        <th>Id</th>
        <th>Дата</th>
        <th>Позиций</th>
        <th>Сумма</th>
        <th>Статус</th>
    </tr>
    <c:forEach var="sale" items="${sales}">
        <tr>
            <td>
                <a href="<%=request.getContextPath()%>/sale/orderInfo?saleId=${sale.id}">${sale.id}</a>
            </td>
            <td>${sale.date}</td>
            <td>${sale.amount}</td>
            <td>${sale.totalSum}</td>
            <form:form action="updateState?saleId=${sale.id}" method="POST">
                <td>
                    <c:choose>
                        <c:when test="${sale.state == 'CANCELED'}">
                            ${sale.state}
                        </c:when>
                        <c:otherwise>
                            <select name="state">
                                <option>${sale.state}</option>
                                <c:forEach items="${stateSale}" var="state">
                                    <option>${state}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="totalSum" value="${sale.totalSum}">
                            <input type="submit" value="Изменить">
                        </c:otherwise>
                    </c:choose>
                </td>
            </form:form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
