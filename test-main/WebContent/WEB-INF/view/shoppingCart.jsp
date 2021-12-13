<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
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
<style>


.testdiv {
	border: 5px outset red;
}

.testdiv2 {
	border: 3px dashed greenyellow;
}

.prices {
	color: red;
}

.productImg {
	height: 300px;
	width: 300px;
}

.carousel-inner {
	height: 550px;
}
/* Custom button CSS */
.btn-custom {
	color: black;
	background-color: white;
	border-color: white;
}
.btn-custom:hover {
	color: black;
	background-color: white;
	border-color: white;
}
.btn-custom:focus,
.btn-custom.focus {
	box-shadow: 0 0 0 0rem rgba(0, 0, 0, 0)
}

.btn-warning {
	color: white;
}
.btn-warning:hover {
	color: white;
}
.btn-primary.disabled,
.btn-primary:disabled {
	color: white;
    }
.btn-warning:not(:disabled):not(.disabled):active,
.btn-warning:not(:disabled):not(.disabled).active,
.show>.btn-warning.dropdown-toggle {
        color: white;
    }


</style>
<script>
	// 文字提示效果
	function turnOnlight(x) {
		x.style = "color: white;";
	}
	function turnOfflight(x) {
		x.style = "color: black;";
	}
</script>
</head>
<body>
	<nav class="navbar  sticky-top navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="/test-main"> <img
				src="homePageImg/pawprint.png" width="30" height="24"
				class="d-inline-block align-text-top"> <span
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">狗狗商城</span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<form class="d-flex">
					<input class="form-control me-2" type="search"
						placeholder="輸入商品名稱或關鍵字..." aria-label="Search" size="70">
					<button class="btn btn-outline-success" type="submit">
						<img src="homePageImg/magnifying-glass.png" width="25" height="25">
					</button>
				</form>
			</div>

			<a class="nav-link active " aria-current="page"
				href="shoppingCartServlet" style="color: black;" id="cartImg"> <img
				src="/test-main/homePageImg/shopping-cart.png" width="25"
				height="25"></a> <a class="nav-link py-0 nologin-1" href="#"
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">註冊</a> <span class="p-0 nologin">
				| </span> <a class="nav-link py-0 nologin" href="loginServlet?login=true"
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">登入</a> <a class="nav-link" href="#"
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">賣家中心</a>

			<script>
				let loginTag = "<span class='dropdown'>"
						+ "<a class='nav-link py-0 dropdown-toggle' data-bs-toggle='dropdown'"
						+ "href='#' style='color: black;' "
						+ "onmouseover='turnOnlight(this)' onmouseout='turnOfflight(this)'>"
						+ "<img src='/test-main/homePageImg/cat0.png' width='15'"
           			+"height='15'> ${sessionScope.username}</a>"
						+ "<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>"
						+ "<li><a class='dropdown-item' href='#'>會員中心</a></li>"
						+ "<li><a class='dropdown-item' href='loginServlet?login=false'>登出</a></li>";

				let userid = '${sessionScope.username}';
				if (userid != '') {
					$(".nologin-1").replaceWith(loginTag);
					$(".nologin").remove();
				}

				var cartlist = '${sessionScope.shoppingcart}';
				if (cartlist != ""
						&& '${sessionScope.shoppingcart.totalAmount}' != 0) {
					const changeCartImg = "<a class='nav-link active' aria-current='page'"
                        	+ "href='shoppingCartServlet' >"
							+ "<img src='/test-main/homePageImg/shopping-cart1.png' width='25'  height='25'>"
							+ "<sup class='badge bg-danger float-end'>${sessionScope.productNumInCart}</sup></a>";
					$("#cartImg").replaceWith(changeCartImg);

				}
			</script>
		</div>
	</nav>

	<!--      body div      -->
	<div class="container-xl">
	
	<!-- Breadcrumb -->
	<nav class="pt-3" style=" --bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/test-main">首頁</a></li>
                <li class="breadcrumb-item active" aria-current="page">購物車</li>
            </ol>
    </nav>
    
    <!-- Cart items -->
    <div class="card mb-3">
		<table class="table align-middle">
			<tr class="fw-bold text-black-50">
				<td>商品名稱</td>
				<td>圖片</td>
				<td>單價</td>
				<td>數量</td>
				<td>小計</td>
				<td>功能</td>
			</tr>
			<c:forEach var="product" items="${shoppingcart.products}">
				<tr>
					<td><button class="btn btn-custom" onclick="document.location='showProductInfo?pnum=${product.id}'">${product.name}</button></td>
					<td><a href="showProductInfo?pnum=${product.id}">
					<img src="showImage?pnum=${product.id}" width="300"
						height="300"></a></td>
					<td class="prices"><fmt:setLocale value = "en_US"/><fmt:formatNumber value = "${product.price}" type = "currency" minFractionDigits="0"/></td>
					<td>${product.quantity}</td>
					<td class="prices"><fmt:setLocale value = "en_US"/><fmt:formatNumber value = "${product.price * product.quantity}" type = "currency" minFractionDigits="0"/></td>
					<td><button class="btn btn-outline-danger"
						onclick="document.location='shoppingCartServlet?pid=${product.id}&action=remove'">刪除此商品</button></td>
				</tr>
			</c:forEach>
		</table>
		</div><!-- /.Cart items -->

		<div class="card mb-3 pt-3">
		<div class="fs-4 px-3">
		<p class="float-end">
			總金額: <span class="prices p-0">
			<fmt:setLocale value = "en_US"/>
			<fmt:formatNumber value = "${shoppingcart.totalAmount}" 
				type = "currency" minFractionDigits="0"/></span>
			<button type="button" class="btn btn-warning">　　　去結帳　　　</button>
		</p>
		</div>
    	</div>

	</div><!-- /.body div -->

	<footer
		class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"
		style="background-color: #e3f2fd;">
		<p class="col-md-4 mb-0 text-muted">&copy; 2021 狗狗商城 版權所有</p>

		<a href="/test-main"
			class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
			<img src="homePageImg/pawprint.png" width="30" height="24"
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
</body>
</html>