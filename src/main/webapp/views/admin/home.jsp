<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<%@page import="iostart.util.FusionCharts" %>
<%@page import="com.google.gson.Gson" %>
<%@page import="java.util.*" %>
<%@page import="iostart.Services.IProductServices" %>
<%@page import="iostart.Services.Impl.ProductServicesImpl" %>
<%@page import="iostart.Controller.Admin.*" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<div class="line"></div>
	<div class="container">
        <div class="row">
            <div class="col-md-3 box box-item1">
                <div class="row box_content">
                    <div class="col-sm-6">
                        <img src="https://cdn-icons-png.flaticon.com/128/8163/8163551.png" alt="">
                    </div>
                    <div class="col-sm-6 box_content">
                        <h5>MEMBERS</h5>
                        <p class="item-style">${num_Users}</p>
                       
                    </div>
                </div>
            </div>
            <div class="col-md-3 box box-item2">
                <div class="row box_content">
                    <div class="col-sm-6">
                        <img src="https://cdn-icons-png.flaticon.com/128/2258/2258432.png" alt="">
                    </div>
                    <div class="col-sm-6 box_content">
                        <h5>SALES</h5>
                        <p class="item-style">${num_product}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 box box-item3">
                <div class="row box_content">
                    <div class="col-sm-6">
                        <img src="https://cdn-icons-png.flaticon.com/128/1728/1728946.png" alt="">
                    </div>
                    <div class="col-sm-6 box_content">
                        <h5>INCOMES</h5>
                        <p class="item-style"><fmt:formatNumber value="${incomes}" minFractionDigits="0"></fmt:formatNumber> VNĐ</p>
                    </div>
               </div>
            </div>
        </div>
        <div>
        
        </div>
        <div class = "row">
			<div class="dropdown" style="margin-top: 50px;margin-left: 20px;">
				<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"aria-expanded="false">Filter</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item" href=<c:url value = "/admin-home?flag=0"></c:url>>Tất cả</a>
					<a class="dropdown-item"href=<c:url value = "/admin-home?flag=1"></c:url>>Đã bán</a> 
				</div>
			</div>
		</div>
		<div class = "row">
        	<div class="col-sm-6" style="margin-top: 50px;">
                 <div id = "chart-column"></div>
                 <%
                 HomeController home = new HomeController();
                 String flag = request.getParameter("flag");
                 out.println(home.myFirstchart(flag));
                 %> 
                
            </div>
			
            <div class="col-sm-6" style="margin-top: 50px;">
           		<div id = "chart-pie"></div>
           		<%out.println(home.mySecondchart(flag)); %> 
            </div>
        </div>
	</div>
</body>
</html>