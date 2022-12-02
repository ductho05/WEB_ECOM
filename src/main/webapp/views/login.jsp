<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<section class="vh-100 gradient-custom">
		<div class="container py-5 h-100">
			<div class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-10 col-md-4 col-lg-4 col-xl-5 h-100">
					<div class="card bg-dark text-white" style="border-radius: 1rem;">
						<div class="card-body p-5 text-center">
							<div class="mb-md-5 mt-md-4 pb-5">
								<form action="" method="post">
									<c:if test="${not empty message }">
									<div class="alert alert-${alert}" role="alert">
  										${message}
									</div>
									</c:if>
									<h2 class="fw-bold mb-2 text-uppercase" >Đăng Nhập</h2>		
									<div class="form-outline form-white mb-4" style="margin-top: 50px;">
										<input  id="username" class="form-control form-control-lg"  placeholder="Tên đăng nhập" name = "username"/>
									</div>
									<div class="form-outline form-white mb-4">
										<input type="password" id="typePasswordX" class="form-control form-control-lg" placeholder="Mật khẩu" name = "password"/> 
									</div>
									<input type = "hidden" value = "login" name = "action">
									<button class="btn btn-outline-light btn-lg px-5" style="margin-top: 38px;" type="submit">Đăng nhập</button>
								</form>							
							</div>
							<p class="mb-0"><a href=<c:url value = "/quen-mat-khau?action=forgot_password"></c:url> style="margin-top: 20px;" class="text-white-50 fw-bold">Quên mật khẩu?</a></p>
							<p class="mb-0">Chưa có tài khoản?  <a href=<c:url value = "/dang-ky?action=register"></c:url> class="text-white-50 fw-bold">Đăng ký</a></p>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>