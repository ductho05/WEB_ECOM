<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Category</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="store-update" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Chỉnh sửa sản phẩm</h4>
                </div>
                	<div class="modal-body">
                	<div class="form-group">
                        <label>ID</label>
                        <input name="productId" type="text" class="form-control" required value = "${product.productId}">
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input name="productName" type="text" class="form-control" required value="${product.productName}">
                        <input name="productCode" type="hidden" class="form-control" required value = "${product.productCode}">
                        <input name="status" type="hidden" class="form-control" required value = "${product.status}">
                        <input name="sellerId" type="hidden" class="form-control" required value = "${Sellerid}">
                        <input name="createDate" type="hidden" class="form-control" required value = "${product.createDate}">
                        <input name="amount" type="hidden" class="form-control" required value = "${product.amount}">
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                       <textarea name="description" class="form-control"  required >${product.description}</textarea>
                    </div> 
                    
                     <div class="form-group">
                        <label>Price</label>
                        <input name="price" type="text" class="form-control" required value = "${product.price}">
                    </div>                   
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Images</label><br>
                            <c:url value="/image?fname=product/${product.images}" var="imgUrl"></c:url> <img width="50px" height="50px"
								src="${imgUrl}"> <br>
                            <input type="file" name="images" style="margin-top: 10px;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Category</label><br>
                        <select class="browser-default custom-select" name = "categoryId">                   	
                        	<c:forEach items = "${list_category}" var = "item">
                            	<option value="${item.categoryId}">${item.categoryname}</option>                           	
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/seller-store_home/store-product?index=0&sellerid=${Sellerid}&filter=all"></c:url> ><input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Update">
                </div>
            </form>
        </div>
    </div>
</body>
</html>