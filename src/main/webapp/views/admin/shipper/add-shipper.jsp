<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Them moi don vi van chuyen</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="insert" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Thêm mới đơn vị vận chuyển</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input name="name" type="text" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <div class="form-group mt-3" style="font-weight: bold;">
                            <label class="mr-2">Images</label>
                            <input type="file" name="images">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select class="browser-default custom-select" name = "status">
                            <option value="1">Cấp phép</option>
                            <option value="0">Cấm</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/admin-shipper?index=0"></c:url> ><input type="button"  class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Cread">
                </div>
            </form>
        </div>
    </div>
</body>
</html>