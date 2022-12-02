<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Product</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="update" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Update Product</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>ID</label>
                        <input name="productId" type="text" class="form-control" value="${product.productId}">
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input name="productName" type="text" class="form-control" value="${product.productName}" required>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select class="browser-default custom-select" name="status">
                            <option value="1">Hoạt động</option>
                            <option value="0">Cấm</option>
                        </select>
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