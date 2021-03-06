<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>狗狗商城</title>
</head>
<body>
	<h1>商品列表</h1>
	
	<div>
	<table>
		<tr>
			<td>名稱</td>
			<td>價格</td>
			<td>數量</td>
			<td>圖片</td>
			<td>商品描述</td>
		</tr>
		<c:forEach var="product" items="${allProducts}">
		<tr>
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td>${product.quantity}</td>
			<td><img src="showImage?pnum=${product.id}" width="300" height="300"></td>
			<td>${product.description}</td>
		</tr>
		</c:forEach>
	</table>
	</div>
<a href="/test-main">首頁</a>
</body>
</html>