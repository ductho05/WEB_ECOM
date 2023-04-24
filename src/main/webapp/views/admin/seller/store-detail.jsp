<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiet cua hang</title>
</head>
<body>
    <div class="container-xl">
    <c:forEach items = "${new_list}" var = "s">
        <div class="row">
            <div class="col-md-4">
                <h5 class="text-primary">Thông tin cửa hàng</h5>                            
                <h3>${s.sellername}</h3>
                <form action="save" method="post">
                <input type = "hidden" name = "sellerid" value = "${s.sellerid}"> 
                <div class="row flex-lg-row">
                        <div class="col-md-6">
                        <c:if test="${s.isactive == true}">
                            <div class="select">
                                <select class="form-control selcls form_statistical_item" name="status" id="">
                                    <option value="" selected disabled hidden>Cấp phép</option>
                                    <option value="false">Cấm</option>
                                    <option value="true">Cấp phép</option>                     
                                </select>
                            </div>
                       </c:if>
                       <c:if test="${s.isactive == false}">
                            <div class="select">
                                <select class="form-control selcls form_statistical_item" name="active" id="" style="width: 213px;">
                                    <option value="" selected disabled hidden>Cấm</option>
                                    <option value="false">Cấm</option>
                                    <option value="true">Cấp phép</option>                     
                                </select>
                            </div>
                       </c:if>
                            <div class="modal-footer" style="margin-right: 90px;margin-top: 50px;margin-bottom: 20px;">
                                <input type="submit" class="btn btn-success" value="Save">
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            
                        </div>                   
                </div>
            </form>
            
                <h5 class="text-primary">Thông tin chủ cửa hàng</h5>
                <h6>
	                <c:url value = "/image?fname=users/${s.user.images}" var = "imgUrl"></c:url>
	                <img src="${imgUrl}" alt="avartar">
	                ${s.user.fullname}
                </h6>
                <h6>Điện thoại: ${s.user.phone}</h6>
                <h6>Ngày tham gia: <fmt:formatDate type = "both" dateStyle="short"  value="${s.createat}"/></h6>
            </div>
            <div class="col-md-8">
            	<div id="demo" class="carousel slide" data-bs-ride="carousel">

  <!-- Indicators/dots -->
				  <div class="carousel-indicators">
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
				    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
				  </div>
				
				  <!-- The slideshow/carousel -->
				  <div class="carousel-inner">
				    <c:forEach items = "${listslide}" var = "sl">
				    <div class="carousel-item active">
				    	<c:url value = "/image?fname=slides/${sl.slideimages}" var = "imgUrl"></c:url>
				      	<img src="${imgUrl}" alt="Chicago" class="d-block" style="width:100%; height: 350px;object-fit: cover;">
				    </div>				    
				  	</c:forEach>
				  </div>
				
				  <!-- Left and right controls/icons -->
				  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon"></span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
				    <span class="carousel-control-next-icon"></span>
				  </button>
				</div>
            </div>
            
        </div>
        </c:forEach>
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>Danh sách các sản phẩm</h2>
                            </div>
                            <div class="col-sm-7">
                                <div class="dropdown">
                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                      Filter
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                      <a class="dropdown-item" href="#">Tất cả</a>
                                      <a class="dropdown-item" href="#">Cấp phép</a>
                                      <a class="dropdown-item" href="#">Cấm</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>						
                                <th>Price</th>
                                <th>Amount</th>
                                <th>Image</th>
                                <th>Status</th> 
                                <th>Action</th>                  
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items = "${new_listp}" var = "item">
                            <tr>
                                <td>${item.productId}</td>
                                <td><a href="#">${item.productName}</a></td>                  
                                <td>${item.price} VNĐ</td>                        
                                <td>${item.amount}</td>
                                <td>
                                <c:url value = "/image?fname=product/${item.images}" var = "imgUrl"></c:url>
                                <img src="${imgUrl}" class="image" alt="Avatar">
                                </td>
                                <td>
                                	<c:if test="${item.status == true }">
                                		<span class="status text-success">&bull;</span> Cấp phép
                                	</c:if>
                                	<c:if test="${item.status == false }">
                                		<span class="status text-danger">&bull;</span> Cấm
                                	</c:if>
                                </td>
                                <td>
                            		<a href=<c:url value = "/admin-product/update?pid=${item.productId}"></c:url> class="settings" title="Update" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>                     
                        		</td>
                            </tr>
                        </c:forEach>    
                        </tbody>
                    </table>
                </div>
            </div>
        </div> 
</body>
</html>