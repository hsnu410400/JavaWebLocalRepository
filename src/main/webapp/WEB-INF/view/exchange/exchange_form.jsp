<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Exchange Form</title>
		<link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
		<link rel="stylesheet" href="/JavaWeb/css/button-style.css">
	</head>
	<body style="padding: 20px">
		
		<form class="pure-form" method="post" action="/JavaWeb/exchange">
			<fieldset>
				<legend>Exchange Form</legend>
				<select id="symbol" name="symbol">
					<option value="TWD" selected>台幣 TWD</option>
					<option value="JPY">日幣 JPY</option>
					<option value="USD">美金 USD</option>
					<option value="CNY">人民幣 CNY</option>
					<option value="EUR">歐元 EUR</option>
				</select>
				
				<svg width="20" style="transform: rotate(90deg);fill:#6e7780;stroke:#6e7780;stroke-width:0;vertical-align:bottom" height="25" viewBox="0 0 24 24" data-icon="reorder"><path d="M14.707 8.12c-.39.392-1.023.392-1.414 0-.39-.39-.39-1.022 0-1.413L17 3l3.707 3.707c.195.194.293.45.293.707 0 .256-.098.512-.293.707-.39.392-1.023.392-1.414 0L18 6.83v13.586h-2V6.828L14.707 8.12zm-10 7.173c-.39-.39-1.023-.39-1.414 0-.39.39-.39 1.023 0 1.414L7 20.414l3.707-3.707c.195-.194.293-.45.293-.707 0-.256-.098-.512-.293-.707-.39-.39-1.023-.39-1.414 0L8 16.586V3H6v13.586l-1.293-1.293z"></path></svg>
				
				<select id="symbol" name="symbol">
					<option value="TWD">台幣 TWD</option>
					<option value="JPY" selected>日幣 JPY</option>
					<option value="USD">美金 USD</option>
					<option value="CNY">人民幣 CNY</option>
					<option value="EUR">歐元 EUR</option>
				</select>
				
				<input type="number" id="amount" name="amount" placeholder="請輸入金額">
				
				<button type="submit" class="pure-button pure-button-primary">換匯</button>
			</fieldset>
		</form>
		
	</body>
</html>