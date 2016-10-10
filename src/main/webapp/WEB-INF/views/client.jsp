<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Клиентский магазин</title>
    <style>
        <%@include file="/WEB-INF/views/css/client.css" %>
        <%@include file="css/pagination.css"%>
    </style>
</head>
<body>
    <h1>${client}</h1>
    <form:form modelAttribute="paginator" action="${url}" id="filterForm">
    <%@include file="include/paginationFilterHeader.html"%>

    <table border="1px" class="table_price">
        <th>№</th>
        <th>Наименование</th>
        <th>Цена</th>
        <th>Действие</th>

        <c:forEach var="product" items="${productList}" varStatus="status">
            <tr>
                <td>
                    ${status.index + (paginator.pageNumber*paginator.numberRowsOnPage)}
                </td>
                <td <c:if test="${not empty discountNow and discountNow.productId == product.id}">style="background-color: #B3B3FF" </c:if>>
                    ${product.name}
                </td>

                <td>
                    <fmt:formatNumber value="${product.price}" type="currency" currencyCode="RUB"/>
                </td>
                <td>
                    <a href="/sale?id=${product.id}" class="c" onclick="alert('Товар приобретен!');">Купить</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <h2>Дисконт - ${discountNow.productName}</h2>

    <%@include file="include/paginationFilterFooter.html"%>
    </form:form>

<a href="/">Вернуться на главную</a>

</body>
</html>
