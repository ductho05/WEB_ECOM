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
<title>Thong ke san pham</title>
</head>
<body>
	<div id="mysecondchart-column">
		<%
			int sellerid = Integer.parseInt(request.getParameter("sellerid"));
		 	StoreController store = new StoreController();
		 	out.println(store.top10Product(sellerid, "column3d", "mysecondchart-column"));
		%>
	</div>
	<div id="mysecondchart-pie">
		<%
			out.println(store.top10Product(sellerid, "pie3d", "mysecondchart-pie"));
		%>
	</div>
</body>
</html>