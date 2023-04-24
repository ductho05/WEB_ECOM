<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sach nhan vien</title>
</head>
<body>
	<div class="container-lg">
	<c:if test="${empty list}">
		<h2 style="text-align: center;">Danh sách nhân viên trống !</h2>
	</c:if>
	<c:if test="${not empty list}">			
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8"><h2>Danh sách nhân viên</h2></div>
                    <div class="col-sm-4">
                        <a href = <c:url value = "/seller-store-add-staff"></c:url>><button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Thêm nhân viên mới</button></a>
                    </div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                    	<th>ID</th>
                        <th>Name</th>
                        <th>Images</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items = "${list}" var ="item">
                    <tr>
                    	<td>${item.id}</td>
                        <td><a href = "#">${item.name}</a></td>
                        <td>
                        	<c:url value = "/image?fname=staff/${item.images}" var = "imgUrl"></c:url>
                        	<img alt="images" src="${imgUrl}" width="50" height="50">
						</td>
                        <td>${item.phone}</td>
                        <td>${item.address}</td>
                        <td>
                           
                            <a href = <c:url value = "/seller-store-update-staff?staffid=${item.id}"></c:url> class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                            <a href = <c:url value = "/seller-store-delete-staff?staffid=${item.id}&sellerid=${Sellerid}"></c:url> class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                        </td>
                    </tr>
                </c:forEach>                     
                </tbody>
            </table>
        </div>
    </div>
    </c:if>
</div>
</body>
</html>