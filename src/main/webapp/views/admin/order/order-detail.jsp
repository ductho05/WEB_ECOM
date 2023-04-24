<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Detail</title>
</head>
<body>
	
	<c:forEach items = "${list_o}" var = "o">
    <div style="margin-left: 45px;">
    
    	<h2 class="mt-4" style="color:#007bff ;">Mã đặt - ${o.id}</h2>
    	<h5 style = "font-weight: bold;">Người nhận: ${o.name}</h5>
    	<h5 style = "font-weight: bold;">Số điện thoại: ${o.phone}</h5>
    	<h5 style = "font-weight: bold;">
        	Địa chỉ: ${o.address}, ${o.city}
    	</h5>
   	 	<h5 style = "font-weight: bold;">Trạng thái: 
   	 		<span class="badge badge-primary">${o.status}</span>
   	 	</h5>
    	<h5 style = "font-weight: bold;">Thời gian: ${o.created}</h5>
		<form action="admin-save" method="post">
		
				<input name="order_id" type="hidden" class="form-control" value="${o.id}">
				<span class="badge badge-warning" style="padding: 10px;margin-bottom: 10px;margin-top: 10px;font-size: 14px;color: white;">Thay đổi trạng thái đơn hàng</span>
				<div class="select">
				  <select class="form-control selcls form_statistical_item" name="status" id="" style="width: 213px;">
				  <option value="" selected disabled hidden>${o.status}</option>
				  <option value="Đang chờ xác nhận">Đang chờ xác nhận</option>
				  <option value="Đã xác nhận">Đã xác nhận</option>
				  <option value="Đang giao hàng">Đang giao hàng</option>
				  <option value="Đã hoàn thành">Đã hoàn thành</option>
				    
				  </select>
				 </div>
                <div class="modal-footer" style="margin-right: 920px;margin-top: 70px;">
                    <input type="submit" class="btn btn-success" value="Save">
                </div>
            </form>
    	<h3 style = "font-weight: bold;">Sản phẩm</h3>
    </div>
    <ul>
 
    <c:forEach items = "${list_odi}" var = "od">
        <li class="list-group-item d-flex align-items-center justify-content-between flex-column flex-md-row">
            <div class="d-flex align-items-center">
            	<c:url value = "/image?fname=product/${od.product.images}" var = "imgUrl"></c:url>
                <img width="50px" height="50px" src="${imgUrl}" alt="name" class="img-fluid" />
                <h5 class="ml-3 mb-0">
                    <a href="#" style = "font-weight: bold;">${od.product.productName}</a>
                </h5>
            </div>
            
            <h5 class="mb-0">
               <fmt:formatNumber value = "${od.product.price}" minFractionDigits="0"></fmt:formatNumber> VNĐ 
               * <fmt:formatNumber value = "${od.quantity}" minFractionDigits="0"></fmt:formatNumber> = 
               <b><fmt:formatNumber value = "${od.product.price*od.quantity}" minFractionDigits="0"></fmt:formatNumber> VNĐ</b>
            </h5>
        </li>
    </c:forEach>
        <li class="list-group-item bg-primary text-white">
            <h5 class="mb-0 text-right">
                Tổng tiền: <b><fmt:formatNumber value = "${o.total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</b>
            </h5>
        </li>
    </ul>
    </c:forEach>
    	
</body>
</body>
</html>