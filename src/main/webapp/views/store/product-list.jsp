<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-5">
                        <h2>Product Management</h2>
                        <a href=<c:url value = "/seller-store_home/store-add?sellerid=${Sellerid}"></c:url> class="btn btn-secondary" style="margin-right: 200px;margin-top: 20px;"><i
								class="material-icons">&#xE147;</i><span>Thêm sản phẩm mới</span></a>
                    </div>
                    <div class="col-sm-5">
							
						</div>
                    <div class="col-sm-2">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              Filter
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                              <a class="dropdown-item" href=<c:url value = "/seller-store_home/store-product?index=0&sellerid=${Sellerid}&filter=all"></c:url>>Tất cả</a>
                              <a class="dropdown-item" href=<c:url value = "/seller-store_home/store-product?index=0&sellerid=${Sellerid}&filter=active"></c:url> >Cấp phép</a>
                              <a class="dropdown-item" href=<c:url value = "/seller-store_home/store-product?index=0&sellerid=${Sellerid}&filter=ban"></c:url>>Cấm</a>
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
                        <th>Price</th>
                        <th>Amount</th>
                        <th>Image</th>
                        <th>Status</th>
                        <th>Action</th>
                                       
                    </tr>
                </thead>
                <tbody>
                <c:forEach items = "${list}" var = "item">
                    <tr>
                        <td>${item.productId}</td>
                        <td><a href="#">${item.productName}</a></td>                  
                        <td><fmt:formatNumber value = "${item.price}" minFractionDigits="0"></fmt:formatNumber> VNĐ</td>                        
                        <td>${item.amount}</td>
                        <td><c:url value="/image?fname=product/${item.images!=null?item.images:'uploads/abc.jpg'}" var="imgUrl"></c:url>
                        <img width="50px" height="50px" src="${imgUrl}">
                        </td>
                        <td>
                        	<c:if test="${item.status == true }">
                        		<span class="status text-success">&bull;</span> Cấp phép
                        	</c:if>
                        	<c:if test="${item.status == false }">
                        		<span class="status text-danger">&bull;</span> Cấm
                        	</c:if>
                        </td>
                        <td>
                            <a href=<c:url value = "/seller-store_home/store-update?pid=${item.productId}"></c:url> class="settings" title="Update" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                            <a href=<c:url value = "/seller-store_home/store-delete-product?pid=${item.productId}"></c:url> class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>                      
                        </td>
                    </tr>
                </c:forEach> 
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Showing <b>6</b> out of <b>${count}</b> entries</div>
                <ul class="pagination">
                    <li class="page-item"><a href=<c:url value = "/seller-store_home/store-product?index=${index-1}&sellerid=${Sellerid}&filter=all"></c:url> >Previous</a></li>
                    <c:forEach begin = "0" end = "${sizepage-1}" var = "item">
                    	<li class="page-item"><a href=<c:url value = "/seller-store_home/store-product?index=${item}&sellerid=${Sellerid}&filter=all"></c:url> class="page-link">${item}</a></li>
                    </c:forEach>
                    <li class="page-item"><a href=<c:url value = "/seller-store_home/store-product?index=${index+1}&sellerid=${Sellerid}&filter=all"></c:url> class="page-link">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>     
</body>
</html>