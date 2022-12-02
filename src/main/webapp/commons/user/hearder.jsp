<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<c:if test="${not empty Users }">
	<a href = "#">Chào mừng ${Users.fullname} đến với trang web! </a>
	<a href = <c:url value = "/home?action=logout"></c:url>>Đăng xuất</a>
	</c:if>
	<c:if test="${empty Users}">
	<a href = <c:url value = "/dang-nhap?action=login"></c:url>>Đăng nhập</a>
	<a href = <c:url value = "/dang-ky?action=register"></c:url>>Đăng ký</a>
	</c:if>
</body>
</html>