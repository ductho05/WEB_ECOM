<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tao cua hang</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="insert-seller" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm cửa hàng mới</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="sellername" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Description</label>
                            <textarea name="bio" class="form-control" required></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Images</label>
                            <input type="file" name="images">
                        </div>
                    </div>                   
                    <div class="form-group">
                        <label>Open</label>
                        <select class="browser-default custom-select" name = "isactive">
                            <option value="1">Hoạt động</option>
                            <option value="0">Đóng</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/seller-home"></c:url> ><input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Cread">
                </div>
            </form>
        </div>
    </div>
</body>
</html>