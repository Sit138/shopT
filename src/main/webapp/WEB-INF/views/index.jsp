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
<%@include file="include/header.html"%>
        <c:url value="/logout" var="logoutUrl" />
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}" />
        </form>
        <script>
            function formSubmit() {
                document.getElementById("logoutForm").submit();
            }
        </script>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                User : ${pageContext.request.userPrincipal.name} | <a
                    href="javascript:formSubmit()"> Logout</a>
            </h2>
        </c:if>
<sec:authorize access="hasRole('ROLE_USER')">
    This session will be visible to an admin only.<br/>
    You are an Administrator.<br/>
</sec:authorize>

</body>
</html>
