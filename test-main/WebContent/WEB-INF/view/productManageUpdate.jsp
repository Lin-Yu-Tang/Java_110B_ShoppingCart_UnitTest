<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid" id="content-management">
	<div>
        <div class="text-center pb-3 mb-3 border-bottom">
        <!-- header -->
            <h1>商品更新頁面</h1>
            <span class="fs-5">標題</span>

            <button class="btn">搜尋</button>
        </div>
        <div>
            <!-- content - product fields-->
		<c:if test="${theProduct != null }">
			<form method="POST" action="#" enctype="multipart/form-data" >
			<p>商品名稱: <input type="text" name="name" value="${theProduct.name}"></p>
			<p>價格: <input type="text" name="price" value="${theProduct.price}"></p>
			<p>數量: <input type="text" name="quantity" value="${theProduct.quantity}"></p>
			<p><img src="showImage?pnum=${theProduct.id}" width="300" height="300"></p>
			<p>照片: <input type="file" name="picture"></p>
			<p>描述: <textarea name="desc" rows="4" cols="100">${theProduct.description}</textarea></p>
			<input type="hidden" name="id" value="${theProduct.id}">
		
			<input type="submit" value="送出修改">
			<input id="cancel" type="button" value="取消">
			<button id="deleteAct" type="button">刪除</button>
			</form>
		
		</c:if>
        </div>

    </div>
</div>
</body>
</html>
