<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thong tin tai khoan</title>
</head>
<body>
	<div class="wrapper">
	<div id="content">
		<form action="user-profile" method="post" enctype="multipart/form-data">
		<div class="container rounded bg-white mt-5 mb-5">
	    <div class="row">   	
	        <div class="col-md-4 border-right">
	            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
	            	<h4 class="text-right">Thông tin khách hàng</h4>
	            	<c:url value = "/image?fname=users/${user.images}" var = "urlImg"></c:url>
	            	<img class="rounded-circle mt-5" width="150px" src="${urlImg}">
	            	<span class="font-weight-bold">${user.username}</span>
	            	<span class="text-black-50">${user.email}</span>
	            	<div class="row mt-2">
	                    <label class="mr-2">Name: ${user.fullname}</label><br>
	                    <label class="mr-2">Phone: ${user.phone}</label>                 
	                </div>               
	            </div>
	        </div>      
	        <div class="col-md-8 border-right">
	            <div class="p-3 py-5">
	                <div class="d-flex justify-content-between align-items-center mb-3">
	                    <h4 class="text-right">Chỉnh sửa thông tin</h4>
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">ID</label>
	                    	<input type="text" class="form-control" placeholder="ID" name = "userid" value="${user.userid}">
	                    	<input type="hidden" class="form-control" name = "password" value="${user.password}">
	                    	
	                    	<input type="hidden" class="form-control" name = "status" value="${user.status}">
	                    	
	                    	<input type="hidden" class="form-control" name = "roleid" value="${user.roleid}">
	                    	<input type="hidden" class="form-control" name = "created" value="${user.created}">	                    	
	                    </div>                 
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">User name</label>
	                    	<input type="text" class="form-control" placeholder="Tên tài khoản" name = "username" value="${user.username}">	                    	
	                    </div>                 
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">Email</label>
	                    	<input type="text" class="form-control" placeholder="Địa chỉ email" name = "email" value="${user.email}">	                    	
	                    </div>                 
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">Name</label>
	                    	<input type="text" class="form-control" placeholder="Tên khách hàng" name = "fullname" value="${user.fullname}">
	                    </div>                 
	                </div>	                
	                <div class="row mt-3">
	                    <div class="col-md-12">                  
	                    	<label class="mr-2">Images</label><br> 
	                    	<c:url value = "/image?fname=users/${user.images}" var = "urlImg"></c:url>                  	
	                    	<img alt="" src="${urlImg}" width="150px"><br>    
	                    	<input type="file" name="images" style="margin-top: 15px;">
	                    </div>                 
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">Phone</label>
	                    	<input type="text" class="form-control" placeholder="Số điện thoại" name = "phone" value="${user.phone}">	                    	
	                    </div>                 
	                </div>               
	            </div>
	        </div>     
	        <div class="mt-5 text-center">
	        	<button class="btn btn-primary profile-button" type="submit">Lưu thông tin</button>
	        </div>    		    	
	    </div>
	</div>
  </form>
	</div>
		<h2 class="text-center">Các đơn hàng của bạn</h2>
		<ul class="list-group my-3 resetpass-shadow " style="width: 80%; margin-left: 160px;">
		<c:if test="${not empty list}">
			<c:forEach items = "${list}" var = "item">
			  <li class="list-group-item ">
			    <h5 class="mb-2">	
			      <span class="badge badge-success">
			        ${item.status}
			      </span>
			    </h5>
			    <h3>
			      <a href=<c:url value = "/user-order-detail?order_id=${item.id}"></c:url>>Mã đơn hàng: ${item.id}</a>
			    </h3>
			    <h5><fmt:formatNumber value = "${item.total_price}" maxFractionDigits="0"></fmt:formatNumber> VNĐ</h5>		   
			    <p class="mb-0">
			      Địa chỉ: ${item.address}, ${item.city}
			    </p>
			    <p class="mb-0">Thời gian: ${item.created}</p>
			  </li>
			  </c:forEach>
		  </c:if>
		  <c:if test="${empty list}">		  		  
			  <li class="list-group-item text-center py-4">
			    <h3 class="font-weight-light">Bạn chưa có đơn hàng nào</h3>
			    <a href="{% url 'product:productlist' %}" class="btn btn-outline-info mt-3">
			     Tiếp tục shopping
			    </a>
			  </li> 
		  </c:if>
		</ul>
	</div>
</body>
</html>