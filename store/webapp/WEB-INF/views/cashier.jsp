<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Касса</title>
</head>
<body>
    <h1>Касса</h1>
    <form:form action="deposit">
        <input name="deposit">
        <input type="submit" value="Подтвердить">
    </form:form>
</body>
</html>
