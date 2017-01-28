<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Информация о продукте</title>
    <style>
        <%@include file="css/main.css"%>
        .cl {
            width: 920px;
            display: flex;
            /*justify-content: space-between;*/
            flex-wrap: wrap;
        }
        img {
            margin-bottom: 5px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="menu">
        <jsp:include page="menuUser.jsp"/>
    </div>
    <div id="content">
        <h1>${productDTO.name}</h1>
        <div class="cl">
            <c:forEach var="image" items="${fileNames}" varStatus="status">
                <img src="<%=request.getContextPath()%>/image?prod=${productDTO.id}&num=${image}" style="height: 300px;"/>
            </c:forEach>
        </div>

        <div>
            <c:forEach var="comment" items="${commentList}">
                <p>${comment.buyerName}/
                    <fmt:formatDate value="${comment.creationDate}" pattern="dd/MM/yyyy HH:mm:ss"/>
                        : ${comment.message}
                </p>
            </c:forEach>
            <form:form modelAttribute="paginator" action="${url}" id="filterForm">
                <%@include file="include/paginationFilterHeader.html"%>
                <%@include file="include/paginationFilterFooter.html"%>
            </form:form>
            <br/>

            <form:form action="addComment" method="post" modelAttribute="commentDTO">
                <form:textarea path="message"/>
                <input type="hidden" name="productId" value="${productDTO.id}">
                <input type="submit" value="Добавить">
            </form:form>
        </div>
    <a href="<%=request.getContextPath()%>/product">Перейти к списку продуктов</a>
    </div>
</div>
</body>
</html>
