<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chu</title>
</head>
<body>
	<div class="line"></div>
	 <div class="container-xl">
        <div class="table-responsive">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-5">
                            <h2>Quản lý cửa hàng</h2>
                            <a href=<c:url value = "/seller-home/insert-seller"></c:url> class="btn btn-secondary" style="margin-right: 250px;margin-top: 20px;"><i
								class="material-icons">&#xE147;</i> <span>Tạo mới cửa hàng</span></a>
                        </div>
                        <div class="col-sm-5">
							
						</div>
                        <div class="col-sm-2">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Filter
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href=<c:url value = "/seller-home/list-seller?index=0&filter=tat-ca"></c:url>>Tất cả</a>
                              <a class="dropdown-item" href=<c:url value = "/seller-home/list-seller?index=0&filter=cap-phep"></c:url> >Cấp phép</a>
                              <a class="dropdown-item" href=<c:url value = "/seller-home/list-seller?index=0&filter=cam"></c:url>>Cấm</a>
                            </div>
                        </div>
                    </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Images</th>
                            <th>Active</th>
                            <th>Open</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items = "${list}" var = "item">
                        <tr>
                            <td>${item.sellerid}</td>
                            <td><a href="#">${item.sellername}</a></td>
                            <td><c:url value = "/image?fname=seller/${item.images}" var = "imgUrl"></c:url>
                            <img src="${imgUrl}" width=80px; height=80px; alt="images">
                            </td>
                            <td>
                            <c:if test="${item.isactive == true}">
                            	<span class="status text-success">&bull;</span> Cấp phép
                            </c:if>
                            <c:if test="${item.isactive == false}">
                            	<span class="status text-danger">&bull;</span> Cấm
                            </c:if>
                            </td>
                            <td>
                            <c:if test="${item.isopen == true}">
                            	<span class="status text-success">&bull;</span> Mở
                            </c:if>
                            <c:if test="${item.isopen == false}">
                            	<span class="status text-danger">&bull;</span> Đóng
                            </c:if>
                            </td>
                            <td>
                                <a href=<c:url value = "/seller-store_home?sellerid=${item.sellerid}"></c:url> ><button type="button" class="btn btn-success">Đi tới cửa hàng</button></a>                               
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <c:if test="${count != 0}">
                    	<div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href=<c:url value = "admin-seller?index=${index-1}"></c:url>>Previous</a></li>
                        <c:forEach begin = "0" end = "${sizepage-1}" var = "item">                      
                        <li class="page-item"><a href=<c:url value = "admin-seller?index=${item}"></c:url> class="page-link">${item}</a></li>
                        </c:forEach>
                        <li class="page-item"><a href=<c:url value = "admin-seller?index=${index+1}"></c:url> class="page-link">Next</a></li>
                    </ul>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

	
</body>
</html>