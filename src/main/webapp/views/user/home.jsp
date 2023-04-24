<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>	
  <main>
    <div class="container">
      
      <nav class="navbar navbar-expand-lg navbar-dark mdb-color lighten-3 mt-3 mb-5" style="background: #929fba;padding: 5px;">
        
        <span class="navbar-brand">Danh mục:</span>
      
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#basicExampleNav"
          aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
       
        <div class="collapse navbar-collapse" id="basicExampleNav">
          <!-- Links -->
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
              <a class="nav-link" href=<c:url value = "/home?index=0&filter=tat-ca&cid=0"></c:url>>Tất cả
              </a>
            </li>
            <c:forEach items = "${list_cate}" var = "item">
            <li class="nav-item">
              <a class="nav-link" href=<c:url value = "/home?index=0&filter=categories&cid=${item.categoryId}"></c:url>>${item.categoryname}</a>
            </li>
            </c:forEach>          
          </ul>

          <form class="form-inline" action="search-product?index=0" method="get">
            <div class="md-form my-0">
              <input type="hidden" value="0" name = "index">
              <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search" name = "name">
              <button id="search-button" type="submit" class="btn btn-primary">
    			<i class="fas fa-search"></i>
    			</button>
            </div>
          </form>
        </div>

      </nav>

      <c:if test="${empty list}">
      	<div>
      		<h5>Không tìm thấy sản phẩm phù hợp</h5>
      	</div>
      </c:if>
      <c:if test="${not empty list}">
      	<section class="text-center mb-4">
		
		<div class = "container">
			<div class = "row">
			<c:forEach items = "${list}" var = "item">
				<div class = "col-lg-3 col-md-4" style="margin-top: 10px;">
					<div class="card mx-auto">
					<c:url value = "/image?fname=product/${item.images}"  var = "imgUrl"></c:url>
            		<img style = "object-fit: cover" width="100%" height="250" src="${imgUrl}"/>
            	<div class="card-body text-center mx-auto">
                <div class='cvp'>
                    <h5 class="card-title font-weight-bold">${fn:substring(item.productName,0,15)}...</h5>
                    <p class="card-text"><fmt:formatNumber value="${item.price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</p>
                    <a href=<c:url value = "/home/product-detail?pid=${item.productId}"></c:url> class="btn details ">view details</a><br />
                    
                </div>
            	</div>
          </div>
				</div>
				</c:forEach>
        		
        </div>
        </div>
   		
       </section>
      
     <nav class="d-flex justify-content-center wow fadeIn">
       <div class="clearfix">
					
					<ul class="pagination">
					<li class="page-item disabled"><a href=<c:url value = "/home?index=${index-1}&filter=${filter}&cid=${cid}"></c:url>>Previous</a></li>
						<c:forEach begin = "0" end = "${sizepage-1}" var = "item" >
						<li class="page-item"><a href=<c:url value = "/home?index=${item}&filter=${filter}&cid=${cid}"></c:url> class="page-link">${item}</a></li>
						</c:forEach>
						<li class="page-item"><a href=<c:url value = "/home?index=${index+1}&filter=${filter}&cid=${cid}"></c:url> class="page-link">Next</a></li>
					</ul>
				</div>       
      </nav>
      </c:if>
       
    </div>
    </main>
</body>
</html>