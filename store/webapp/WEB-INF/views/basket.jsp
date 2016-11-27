<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Basket</title>
    <style>
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
    <h1>Корзина</h1>
    <div id="container">
        <div id="menu">
            <jsp:include page="menuUser.jsp"/>
        </div>

        <div id="content">
            <table border="1px" style="font-family: 'Times New Roman',serif; font-size: large">
                <th>Наименование</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Действие</th>
                <c:forEach items="${basket.basketProducts}" var="prod">
                    <form:form action="delete?id=${prod.productId}" method="POST">
                        <tr>
                            <td style="text-align: center">${prod.name}</td>
                            <td style="text-align: center">${prod.price}</td>
                            <td style="text-align: center">${prod.amount}</td>
                            <td style="text-align: center">
                                <input type="number" value="1" name="amount" min="1" max="${prod.amount}">
                                <input type="submit" value="Удалить">
                            </td>
                        </tr>
                    </form:form>
                </c:forEach>
                <tr>
                    <td colspan="2">Сумма заказа: ${totalSum}</td>
                    <td><a href="<%=request.getContextPath()%>/order">Подтвердить заказ</a></td>
                </tr>
            </table>

        </div>
    </div>

</body>
</html>
