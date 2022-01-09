<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid" id="content-management">
	<div>
        <div class="text-center pb-3 mb-3 border-bottom">
        <!-- header -->
            <h1>商品更新頁面</h1>
            
			<!-- 搜尋商品 -->
        	<div class="input-group mb-3 px-5">
            	<input type="text" class="form-control" placeholder="請輸入商品Id">
            	<button type="button" class="btn btn-outline-secondary">搜尋</button>
        	</div>

      	</div>
	</div>
        
            <!-- content - product fields-->
        <div>
		<c:if test="${theProduct != null }">
		<form class="form" name="product-update" method="POST" action="seller/update" enctype="multipart/form-data">
			
			<!-- hidden value -->
			<input type="hidden" name="id" value="${theProduct.id}" >
			
        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
              <label for="product-name" class="col-form-label">商品名稱: </label>
            </div>
            <div class="col-auto">
              <input type="text" id="product-name" class="form-control" name="name" value="${theProduct.name}">
            </div>
        </div>

        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
              <label for="product-price" class="col-form-label">價格: </label>
            </div>
            <div class="col-auto">
              <input type="text" id="product-price" class="form-control" name="price" value="${theProduct.price}">
            </div>
        </div>

        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
              <label for="product-quantity" class="col-form-label">數量: </label>
            </div>
            <div class="col-auto">
              <input type="text" id="product-quantity" class="form-control" name="quantity" value="${theProduct.quantity}">
            </div>
        </div>

        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
                <p>圖片預覽</p>
            </div>
            <div class="col-auto">
                <img src="showImage?pnum=${theProduct.id}" width="300" height="300">
            </div>
        </div>
        	
			
			
			
        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
                <label for="updateProductImgInput" class="form-label">更新圖片: </label>
            </div>
            <div class="col-auto">
                <input class="form-control" type="file" name="picture" id="updateProductImgInput">
            </div>
            <div class="col-auto">
				<p id="fileupload-product-img"></p>            
            </div>
        </div>

        <div class="row g-3 align-items-center mb-2">
            <div class="col-1">
                <label for="product-desc">描述: </label>
            </div>
            <div class="col-5">
                <textarea class="form-control" id="product-desc" name="desc" style="height: 100px">${theProduct.description}</textarea>
            </div>
        </div>

		<div class="btn-group" role="group">
			<button name="processForm" type="submit" class="btn btn-success">送出修改</button>
			<button name="cancel" id="cancel" type="button" class="btn btn-outline-secondary" onclick="homeClick()">取消</button>
			<button name="delete" id="deleteAct" type="button" class="btn btn-outline-danger" onclick="deleteProduct(this.value)"  value="${theProduct.id}">刪除</button>
		</div>
		</form>
		
		</c:if>
        </div>
</div>
</body>
</html>
