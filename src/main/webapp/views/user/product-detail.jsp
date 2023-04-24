<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiet san pham</title>
</head>
<body>
  <main class="mt-5 pt-4">
    <div class="container dark-grey-text mt-5">
      <div class="row wow fadeIn">
        <div class="col-md-6 mb-4">
			<c:url value = "/image?fname=product/${product.images}" var = "imgUrl"></c:url>
          <img src="${imgUrl}" class="img-fluid" alt="img">

        </div>
        <div class="col-md-6 mb-4">
          <div class="p-4">
            <div class="mb-3">            
                <span class="badge badge-primary" style="padding: 5px;font-size: 30px;">Chi tiết sản phẩm</span>                    
            </div>
            <p class="lead" style="font-weight: bold;">             
              <span><fmt:formatNumber value="${product.price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</span>
            </p>
            <p class="lead font-weight-bold">Mô tả</p>
            <p>${product.description}</p>
            <form class="d-flex justify-content-left" action="cart-addtocart" method="post">
              <input type="hidden" name = "pid" value = "${product.productId}">
              <input type="number" name = "quantity" value = "1" aria-label="Search" class="form-control" style="width: 100px;">
              <button class="btn btn-primary btn-md my-0 p" type="submit" style="margin-left: 10px;">Thêm vào giỏ hàng
                <i class="fas fa-shopping-cart ml-1"></i>
              </button>
            </form>
          </div>
        </div>
      </div>
      <hr>
      </div>
  </main>
</body>
</html>