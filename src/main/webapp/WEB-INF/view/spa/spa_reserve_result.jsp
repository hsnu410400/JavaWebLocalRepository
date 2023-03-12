<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button-style.css">
		<title>Spa Reserve Result Page</title>
	</head>

	<body style="padding: 0px">
		<!-- Title  -->
		<%@include file="/WEB-INF/view/spa/spa_title.jspf" %>
		<!-- 本次預約單  -->
		<div style="padding: 20px;display: ${order == null ? 'none':''}">
			<%@ include file="/WEB-INF/view/spa/spa_reserve_current.jspf"%>
		</div>
		<!-- 歷史預約單  -->
		<div style="padding: 20px">
			<%@ include file="/WEB-INF/view/spa/spa_reserve_history.jspf"%>
		</div>
		<!-- 返回鍵  -->
		<div style="padding: 20px">
			<button type="button" 
					onclick="location.href='/JavaWeb/servlet/spa/';"
					class="pure-button button-secondary">返回</button>
		</div>
	</body>
</html>