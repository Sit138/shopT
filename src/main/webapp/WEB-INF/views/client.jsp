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
                    <a href="/sale?id=${product.id}" class="c" onclick="alert('Товар приобретен!');">Купить</a>
                </td>
            </tr>
        </c:forEach>

    </table>

<h2>Товар по акции: ${discountNow}</h2>

</body>
</html>
