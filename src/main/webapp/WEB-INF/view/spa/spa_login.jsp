<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
        <link rel="stylesheet" href="/JavaWeb/css/button-style.css">
        <title>Spa Login</title>
    </head>
    <body style="padding: 0px">
        <!-- Title  -->
        <%@include file="/WEB-INF/view/spa/spa_title.jspf" %>
        <!-- Spa Table -->
        <table style="padding: 20px">
            <tr>
                <td valign="top">
                    <!-- Spa Login -->
                    <form class="pure-form" method="post" action="/JavaWeb/servlet/spa/login">
                        <fieldset>
                            <legend>Spa Login</legend>
                            Username: <input type="text" id="username" name="username" required><p>
                            Password: <input type="password" id="password" name="password"  required><p>
                            <button type="submit" class="pure-button pure-button-primary">登入</button>
                        </fieldset>
                    </form>
                </td>
            </tr>       
        </table>
    </body>
</html>