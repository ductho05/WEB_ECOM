<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sach don vi van chuyen</title>
</head>
<body>
    <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-5">
                            <h2>Shipper Management</h2>
                        </div>
                        <div class="col-sm-7">
                            <a href=<c:url value = "/admin-shipper/insert"></c:url> class="btn btn-secondary"><i class="material-icons">&#xE147;</i> <span>Add New Shipper</span></a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Images</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items = "${list_shipper}" var = "item">
                        <tr>
                            <td>${item.id}</td>
                            <td><a href="#">${item.name}</a></td>
                            <td>
                            	<c:url value = "/image?fname=shipper/${item.images}" var = "imgUrl"></c:url>
                            	<img src="${imgUrl}" width="50px;" height="50px;" alt="images">
                            </td>
                            <td>
                            	<c:if test="${item.status == true}">
                            		<span class="status text-success">&bull;</span> Cấp phép
                            	</c:if>
                            	<c:if test="${item.status == false}">
                            		<span class="status text-danger">&bull;</span> Cấm
                            	</c:if>
                            </td>
                            <td>
                                <a href=<c:url value = "/admin-shipper/update?id=${item.id}"></c:url> class="settings" title="Update" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                <a href=<c:url value = "/admin-shipper/delete?id=${item.id}"></c:url> class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item"><a href=<c:url value = "/admin-shipper?index=${index-1}"></c:url>>Previous</a></li>
                        <c:forEach begin = "0" end = "${sizepage-1}" var = "item">
                        	<li class="page-item"><a href=<c:url value = "/admin-shipper?index=${item}"></c:url> class="page-link">${item}</a></li>
                        </c:forEach>
                        <li class="page-item"><a href=<c:url value = "/admin-shipper?index=${index+1}"></c:url> class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>