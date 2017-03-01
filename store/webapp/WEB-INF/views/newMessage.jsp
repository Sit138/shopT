<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Новое сообщение</title>
    <style>
        <%@include file="css/main.css" %>
    </style>
</head>
<body>
    <h1>Новое сообщение</h1>
    <h2>Получатель: ${buyerDTO.name}</h2>
    <form:form modelAttribute="messageDTO" method="post" action="sendMessage">
        <form:textarea path="text"/>
        <form:errors path="text" cssClass="error"></form:errors>
        <input type="hidden" value="${buyerDTO.id}" name="recipientId">
        <br><input type="submit" value="Отправить">
    </form:form>
</body>
</html>
