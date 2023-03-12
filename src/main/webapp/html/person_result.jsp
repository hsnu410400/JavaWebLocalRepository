<%@ page import="java.util.Map"%>
<%@ page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String name      = request.getAttribute("name") + "";
String age       = request.getAttribute("age") + "";
String sex       = request.getAttribute("sex") + "";
String birth     = request.getAttribute("birth") + "";
String edu       = request.getAttribute("edu") + "";
String[] hobbies = (String[])request.getAttribute("hobbies");
String memo      = request.getAttribute("memo") + "";
Map<String, String> errorMsg = (Map<String, String>)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button-style.css">
		<title>Person Preview</title>
	</head>
	<body style="padding: 20px">
		<form class="pure-form" method="post" action="/JavaWeb/servlet/person_save">
			<fieldset>
				<legend>會員資料預覽</legend>
				姓名: <%=name %><p>
				年齡: <%=age %><p>
				姓別: <%=sex %><p>
				生日: <%=birth %><p>
				學歷: <%=edu %><p>
				興趣: <%=Arrays.toString(hobbies) %><p>
				備註: <pre><%=memo %></pre><p>
				<font style="color: red">	 	 
					<%=errorMsg.size() == 0?"":errorMsg+"<p>" %>
				</font>
				<button type="button"
						onclick="window.location.href='/JavaWeb/html/person.html';" 
						class="pure-button button-secondary">返回</button>
				<button type="submit" 
						<%=errorMsg.size() == 0?"":"disabled" %>
						class="pure-button pure-button-primary">儲存</button>
			</fieldset>
		</form>
		
	</body>
</html>