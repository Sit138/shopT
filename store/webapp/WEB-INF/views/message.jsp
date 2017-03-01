<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Сообщения</title>
    <style>
        <%@include file="css/main.css" %>
    </style>
</head>
<body>
<div id="container">
    <div id="menu">
        <jsp:include page="menuUser.jsp"/>
    </div>
    <div id="content">
        <c:forEach items="${dialogs}" var="dialog">
            <div <c:choose>
                    <c:when test="${dialog.readAll}">style="border: solid"</c:when>
                    <c:otherwise>style="border: solid red"</c:otherwise>
                </c:choose>style="">
                <b>${dialog.lastSenderName}</b>
                <fmt:formatDate value="${dialog.messageDate}" pattern="yyyy-MM-dd"/><br>
                    ${dialog.lastText}
            </div><br>
        </c:forEach>
    </div>
</div>
</body>
</html>
