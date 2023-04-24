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
    	<h3 style = "font-weight: bold;">Sản phẩm</h3>
    </div>
    <ul>
 
    <c:forEach items = "${list_odi}" var = "od">
        <li class="list-group-item d-flex align-items-center justify-content-between flex-column flex-md-row" style="background-color: #f5f5f5;">
            <div class="d-flex align-items-center">
            	<c:url value = "/image?fname=product/${od.product.images}" var = "imgUrl"></c:url>
                <img width="130px" height="130px" src="${imgUrl}" alt="name" class="img-fluid" style="margin-left: 20px;"/>
                <h5 class="ml-3 mb-0">
                    <a href="#" style = "font-weight: bold;margin-left: 50px;">${od.product.productName}</a>
                </h5>
            </div>
            
            <h5 class="mb-0" style="margin-right: 100px;">
               <fmt:formatNumber value = "${od.product.price}" minFractionDigits="0"></fmt:formatNumber> VNĐ 
               * <fmt:formatNumber value = "${od.quantity}" minFractionDigits="0"></fmt:formatNumber> = 
               <b><fmt:formatNumber value = "${od.product.price*od.quantity}" minFractionDigits="0"></fmt:formatNumber> VNĐ</b>
            </h5>
        </li>
    </c:forEach>
        <li class="list-group-item bg-primary text-white">
            <h5 class="mb-0 text-right"style="margin-top: 20px; margin-right: 100px;padding: 10px;">
                Tổng tiền: <b><fmt:formatNumber value = "${o.total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</b>
            </h5>
        </li>
    </ul>
    </c:forEach>
    	
</body>
</body>
</html>