<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1>編輯員工資料表</h1>
	
	<form method="POST" action="updateProductServlet" enctype="multipart/form-data" >
		<p>商品名稱: <input type="text" name="name" value="${product.name}"></p>
		<p>價格: <input type="text" name="price" value="${product.price}"></p>
		<p><img src="showImage?pnum=${product.id}" width="300" height="500"></p>
		<p>照片: <input type="file" name="picture"></p>
		<p>描述: <textarea name="desc" rows="4" cols="100">${product.description}</textarea></p>
		<input type="hidden" name="id" value="${product.id}">
		
		<input type="submit" value="送出修改">
		<input id="cancel" type="button" value="取消">
		<button id="deleteAct" type="button">刪除</button>
	</form>
	<script>
	// 使用者取消修改，返回至上一頁
	$("#cancel").click(function(){
		if (confirm("你的編輯尚未儲存，是否要離開此次編輯?")){
			history.go(-1);
		}
	});
	
	// 抓取使用者返回上一頁時候，給予提示視窗
	
	
	
	// 刪除功能
	$("#deleteAct").click(function() {
		let pid = ${product.id};
		let act = confirm("您要刪除此商品嗎?");
		const xhttp = new XMLHttpRequest();
		if (act == true) {
			alert("此商品已完成刪除");
			xhttp.open("GET", "deleteProductServlet?pnum="+pid);
			xhttp.send();
			location.replace("listAllProductServlet");
		}
	});
	
	
	</script>
</body>
</html>