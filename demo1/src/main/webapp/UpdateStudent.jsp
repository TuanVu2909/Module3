<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2023
  Time: 9:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/UpdateStudent" method="post">

    <label>Nhập ID:</label><input type="text" name="id" value="${student.id}">
    <label>Nhập Name:</label><input type="text" name="name" value="${student.name}">
    <select name="className">
        <option selected>Class</option>
        <c:forEach items="${arrayClass}" var="i">
            <option value="${i.getName()}">${i.getName()}</option>
        </c:forEach>
    </select>
  <button type="submit">Update</button>
    <a href="/view">back go home</a>

</form>
</body>
</html>
