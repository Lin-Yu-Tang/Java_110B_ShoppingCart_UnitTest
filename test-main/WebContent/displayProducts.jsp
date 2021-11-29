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
			<td>圖片</td>
			<td>商品描述</td>
			<td>修改</td>
			<td>刪除</td>
		</tr>
		<c:forEach var="product" items="${allProducts}">
		<tr>
			<td>${product.name}</td>
			<td>${product.price}</td>
			<td><img src="showImage?pnum=${product.id}" width="300" height="500"></td>
			<td>${product.description}</td>
			<td><a href="editProductServlet?pnum=${product.id}" >修改</a></td>
			<td><button id="deleteAct" type="button">刪除</button></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<script>
	// 刪除功能(未完成)
	{
	$(".deleteAct").click(function() {
		let act = confirm("您要刪除此商品嗎?");
		const xhttp = new XMLHttpRequest();
		if (act == true) {
			alert("此商品已完成刪除");
			//xhttp.open("GET", "deleteProductServlet?pnum="+pid);
			//xhttp.send();
			location.replace("listAllProductServlet");
		}
	});
	}
	</script>
</body>
</html>