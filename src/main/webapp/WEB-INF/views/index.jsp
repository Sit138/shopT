<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Index</title>
    <style>
        <%@include file="/WEB-INF/views/css/main.css" %>
    </style>
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <%@include file="include/headerAdmin.html"%>
    <h1>Админ</h1>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_MANAGER')">
    <%@include file="include/header.html"%>
    <h1>Менеджер</h1>
</sec:authorize>

</body>
</html>
