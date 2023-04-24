<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Them nhan vien</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="seller-store-add-staff" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm mới nhân viên</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input name="phone" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input name="address" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Images</label>
                            <input type="file" name="images">
                        </div>
                    </div>                   
                    <div class="form-group">
                        <label>Store Shop</label>
                        <select class="browser-default custom-select" name = "seller_id">
                        	<c:forEach items = "${seller}" var = "item">
                            	<option value="${item.sellerid}">${item.sellername}</option>
                            </c:forEach>                         
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/seller-store-staff-list?sellerid=${Sellerid}"></c:url> ><input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Cread">
                </div>
            </form>
        </div>
    </div>
</body>
</html>