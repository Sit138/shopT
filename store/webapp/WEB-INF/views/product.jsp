<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Клиентский магазин</title>
    <style>
        <%@include file="css/pagination.css"%>
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
    <h1>${client}</h1>

    <div id="container">
        <div id="menu">
            <jsp:include page="menuUser.jsp"/>
        </div>
        <div id="content">
                <table>
                    <th>№</th>
                    <th>Наименование</th>
                    <th>Цена</th>
                    <th>Количество</th>
                    <th>Действие</th>

                    <c:forEach var="product" items="${productList}" varStatus="status">

                        <form:form action="put?id=${product.id}" method="post">
                            <tr id="cell">
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
                                    <input type="number" style="width: 50px" name="count">
                                </td>
                                <td><input type="submit" value="В корзину" onclick="alert('Товар в корзине!');"></td>
                                <td>${product.id}</td>
                            </tr>
                        </form:form>
                    </c:forEach>

                </table>


            <h2>Дисконт - ${discountNow.productName}</h2>

            <form:form modelAttribute="paginator" action="${url}" id="filterForm">
                <%@include file="include/paginationFilterHeader.html"%>
                <%@include file="include/paginationFilterFooter.html"%>
            </form:form>
        </div>
    </div>
</body>
</html>
