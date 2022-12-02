<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Users</title>
</head>
<body>
	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-5">
							<h2>
								User <b>Management</b>
							</h2>
						</div>
						<div class="col-sm-7">
							<a href=<c:url value = "/admin-user/create"></c:url> class="btn btn-secondary"><i
								class="material-icons">&#xE147;</i> <span>Add New User</span></a>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Role</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="item">
							<tr>
								<td>${item.userid}</td>
								<td><c:url value="/image?fname=category/${item.images!=null?item.images:'uploads/abc.jpg'}" var="imgUrl"></c:url>
								<img class="avatar" width="50px" height="50px" src="${imgUrl}"><a href= "#">${item.fullname}</a></td>
								<td>${item.phone}</td>
								<td><c:if test="${item.roleid == 1}">

									<span class="label label-sm label-success"> Admin </span>

								</c:if> <c:if test="${item.roleid ==2}">

									<span class="label label-sm label-warning"> Seller </span>

								</c:if><c:if test="${item.roleid ==3}">

									<span class="label label-sm label-warning"> Customer </span>

								</c:if>
								</td>
								<td> <c:if test="${item.status == true }">
									<span class="status text-success">&bull;</span> Hoạt động
								</c:if> <c:if test="${item.status == false }">
									<span class="status text-danger">&bull;</span> Cấm
								</c:if>
								</td>
								<td><a href=<c:url value = "/admin-user/update?uid=${item.userid}"></c:url> class="settings" title="Update" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
									<a href=<c:url value = "/admin-user/delete?uid=${item.userid}"></c:url> class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="clearfix">
					<div class="hint-text">
						Showing <b>6</b> out of <b>${count}</b> entries
					</div>
					<ul class="pagination">
					<c:if test="${index == 0}">
						<li class="page-item disabled"><a href=<c:url value = "/admin-user?index=${index-1}"></c:url>>Previous</a></li>
						<c:forEach begin = "0" end = "${sizepage-1}" var = "item">
						<li class="page-item"><a href=<c:url value = "/admin-user?index=${item}"></c:url> class="page-link">${item}</a></li>
						</c:forEach>
						<li class="page-item"><a href=<c:url value = "/admin-user?index=${index+1}"></c:url> class="page-link">Next</a></li>
					</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>