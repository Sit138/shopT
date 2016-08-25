<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Клиентский магазин</title>
    <style>
        <%@include file="/WEB-INF/views/css/client.css" %>
    </style>
</head>
<body>
    <h1>${client}</h1>
    <table border="1px" class="table_price">
        <th>№</th>
        <th>Наименование</th>
        <th>Цена</th>
        <th>Действие</th>

        <c:forEach var="product" items="${productList}" varStatus="status">
            <tr>
                <td>
                    ${status.index}
                </td>
                <td <c:if test="${not empty discountNow and discountNow.productId == product.id}">style="background-color: #B3B3FF" </c:if>>
                    ${product.productName}
                </td>

                <td>
                    ${product.productPrice}
                </td>
                <td>
                    <a href="/sale?id=${product.id}" class="c" onclick="alert('Товар приобретен!');">Купить</a>
                </td>
            </tr>
        </c:forEach>

    </table>

<a href="/">Вернуться на главную</a>

</body>
</html>
