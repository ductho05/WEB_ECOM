<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Orders</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="update-orders" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Update Product</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>ID</label>
                        <input name="id" type="text" class="form-control " value="${order.id}">
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" value="${order.name}" disabled>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <div class="select">
						  <select class="form-control selcls form_statistical_item" name="status" id="" style="width: 213px;">
						  <option value="" selected disabled hidden>${order.status}</option>
						  <option value="Đang chờ xác nhận">Đang chờ xác nhận</option>
						  <option value="Đã xác nhận">Đã xác nhận</option>
						  <option value="Đang giao hàng">Đang giao hàng</option>
						  <option value="Đã hoàn thành">Đã hoàn thành</option>
						    
						  </select>
				 </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#"><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Update">
                </div>
            </form>
        </div>
    </div>
</body>
</html>