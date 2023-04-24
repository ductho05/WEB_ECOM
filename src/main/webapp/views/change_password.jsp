<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thay mat khau</title>
</head>
<body>
<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-10 col-md-4 col-lg-4 col-xl-5 h-100">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body p-5">
							<div class="mb-md-5 mt-md-4 pb-5">
								<form action="change_password" method="post" enctype="multipart/form-data">
            <c:if test="${not empty message}">
	            <div class="alert alert-${alert}" role="alert" style="margin-top: 10px;">
	  					${message}				
				</div>
			</c:if>
                <div class="modal-header">           
                    <h4 class="modal-title text-center">Thay mật khẩu mới</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Email</label>
                        <input name="email" type="email" class="form-control" placeholder="Nhập Email" required>
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu cũ</label>
                        <input name="password" type="password" class="form-control" placeholder="Nhập mật khẩu cũ" required>
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu mới</label>
                        <input name="newpassword" type="password" class="form-control" placeholder="Nhập mật khẩu mới" required>
                    </div>
                    <div class="form-group">
                        <label>Nhập lại mật khẩu</label>
                        <input name="newpassword2" type="password" class="form-control" placeholder="Nhập lại mật khẩu mới" required>
                    </div>                    
                </div>
                <div class="modal-footer">
                    <input type="submit" class="btn btn-success" value="Change" style="margin-right: 120px;">
                </div>
            </form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>