<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Касса</title>
    <style>
        <%@include file="css/main.css" %>
    </style>
</head>
<body>
    <h1>Касса</h1>
    <div id="container">
        <div id="menu">
            <jsp:include page="menuUser.jsp"/>
        </div>
        <div id="content">
            <form:form action="deposit">
                <input name="deposit">
                <input type="submit" value="Подтвердить">
            </form:form>
        </div>
    </div>
</body>
</html>
