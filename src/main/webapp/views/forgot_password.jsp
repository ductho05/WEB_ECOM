<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
	<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-50">
				<div class="col-10 col-md-4 col-lg-4 col-xl-5 h-100">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">
							<div class="mb-md-5 mt-md-4 pb-5">
								<form action="quen-mat-khau" method="post">
									<c:if test="${not empty message }">
									<div class="alert alert-${alert}" role="alert">
  										${message}
									</div>
									</c:if>
									<h2 class="fw-bold mb-2 text-uppercase" >Quên mật khẩu ?</h2>		
		
									<div class="form-outline form-white mb-4" >
										<input type = "email" id="username" class="form-control form-control-lg" style="margin-top: 50px;" placeholder="Email" name = "email"/>
									</div>
									
									<div class="form-outline form-white mb-4" >
										<input  type="password" id="typePasswordX" class="form-control form-control-lg"  placeholder="Mật khẩu mới" name = "password1"/>
									</div>
									<div class="form-outline form-white mb-4">
										<input type="password" id="typePasswordX" class="form-control form-control-lg" placeholder="Nhập lại mật khẩu" name = "password2"/> 
									</div>
									<input type = "hidden" value = "forgot_password" name = "action">
									<button class="btn btn-outline-light btn-lg px-5" style="margin-top: 20px;" type="submit">Đổi mật khẩu</button>
								</form>							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>