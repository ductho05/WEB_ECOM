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
<title>Top 10 san pham</title>
</head>
<body>
	<div class="line"></div>
	<div class="container" style="margin-left: 50px;">
        <div class="modal-content" style="width: 250px;margin-right: 100px;">
		<form action="">
		 <div class="modal-body">
		 	<div class="form-group">
		 		<input name = "filter" type="hidden" value="thoi-gian">
	            <label for="start" class = "form_statistical_item" >Ngày bắt đầu</label>
	            <input class="form_statistical_item" type="date" id="start_day" name="start_day" value="start_day">
          	</div>
          	<div class="form-group">
	            <label for="end" class = "form_statistical_item">Ngày kết thúc</label>
	            <input class="form_statistical_item" type="date" id="end_day" name="end_day" value="end_day">
           </div>
           <button class="btn btn-success">Thống kê</button>
           <a href = <c:url value = "/admin-chart/top10store?filter=tat-ca&start_day=&end_day="></c:url>>
             <input type="button"  class="btn btn-danger" data-dismiss="modal" value="Tất cả" style="margin-left: 40px;"></a>
		</div>
		
		</form>
		</div>
		<div class = "row">
        	<div id = "mythirdchart-column" style="margin-top: 20px;"></div>                 
            	<%
	            	ChartController chartController = new ChartController();
	             	String start_day = request.getParameter("start_day");
	          		String end_day = request.getParameter("end_day");
	          		String filter = request.getParameter("filter");
	          		
	          		out.println(chartController.myThirdChart(start_day, end_day,"column3d","mythirdchart-column",filter));
            	%>      
        	<div id = "mythirdchart-pie" style="margin-top: 50px;"></div>
        		<%
        			out.println(chartController.myThirdChart(start_day, end_day,"pie3d","mythirdchart-pie",filter));
        		%>           	            	
        </div>
	</div>
</body>
</html>