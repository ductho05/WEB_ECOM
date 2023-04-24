<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order List</title>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Order Management</h2>
                    </div>
                    <div class="col-sm-7">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Filter
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href=<c:url value = "/admin-order?index=0&filter=tat-ca"></c:url>>Tất cả</a>
                              <a class="dropdown-item" href=<c:url value = "/admin-order?index=0&filter=dang-giao-hang"></c:url> >Đang giao hàng</a>                           
                            </div>
                        </div>
                    </div>
                </div>
            </div>
           <table class="table table-striped table-hover">
						<thead>
							<tr class="style-item">
								<th>ID</th>
								<th>Name</th>
								<th>Total Price</th>
								<th>Address</th>
								<th>City</th>
								<th>Status</th>
								<th>Phone</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="item">
								<tr>
									<td>${item.id}</td>
									<td><a href = <c:url value = "/admin-order-detail?order_id=${item.id}"></c:url>>${item.name}</a></td>
									<td><fmt:formatNumber value = "${item.total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</td>
									<td>${item.address}</td>
									<td>${item.city}</td>
									<td><span class="badge badge-primary" style="padding: 3px; font-size: 13px;">${item.status}</span></td>
									<td>${item.phone}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
            <div class="clearfix">
				<div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries </div>
						<ul class="pagination">
							<li class="page-item"><a
								href=<c:url value = "/admin-order?index=${index-1}&filter=${flag}"></c:url>>Previous</a></li>
							<c:forEach begin="0" end="${sizepage-1}" var="item">
								<li class="page-item"><a
									href=<c:url value = "/admin-order?index=${item}&filter=${flag}"></c:url>
									class="page-link">${item}</a></li>
							</c:forEach>
							<li class="page-item"><a
								href=<c:url value = "/admin-order?index=${index+1}&filter=${flag}"></c:url>
								class="page-link">Next</a></li>
						</ul>
		</div>
		
        </div>
    </div>
</div>     
</body>
</html>