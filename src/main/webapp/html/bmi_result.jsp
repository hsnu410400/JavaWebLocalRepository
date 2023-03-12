<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button-style.css">
		<title>BMI Result Page</title>
	</head>
	<body style="padding: 20px">
		
		<form class="pure-form" method="get" action="/JavaWeb/html/bmi.html">
			<fieldset>
				<legend>BMI 計算結果</legend>
				<!-- 結果資料顯示 -->
				<%=request.getAttribute("output") %><p>
				<!-- 返回鍵 -->
				<button type="submit" class="pure-button pure-button-primary">返回</button>
			</fieldset>
		</form>
		
	</body>
</html>