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
							<c:forEach items="${new_list}" var="item">
								<tr>
									<td>${item.id}</td>
									<td><a href = <c:url value = "/seller-store_home/update-orders?order_id=${item.id}"></c:url>>${item.name}</a></td>
									<td><fmt:formatNumber value = "${item.total_price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</td>
									<td>${item.address}</td>
									<td>${item.city}</td>
									<td><span class="badge badge-primary" style="padding: 3px; font-size: 13px;">${item.status}</span></td>
									<td>${item.phone}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
            
		
        </div>
    </div>
</div>     
</body>
</html>