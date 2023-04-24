<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light white scrolling-navbar">
	
    <div class="container">
      <a class="navbar-brand waves-effect" target="_blank">
        <strong class="blue-text">TNH</strong>
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link waves-effect" href=<c:url value = "/home?index=0&filter=tat-ca&cid=0"></c:url>>Home
            </a>
          </li>       
        </ul>
        <ul class="navbar-nav nav-flex-icons" style="margin-left: 50px;">
        <c:if test="${not empty Users }">
        <c:if test="${Users.roleid == 2}">
        <li class="nav-item">
            <a class="nav-link waves-effect" href = <c:url value = "/seller-home/list-seller?index=0&filter=tat-ca"></c:url>>             
              Quản lý cửa hàng
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link waves-effect" href = <c:url value = "/home/cart-detail"></c:url>>
              <span class="badge red z-depth-1 mr-1" style="color: red;"> ${num_item} </span>
              <i class="fas fa-shopping-cart"></i>
              <span class="clearfix d-none d-sm-inline-block"> Cart </span>
            </a>
          </li>
          <li class="nav-item">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="nav navbar-nav ml-auto">
						<div class="dropdown" style="margin-right: 80px;">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown">${Users.username}</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href="#">Thay mật khẩu</a>
								<a class="dropdown-item" href=<c:url value = "/home?action=logout"></c:url>>Đăng xuất</a>
								<a class="dropdown-item" href=<c:url value = "/user-profile?uid=${Users.userid}"></c:url>>Quản lí tài khoản</a>
							</div>
						</div>					
					</ul>
		  </div> 
          </li>
          </c:if>
          <c:if test="${Users.roleid == 3}">       
          <li class="nav-item">
            <a class="nav-link waves-effect" href = <c:url value = "/home/cart-detail"></c:url>>
              <span class="badge red z-depth-1 mr-1" style="color: red;"> ${num_item} </span>
              <i class="fas fa-shopping-cart"></i>
              <span class="clearfix d-none d-sm-inline-block"> Cart </span>
            </a>
          </li>
          <li class="nav-item">
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
		  		<ul class="nav navbar-nav ml-auto">
						<div class="dropdown" style="margin-right: 80px;">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton" data-toggle="dropdown">${Users.username}</button>
							<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
								<a class="dropdown-item" href=<c:url value = "/change_password"></c:url>>Thay mật khẩu</a>
								<a class="dropdown-item" href=<c:url value = "/home?action=logout"></c:url>>Đăng xuất</a>
								<a class="dropdown-item" href=<c:url value = "/user-profile?uid=${Users.userid}"></c:url>>Quản lí tài khoản</a>
							</div>
						</div>					
					</ul>		
		  </div>          
          </li>
          </c:if>
          </c:if>
          <c:if test="${empty Users}">
          <li class="nav-item">
            <a class="nav-link waves-effect" href = <c:url value = "/dang-nhap?action=login"></c:url>>             
              Đăng nhập
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href = <c:url value = "/dang-ky?action=register"></c:url>>            
              Đăng ký
            </a>
          </li>
                    
          </c:if>       
        </ul>
      </div>
    </div>
  </nav>
</body>
</html>