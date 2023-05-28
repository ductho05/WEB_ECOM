<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhap ma OTP</title>
</head>
<body>
    <div class="container" style = "padding: 200px">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <h2>Xác nhận mã OTP</h2>
                <form action="verifyotp" method="post">
                <c:if test="${not empty message }">
					<div class="alert alert-${alert}" role="alert">
								${message}
					</div>
				</c:if>
                    <div class="form-group">
                        <label for="otp">Nhập mã OTP:</label>
                        <input type="text" class="form-control" id="otp" name="otp" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Xác nhận</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>