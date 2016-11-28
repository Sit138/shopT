<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        body {
            padding: 10px;
            font-family: "Times New Roman", sans-serif;
            font-size: 14px;
            line-height: 1.4;
        }

        .main-menu {
            width: 180px;
            margin: 0;
            padding: 0;
            list-style: none;
        }

        .main-menu a {
            display: block;
            margin-bottom: -1px;
            padding: 8px 14px;
            text-decoration: none;
            color: #0088cc;
            border: 1px solid #e5e5e5;
        }

        .main-menu a:hover {
            background: #f5f5f5;
        }

        .main-menu > li {
        }

    </style>
</head>
<body>
<ul class="main-menu">
    <li><a href="<%=request.getContextPath()%>/profile">Профиль</a></li>
    <c:choose>
        <c:when test="${basket != null}">
            <li>
                <a href="<%=request.getContextPath()%>/basket" style="background-color: lavender">
                    Корзина (${basket.basketProducts.size()})</a>
            </li>
        </c:when>
        <c:otherwise>
            <li><a href="<%=request.getContextPath()%>/basket">Корзина</a></li>
        </c:otherwise>
    </c:choose>

    <li><a href="<%=request.getContextPath()%>/product">Продукты</a></li>
    <c:url value="/logout" var="logoutUrl" />
    <form id="logout" action="${logoutUrl}" method="post" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    <li>
        <a href="javascript:document.getElementById('logout').submit()">Выйти</a>
    </li>
</ul>
</body>
</html>
