<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache, no-store">
<meta http-equiv="expires" content="0">
<title>狗狗商城 - 賣家中心</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Bootstrap core CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<style>
.product-name-url {
	text-decoration: none;
	color: black;
}

.product-name-url:hover {
	color: crimson;
}

.table-border {
	border: 2px solid rgb(171, 190, 202);
}

.order-div {
	background-color: rgba(133, 255, 174, 0.1);
}
</style>
<script>
	// 驗證session是否存在

	$(document).ready(function() {
		let session = '${sessionScope.sellername}';
		if (session == "") {
			location.replace("/test-main/sellerLogin");
		}
	});
</script>
</head>
<body>
	<div class="container-fluid" id="content-management">
	<h1 class="mb-4" id="title">訂單總覽</h1>
        <div class="row fw-bolder border-bottom bg-info bg-opacity-10">
            <div class="col">訂單編號</div>
            <div class="col">運送地址</div>
            <div class="col">下單日期</div>
            <div class="col">狀態</div>
        </div>
		<c:forEach var="orders" items="${sellerOrders}">
		<div class="row border border-2 order-div" data-bs-toggle="collapse" data-bs-target="#collapse-${orders.id}"
        aria-expanded="false" id="title">
            <div class="col">${orders.id}</div>
            <div class="col">${orders.shippingAddress}</div>
            <div class="col">${orders.createTime}</div>
            <div class="col">${orders.status}</div>
        </div>
        	<div class="table-responsive row">
            <table class="table table-hover collapse table-border" id="collapse-${orders.id}">
                <thead>
                    <tr>
                        <th>編號</th>
                        <th>商品名稱</th>
                        <th>單價</th>
                        <th>數量</th>
                    </tr>
                </thead>
                <tbody>
        	<c:forEach var="orderItems" items="${orders.items}">
                    <tr>
                        <td>${orderItems.id}</td>
                        <td><a class="product-name-url" href="#">${orderItems.productId}</a></td>
                        <td>${orderItems.price}</td>
                        <td>${orderItems.quantity}</td>
                    </tr>
        	</c:forEach>
                </tbody>
            </table>
        	</div>
        
		</c:forEach>
		</div>
</body>
</html>
