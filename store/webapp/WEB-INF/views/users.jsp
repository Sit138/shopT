<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Пользователи</title>
    <style>
        <%@include file="css/main.css"%>
    </style>
</head>
<body>
<h1>Пользователи магазина</h1>
<div id="container">
    <div id="menu">
        <jsp:include page="menuUser.jsp"/>
    </div>
    <div id="content">
        <table>
            <c:forEach items="${buyerList}" var="buyer">
                <c:if test="${buyer.enabled}">
                    <tr>
                        <td>
                            <img src="<%=request.getContextPath()%>/image?avatar=${buyer.id}" style="height: 100px;"/>
                            <br><a href="#">Написать</a>
                        </td>
                        <td>${buyer.name}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
