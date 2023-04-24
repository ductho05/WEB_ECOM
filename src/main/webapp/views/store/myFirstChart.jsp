<%@page import="iostart.Controller.Seller.StoreController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<%@page import="iostart.util.FusionCharts" %>
<%@page import="com.google.gson.Gson" %>
<%@page import="java.util.*" %>
<%@page import="iostart.Controller.Admin.*" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thong ke khach hang</title>
</head>
<body>
	<div id="myfirstchart-column">
		<%
			int sellerid = Integer.parseInt(request.getParameter("sellerid"));
			StoreController store = new StoreController();
			
			out.println(store.top10Customer(sellerid, "column3d", "myfirstchart-column"));
		%>
	</div>
	<div id="myfirstchart-pie">
		<%
			out.println(store.top10Customer(sellerid, "pie3d", "myfirstchart-pie"));
		%>
	</div>
</body>
</html>