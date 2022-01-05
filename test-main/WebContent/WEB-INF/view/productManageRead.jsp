<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="author" content="Tom Lin">
<meta charset="UTF-8">
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
    .product-img:hover{
        width: 200px;
        height: 200px;
    }
</style>
</head>
<body>
	<div class="container-fluid" id="content-management">
	<h1>商品列表</h1>
	
	<div>
	<table class="table table-hover">
		<thead class="table-light">
		<tr>
			<td>名稱</td>
			<td>價格</td>
			<td>數量</td>
			<td>圖片</td>
			<td>商品描述</td>
			<td>修改</td>
			<td>刪除</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="product" items="${allProducts}">
		<tr>
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td>${product.quantity}</td>
			<td><img class="product-img" src="showImage?pnum=${product.id}" width="150" height="150"></td>
			<td>${product.description}</td>
			<td><button class="btn" type="button" onclick="editBtn(this.value)" value="${product.id}">修改</button></td>
			<td><button class="btn" type="button" onclick="deleteBtn(this.value)" value="${product.id}">刪除</button></td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
</body>
</html>