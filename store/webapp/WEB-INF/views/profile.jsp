<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Профиль</title>
    <style>
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
    <h1>Профиль</h1>
    <div id="container">
        <div id="menu">
            <jsp:include page="menuUser.jsp"/>
        </div>
        <div id="content">
            <table>
                <tr>
                    <td>
                        <img src="<%=request.getContextPath()%>/image?type=avatar&id=${buyer.id}" style="height: 100px;"/>
                    </td>
                    <td>
                        <a href="<%=request.getContextPath()%>/avaForm">Загрузить аватар</a>
                    </td>
                </tr>
                <tr>
                    <th>Имя: </th>
                    <td>${buyer.name}</td>
                </tr>
                <tr>
                    <th>Id: </th>
                    <td>${buyer.id}</td>
                </tr>
                <tr>
                    <th>Баланс: </th>
                    <td>${buyer.balance}</td>
                    <td>
                        <a href="<%=request.getContextPath()%>/cashier">Пополнить счет</a>
                    </td>
                </tr>
            </table>
            <h3>Покупки</h3>
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
                            <a href="<%=request.getContextPath()%>/profile/orderInfo?saleId=${sale.id}">${sale.id}</a>
                        </td>
                        <td>${sale.date}</td>
                        <td>${sale.amount}</td>
                        <td>${sale.totalSum}</td>
                        <td>${sale.state}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
