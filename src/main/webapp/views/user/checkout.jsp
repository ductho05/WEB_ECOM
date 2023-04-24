<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanh toan</title>
</head>
<body>
	<%
		Calendar createat = Calendar.getInstance();
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
	
		String createDate = String.valueOf(dfm.format(createat.getTime()));
	%>
	<main class="mt-5 pt-4">
  
    <div class="container wow fadeIn">
      <h2 class="my-5 h2 text-center">Nhập thông tin đặt hàng</h2>
      <div class="row">
        <div class="col-md-12 mb-4" style="width: 50%;margin-left: 280px;">
          <div class="card">
            <form class="card-body" action="cart-checkout" method="post">
              <div class="row">
              <div class="md-form mb-5">
              <label for="email" class="">Name</label>
                <input type="text" class="form-control" placeholder="Nhập tên người đặt" name = "name">            
              </div>                             
              <div class="md-form mb-5">
                <label for="email" class="">Address</label>
                <input type="text"  class="form-control" placeholder="Nhập địa chỉ" name = "address">
              </div>
              <div class="md-form mb-5">
              	<label for="address" class="">City</label>
                <input type="text" class="form-control" placeholder="Nhập tên thành phố" name = "city">
                
              </div>
              <div class="md-form mb-5">
              	<label for="address-2" class="">Phone</label>
                <input type="text" class="form-control" placeholder="Nhập số điện thoại" name = "phone">                
                <input type="hidden" name = "paid" value = "0">
                <input type="hidden" name = "status" value = "Đang chờ xác nhận">
                <input type="hidden" name = "created" value = "<%=createDate%>">
                <input type="hidden" name = "user_id" value = "${Users.userid}">
                <input type="hidden" name = "total_price" value = "${total_price}">
              </div>
              <div class="md-form mb-5">
                <span>Tổng tiền</span>
              	<strong><fmt:formatNumber value="${total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</strong>
              </div>
              
              </div>
              <hr>
              <hr class="mb-4">
              <button class="btn btn-primary btn-lg btn-block" type="submit">Thanh toán</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </main>
</body>
</html>