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

    <form:form modelAttribute="paginator" action="${url}" id="filterForm">
    <%@include file="include/paginationFilterHeader.html"%>

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

        <c:forEach var="product" items="${productList}" varStatus="status">
            <tr>
                <td>
                    ${status.index + (paginator.pageNumber*paginator.numberRowsOnPage)}
                </td>
                <td>
                    ${product.name}
                </td>
                <td>
                    ${product.price}
                </td>
                <td>
                    <a href="/admin/edit?id=${product.id}">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/admin/delete?id=${product.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <%@include file="include/paginationFilterFooter.html"%>
    </form:form>

    <a href="/admin/new">Добавить новый товар</a>

</body>
</html>
