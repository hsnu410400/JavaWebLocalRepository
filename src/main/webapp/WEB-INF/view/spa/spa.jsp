<%@page import="spa.entity.Master"%>
<%@page import="spa.entity.Spa"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    List<Spa> spaList = (List<Spa>)request.getAttribute("spaList");
    List<Master> masterList = (List<Master>)request.getAttribute("masterList");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/JavaWeb/css/pure-min.css">
        <link rel="stylesheet" href="/JavaWeb/css/button-style.css">
        <title>Spa Page</title>
    </head>
    <body style="padding: 0px">
        <!-- Title  -->
        <%@include file="/WEB-INF/view/spa/spa_title.jspf" %>
        <!-- Spa Table -->
        <table style="padding: 20px">
            <tr>
                <td valign="top">
                    <!-- Spa 預約 -->
                    <%@include file="/WEB-INF/view/spa/spa_reserve.jspf" %>
                </td>
                <td valign="top">
                    <!-- Spa 產品說明 -->
                    <%@include file="/WEB-INF/view/spa/spa_product.jspf" %>
                </td>
            </tr>
        </table>
        
    </body>
</html>