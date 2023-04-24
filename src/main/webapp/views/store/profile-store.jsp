<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thong tin cua hang</title>
</head>
<body>
   
	<div class = "container">
		<c:url value = "/image?fname=slides/${seller.slides[0].slideimages}" var = "imgUrl"></c:url>
		<img alt="" src="${imgUrl}" style="width: 100%; height: 300px; object-fit: cover;">
	</div>
	<form action="store-profile" method="post" enctype="multipart/form-data">
		<div class="container rounded bg-white mt-5 mb-5">
	    <div class="row">   	
	        <div class="col-md-3 border-right">
	            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
	            	<h4 class="text-right">Chủ cửa hàng</h4>
	            	<c:url value = "/image?fname=users/${seller.user.images}" var = "imgUrl"></c:url>
	            	<img class="rounded-circle mt-5" width="150px" src="${imgUrl}">
	            	<span class="font-weight-bold">${seller.user.username}</span>
	            	<span class="text-black-50">${seller.user.email}</span>
	            	<div class="row mt-2">
	                    <label class="mr-2">Name: ${seller.user.fullname}</label><br>
	                    <label class="mr-2">Phone: ${seller.user.phone}</label>                 
	                </div>               
	            </div>
	        </div>      
	        <div class="col-md-5 border-right">
	            <div class="p-3 py-5">
	                <div class="d-flex justify-content-between align-items-center mb-3">
	                    <h4 class="text-right">Chỉnh sửa thông tin cửa hàng</h4>
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">ID</label>
	                    	<input type="text" class="form-control" placeholder="ID" name = "sellerid" value="${seller.sellerid}">
	                    	<input type="hidden" class="form-control"  name = "ownerid" value="${seller.ownerid}">
	                    	<input type="hidden" class="form-control"  name = "isactive" value="1">	                    	
	                    </div>                 
	                </div>
	                <div class="row mt-2">
	                    <div class="col-md-12">
	                    	<label class="mr-2">Name</label>
	                    	<input type="text" class="form-control" placeholder="Tên cửa hàng" name = "sellername" value="${seller.sellername}">
	                    </div>                 
	                </div>
	                <div class="row mt-3">
	                    <div class="col-md-12">
	                    	<label class="mr-2">Description</label>
	                    	<textarea name="bio" class="form-control"  required >${seller.bio}</textarea>
	                    </div>          
	                </div>
	                <div class="row mt-3">
	                    <div class="col-md-12">                  
	                    	<label class="mr-2">Images</label><br>
	                    	<c:url value = "/image?fname=seller/${seller.images}" var = "imgUrl"></c:url>
	                    	<img alt="" src="${imgUrl}">      
	                    	<input type="file" name="images" style="margin-top: 15px;">
	                    </div>                 
	                </div>               
	            </div>
	        </div>     
	        <div class="col-md-4">
	            <div class="p-3 py-5">
	                <div class="d-flex justify-content-between align-items-center experience"><h4 class="text-right">Trạng thái cửa hàng</h4></div><br>
	                	<div class="col-md-12">
	                		<c:if test="${seller.isopen == true}">
	                			<span class="badge badge-success" style = "font-size: 16px;">Đang hoạt động</span>
	                		</c:if>
	                		<c:if test="${seller.isopen == false}">
	                			<span class="badge badge-danger" style = "font-size: 16px;">Đóng cửa</span>
	                		</c:if>
	                	</div> <br>
	                	<div class="col-md-12"><label class="mr-2" style="font-weight: bold;">Thay đổi trạng thái</label>
	                		<select class="browser-default custom-select" name="isopen">
	                            <option value="1">Hoạt động</option>
	                            <option value="0">Đóng</option>
	                        </select>
	              	 </div>
	            </div>
	        </div>
	        <div class="mt-5 text-center" style="margin-left: 450px;">
	        	<button class="btn btn-primary profile-button" type="submit">Lưu thông tin</button>
	        </div>    		    	
	    </div>
	</div>
  </form>
</body>
</html>