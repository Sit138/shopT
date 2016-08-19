<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Shop</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
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

        <c:forEach var="product" items="${productList}" varStatus="status">
            <tr>
                <td>
                    ${status.index}
                </td>
                <td>
                    ${product.productName}
                </td>
                <td>
                    ${product.productPrice}
                </td>
                <td>
                    <a href="/edit?id=${product.id}">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=${product.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>

    </table>

    <a href="/new">Добавить новый товар</a>
<h3>${lastP}</h3>
</body>
</html>
