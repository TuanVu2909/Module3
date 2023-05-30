<%@ page import="java.util.List" %>
<%@ page import="Date25_05.Student" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/25/2023
  Time: 11:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="ClassServlet" >Create new Student</a><br>
<form action="/view" method="get">

    <c:forEach items="${arr}" var="i">
       <p><c:out value="${i}"></c:out> </p>
       <a href="/UpdateBySelect?updateId=${i.id}">sửa</a>
        <a href="/DeleteStudent?deleteId=${i.id}">xóa</a>
    </c:forEach>
</form>
</form>
</body>
</html>
