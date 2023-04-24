<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Them vao gio hang</title>
</head>
<body style="height: 100%">
	<c:if test="${empty list_order_item}">
	<div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12">
       	<h1 class="fw-bold mb-0 text-black">Giỏ hàng trống</h1>
        </div>
       </div>
       </div>
	</c:if>
	<c:if test="${not empty list_order_item}">
	<section class="h-100 h-auto" style="background-color: #d2c9ff; height: auto;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center">
      <div class="col-12">
        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
        
          <div class="card-body p-0">
            <div class="row g-0">
              <div class="col-lg-8">
                <div class="p-5">
                  <div class="d-flex justify-content-between align-items-center mb-5">
                    <h1 class="fw-bold mb-0 text-black">Giỏ hàng</h1>                    
                  </div>
                  <c:forEach items = "${list_order_item}" var = "item">
                  
                  <hr class="my-4">
                  <div class="row mb-4 d-flex justify-content-between align-items-center">
                    <div class="col-md-2 col-lg-2 col-xl-2">
                    <c:url value = "/image?fname=product/${item.product.images}" var = "imgUrl"></c:url>
                      <img
                        src="${imgUrl}"
                        class="img-fluid rounded-3" alt="Cotton T-shirt">
                    </div>
                    <div class="col-md-3 col-lg-3 col-xl-3">
                      <h6 class="text-black mb-0">${item.product.productName}</h6>
                    </div>
                    <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                    <form action="cart-update-quantity" method="post">
                      <input type = "hidden" name = "cartitemid" value = "${item.id}">
                      <input type="number" name = "quantity" value="${item.quantity}" aria-label="Search" class="form-control" style="width: 100px;">
                      <button class="btn btn-primary" type="submit" style="margin-top: 5px;padding-right: 37px;padding-left: 37px;">Lưu               		
              		  </button>
              		</form>
                    </div>
                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                      <h6 class="mb-0"><fmt:formatNumber value="${item.unitprice}" minFractionDigits="0"></fmt:formatNumber> VNĐ</h6>
                    </div>
                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                      <a href=<c:url value = "/home/cart-delete-cartitem?cartitemid=${item.id}"></c:url> class="text-muted"><i class="fas fa-times"></i></a>
                    </div>
                  </div>
               
                  </c:forEach>
                  <hr class="my-4">

                  <div class="pt-5">
                    <h6 class="mb-0"><a href=<c:url value = "/home?index=0&filter=tat-ca&cid=0"></c:url> class="text-body"><i
                          class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                  </div>
                </div>
              </div>             
              <div class="col-lg-4 bg-grey">
                <div class="p-5">
                  <h3 class="fw-bold mb-5 mt-2 pt-1">Tóm tắt</h3>             
                  <hr class="my-4">
                  <div class="d-flex justify-content-between mb-5">
                    <h5 class="text-uppercase">Tổng số tiền</h5>
                    <h5><fmt:formatNumber value="${total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</h5>                  	
                  </div>
                  <a href=<c:url value = "/home/cart-checkout"></c:url>><button type="button" class="btn btn-dark btn-block btn-lg"
                    data-mdb-ripple-color="dark">Thanh toán</button></a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</c:if>
</body>
</html>