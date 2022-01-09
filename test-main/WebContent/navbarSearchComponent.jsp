<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
/* 目前失靈 
@
include 
        media-breakpoint-down(sm) { .carousel-inner { height:600px;
	
}

}

*/
.carousel-inner {
	height: 550px;
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
<nav class="navbar sticky-top navbar-expand-lg navbar-light"
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
			
			<!-- Search Component -->
				<form class="d-flex" method="GET" action="search">
					<input class="form-control me-2" type="search"
						placeholder="輸入商品名稱或關鍵字..." aria-label="Search" size="70" name="keywords">
					<button class="btn btn-outline-success" type="submit">
						<img src="homePageImg/magnifying-glass.png" width="25" height="25">
					</button>
				</form>
			</div>

			<a class="nav-link active " aria-current="page"
				href="shoppingCartServlet" style="color: black;" id="cartImg"> <img
				src="/test-main/homePageImg/shopping-cart.png" width="25"
				height="25"></a> 
				
				<a class="nav-link py-0 nologin-1" href="#"
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
				
				// scan cart items
				var cartlist = '${sessionScope.shoppingcart}';
				if (cartlist != ""
						&& '${sessionScope.shoppingcart.totalAmount}' != 0) {
					const changeCartImg = "<a class='nav-link active btn' aria-current='page'"
                        	+ "href='shoppingCartServlet' >"
							+ "<img src='/test-main/homePageImg/shopping-cart1.png' width='25'  height='25'>"
							+ "<sup class='badge bg-danger float-end'>${sessionScope.productNumInCart}</sup></a>";
					$("#cartImg").replaceWith(changeCartImg);
				}
			</script>
		</div>
	</nav>
</body>
</html>