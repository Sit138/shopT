<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Shop</title>
    <style>
        <%@include file="css/main.css" %>
        <%@include file="css/pagination.css"%>
    </style>
</head>
<body>
    <%@include file="include/header.html"%>

    <h1>Магазин</h1>
    <h2>Список товаров</h2>

    <c:if test="${info == 'errorDelete'}">
        <script>
            alert("Удаление невозможно!");
        </script>
    </c:if>

    <table border="1px">
        <th>№</th>
        <th>Наименование</th>
        <th>Цена</th>
        <th>Действие</th>
        <th colspan="2">Скидка</th>
        <c:forEach var="product" items="${productList}" varStatus="status">
            <tr>
                <td>
                    ${status.index + (paginator.pageNumber*paginator.numberRowsOnPage)}
                </td>
                <td>
                    <a href="<%=request.getContextPath()%>/upload?prod=${product.id}">${product.name}</a>
                </td>
                <td>
                    ${product.price}
                </td>
                <td>
                    <a href="/admin/edit?id=${product.id}">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/admin/delete?id=${product.id}">Удалить</a>
                </td>
                <c:choose>
                    <c:when test="${product.discounted}">
                        <td>Активна</td>
                        <form:form action="deleteDisc?id=${product.id}">
                            <td><input value="Отключить" type="submit"></td>
                        </form:form>
                    </c:when>
                    <c:otherwise>
                        <td>-</td>
                        <form:form action="newDisc?id=${product.id}">
                            <td><input type="number" name="valDisc"><input value="Добавить" type="submit"></td>
                        </form:form>
                    </c:otherwise>
                </c:choose>

            </tr>
        </c:forEach>
        <a href="/admin/new">Добавить новый товар</a>
    </table>

    <form:form modelAttribute="paginator" action="${url}" id="filterForm">
        <%@include file="include/paginationFilterHeader.html"%>
        <%@include file="include/paginationFilterFooter.html"%>
    </form:form>

</body>
</html>
