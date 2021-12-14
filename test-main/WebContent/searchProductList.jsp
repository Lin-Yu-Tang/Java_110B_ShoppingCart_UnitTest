<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
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