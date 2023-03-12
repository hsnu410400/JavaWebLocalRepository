<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index Page</title>
</head>
<body>
    Hello 我的 JSP Page: <%=new Date() %>
    <ol>
        <li><a href="/JavaWeb/servlet/report">Report servlet</a></li>
        <li><a href="/JavaWeb/servlet/time">Time servlet</a></li>
        <li><a href="/JavaWeb/servlet/spa/">SPA servlet</a></li>
    </ol>
</body>
</html>