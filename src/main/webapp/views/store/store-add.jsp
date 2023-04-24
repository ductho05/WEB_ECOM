<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Category</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="store-add" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm mới sản phẩm</h4>
                </div>
                	<div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="productName" type="text" class="form-control" required>                                                                    
                    </div>
                    
                    <div class="form-group">
                        <label>Description</label>
                       <textarea name="description" class="form-control"  required ></textarea>
                    </div> 
                    
                     <div class="form-group">
                        <label>Price</label>
                        <input name="price" type="text" class="form-control" required>
                    </div>                   
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Images</label>
                            <input type="file" name="images">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Category</label>
                        <select class="browser-default custom-select" name = "categoryId">
                        	<c:forEach items = "${list_category}" var = "item">
                            	<option value="${item.categoryId}">${item.categoryname}</option>                           	
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/admin-category"></c:url> ><input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Cread">
                </div>
            </form>
        </div>
    </div>
</body>
</html>