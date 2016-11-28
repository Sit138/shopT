<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Информация заказа</title>
    <style>
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
    <div id="container">
        <div id="menu">
            <jsp:include page="menuUser.jsp"/>
        </div>
        <div id="content">
            <h1>Заказ номер ${saleId}</h1>
            <table border="1px">
                <tr>
                    <th>Наименование</th>
                    <th>Количество</th>
                    <th>Цена</th>
                    <th>Скидка</th>
                </tr>
                <c:forEach var="soldProd" items="${orderProducts}">
                    <tr>
                        <td>${soldProd.name}</td>
                        <td>${soldProd.amount}</td>
                        <td>${soldProd.price}</td>
                        <td>${soldProd.discount}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
