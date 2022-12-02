<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="update" method="post" enctype="multipart/form-data">
                <div class="modal-header">
                    <h4 class="modal-title">Update User</h4>
                </div>
                <div class="modal-body">
                	<div class="form-group">
                        <label>ID</label>
                        <input name="userid" type="text" class="form-control" required value = "${user.userid}">
                    </div>
                    <div class="form-group">
                        <label>Name</label>
                        <input name="username" type="text" class="form-control" required value = "${user.username}">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input name="email" type="text" class="form-control" required value = "${user.email}">
                    </div>
                    <div class="form-group">
                        <label>Full Name</label>
                        <input name="fullname" type="text" class="form-control" required value = "${user.fullname}">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input name="password" type="text" class="form-control" required value = "${user.password}">
                    </div>
                    <div class="form-group">
						<div class="form-group mt-3" style="font-weight: bold;">
							<label class="mr-2">Images</label> <br>
							<c:url value="/image?fname=user/${user.images!=null?user.images:'uploads/abc.jpg'}" var="imgUrl"></c:url> <img width="50px" height="50px"
								src="${imgUrl}"> <br>
							<input type="file" name="images" style="margin-top: 15px;">
						</div>
					</div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input name="phone" type="text" class="form-control" required value = "${user.phone}">
                    </div>
                    <div class="form-group">
                        <label>Status</label>
                        <select class="browser-default custom-select" name = "status">
                            <option value="1">Hoạt động</option>
                            <option value="0">Cấm</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Role</label>
                        <select class="browser-default custom-select" name = "roleid">
                            <option value="1">Admin</option>
                            <option value="2">Seller</option>
                            <option value="3">Customer</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href = <c:url value = "/admin-user?index=0"></c:url>><input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel"></a>
                    <input type="submit" class="btn btn-success" value="Update">
                </div>
            </form>
        </div>
    </div>
</body>
</html>