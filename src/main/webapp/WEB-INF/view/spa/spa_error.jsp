<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
        <link rel="stylesheet" href="/JavaWeb/css/button-style.css">
        <title>Spa Error</title>
    </head>
    <body style="padding: 0px">
        <!-- Title  -->
        <%@include file="/WEB-INF/view/spa/spa_title.jspf" %>
        <!-- Spa Table -->
        <table style="padding: 20px">
            <tr>
                <td valign="top">
                    <!-- Spa Error -->
                    <form class="pure-form">
                        <fieldset>
                            <legend>Spa Errow:<%=exception.getMessage() %></legend>
                        </fieldset>
                    </form>
                </td>
            </tr>       
        </table>
    </body>
</html>