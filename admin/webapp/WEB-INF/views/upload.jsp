<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Загрузка картинок продукта</title>
    <script>
        function newInput() {
            var tab = document.getElementById("up");
            var tr = document.createElement('tr');
            var td = document.createElement('td');
            var count = parseInt(document.getElementById("count").value);
            var input = document.createElement('input');
            if (count != 5){
                input.type = "file";
                input.name = "uploadList";
                td.appendChild(input);
                tr.appendChild(td);
                tab.appendChild(tr);
                count++;
                document.getElementById("count").value = count;
            } else {
                alert("Максимум 5 файлов")
            }
        }
    </script>
</head>
<body>
    <h1>Загрузка картинок продукта ${productId}</h1>
    <form:form action="saveImage" method="POST"
               enctype="multipart/form-data">
        <table id="up"></table>
        <input type="submit" value="Сохранить">
        <input type="button" value="+" onclick="newInput()">
        <input type="hidden" value="${productId}" name="productId">
    </form:form>
    <input type="hidden" value="0" name="count" id="count">
</body>
</html>
