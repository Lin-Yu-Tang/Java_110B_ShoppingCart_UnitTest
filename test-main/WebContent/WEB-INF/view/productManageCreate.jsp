<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid" id="content-management">
		<h1>新增商品</h1>
		<div>
			<form class="form" name="product-create" method="POST"
				action="seller/create" enctype="multipart/form-data">

				<div class="row g-3 align-items-center mb-2">
					<div class="col-1">
						<label for="new-product-name" class="col-form-label">商品名稱:
						</label>
					</div>
					<div class="col-auto">
						<input type="text" id="new-product-name" class="form-control"
							name="name">
					</div>
				</div>

				<div class="row g-3 align-items-center mb-2">
					<div class="col-1">
						<label for="new-product-price" class="col-form-label">價格:
						</label>
					</div>
					<div class="col-auto">
						<input type="text" id="new-product-price" class="form-control"
							name="price">
					</div>
				</div>

				<div class="row g-3 align-items-center mb-2">
					<div class="col-1">
						<label for="new-product-quantity" class="col-form-label">數量:
						</label>
					</div>
					<div class="col-auto">
						<input type="text" id="new-product-quantity" class="form-control"
							name="quantity">
					</div>
				</div>

				<div class="row g-3 align-items-center mb-2">
					<div class="col-1">
						<label for="newProductImgInput" class="form-label">更新圖片: </label>
					</div>
					<div class="col-auto">
						<input class="form-control" type="file" name="picture"
							id="newProductImgInput">
					</div>
					<div class="col-auto">
						<p id="new-product-img"></p>
					</div>
				</div>

				<div class="row g-3 align-items-center mb-2">
					<div class="col-1">
						<label for="new-product-desc">描述: </label>
					</div>
					<div class="col-5">
						<textarea class="form-control" id="new-product-desc" name="desc"
							style="height: 100px"></textarea>
					</div>
				</div>

				<div class="btn-group" role="group">
					<button name="processForm" type="submit" class="btn btn-success">送出修改</button>
					<button name="cancel" id="cancel" type="button"
						class="btn btn-outline-secondary"
						onclick="homeClick()">取消</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>