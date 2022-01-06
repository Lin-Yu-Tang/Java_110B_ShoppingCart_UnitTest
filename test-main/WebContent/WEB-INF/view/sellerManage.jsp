<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/test-main/asset/sidebars.css" rel="stylesheet">

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
	<nav class="navbar sticky-top navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="/test-main/seller"> <img
				src="/test-main/homePageImg/pawprint.png" width="30" height="24"
				class="d-inline-block align-text-top"> <span
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">狗狗商城<em> 賣家中心</em></span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>


			<span class='dropdown'> <a
				class='nav-link py-0 dropdown-toggle' data-bs-toggle='dropdown'
				href='#' style="color: black;" onmouseover='turnOnlight(this)'
				onmouseout='turnOfflight(this)'> <img
					src='/test-main/homePageImg/cat0.png' width='15' height='15'>
					${sessionScope.sellername}
			</a>
				<div class="dropdown-menu">
					<form name='logoutForm' method='POST' action='sellerLogin'>
						<input type='hidden' name='login' value='false'>
						<button class='btn-custom1 dropdown-item'>登出</button>
					</form>
				</div>
			</span>
		</div>
	</nav>
	<script>

	</script>

	<main class="flex-shrink-0 overflow-scroll" style="min-height: 600px;">
		<!-- sidebar -->
		<div class="flex-shrink-0 p-3 bg-white" style="width: 18%;">
			<p class="text-center pb-3 mb-3 border-bottom">
				<span class="fs-5 fw-bolder">操作</span>
			</p>

			<ul class="list-unstyled ps-0">
				<li class="mb-1">
					<button class="btn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#home-collapse"
						aria-expanded="false">基本資料維護</button>
					<div class="collapse" id="home-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a href="#" class="link-dark rounded">基本資料</a></li>
						</ul>
					</div>
				</li>
				<li class="mb-1">
					<button class="btn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#dashboard-collapse"
						aria-expanded="false">訂單管理</button>
					<div class="collapse" id="dashboard-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a type="button" class="link-dark rounded"
								onclick="action(this.id)" id="order">訂單總覽</a></li>
							<li><a href="#" class="link-dark rounded">不成立</a></li>
							<li><a href="#" class="link-dark rounded">退貨/退款</a></li>
						</ul>
					</div>
				</li>
				<li class="mb-1">
					<button class="btn btn-toggle align-items-center rounded collapsed"
						data-bs-toggle="collapse" data-bs-target="#orders-collapse"
						aria-expanded="false">商品管理</button>
					<div class="collapse" id="orders-collapse">
						<ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
							<li><a type="button" class="link-dark rounded"
								onclick="action(this.id)" id="read">我的商品</a></li>
							<li><a type="button" class="link-dark rounded"
								onclick="action(this.id)" id="create">新增商品</a></li>
							<li><a type="button" class="link-dark rounded"
								onclick="action(this.id)" id="update">編輯商品</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>

		<!-- Content -->
		<div class="container-fluid p-5" id="content-management">
			<div class="alert alert-warning alert-dismissible fade show"
				role="alert">
				<strong>注意:</strong> 目前網站建置中，部分功能無法使用
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<h1>歡迎賣家管理首頁</h1>
			<div>seller home page content</div>

		</div>

	</main>





	<footer
		class="footer d-flex flex-wrap justify-content-between align-items-center py-3 mt-auto border-top"
		style="background-color: #e3f2fd;">
		<p class="col-md-4 mb-0 text-muted">&copy; 2021 狗狗商城 版權所有</p>

		<a href="/test-main"
			class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
			<img src="/test-main/homePageImg/pawprint.png" width="30" height="24"
			class="d-inline-block align-text-top">
		</a>

		<ul class="nav col-md-4 justify-content-end">
			<li class="nav-item"><a href="/test-main"
				class="nav-link px-2 text-muted">首頁</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">聯絡客服</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">關於我們</a></li>
		</ul>
	</footer>
	
	
	<script>
		/* 登出trigger */
		var btnLogout = document.querySelector("form[name='logoutForm'] > button");
		btnLogout.addEventListener("click", function() {
		document.querySelector("form[name='logoutForm']").submit();
		}, false);
		
		
		
		/* 商品管理相關連結 */
		function action(theId) {
			var targetHtml = theId;
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content-management").innerHTML = this.responseText;
				}
			};
			xhttp.open("GET", "seller/" + targetHtml, true);
			xhttp.send();
		}

		
		
		/* productManageRead.jsp 的修改button*/
		function editBtn(theId) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					document.getElementById("content-management").innerHTML = this.responseText;
				}
			};
			xhttp.open("GET", "seller/update?PID=" + theId, true);
			xhttp.send();
		}
		
		
		
		/* 商品內容 */
		
		/* 送出更新商品表單 */
		var btnProductUpdate = document.querySelector("form[name='product-update'] > button[name='processForm']");
		btnProductUpdate.addEventListener("click", function() {
			document.querySelector("form[name='product-update']").submit();
		}, false);
		
		/* 商品更新，圖檔上傳立即載入 */
		updateProductImgInput.onchange = evt => {
            const [file] = updateProductImgInput.files;

            $("#fileupload-product-img").html("<img id='updateProductImg' src='#'  width='300px' height='300px'>");
            if (file) {
                updateProductImg.src = URL.createObjectURL(file);
            }
        }
		
		/* 送出新增商品表單 */
		var btnProductCreate = document.querySelector("form[name='product-create'] > button[name='processForm']");
		btnProductCreate.addEventListener("click", function() {
			document.querySelector("form[name='product-create']").submit();
		}, false);
		
		var btnProductCreateCancel = document.querySelector("form[name='product-create'] > button[name='cancel']");
		btnProductCreateCancel.addEventListener("click", function() {
			location.replace("/test-main/seller");
		}, false);
		
		/* 新增商品，圖檔上傳立即載入 */
		newProductImgInput.onchange = evt => {
            const [file] = newProductImgInput.files;

            $("#new-product-img").html("<img id='newProductImg' src='#'  width='300px' height='300px'>");
            if (file) {
            	newProductImg.src = URL.createObjectURL(file);
            }
        }
		
		
		
	</script>
</body>
</html>