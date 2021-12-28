<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<nav class="navbar  sticky-top navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"> <img
				src="homePageImg/pawprint.png" alt="" width="30" height="24"
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
				<!-- 需要有新增商品後，顯示紅色圈圈提示圖的效果
                參考資料: https://getbootstrap.com/docs/5.1/components/badge/
                -->
                <a class="nav-link active " aria-current="page"
                    href="shoppingCartServlet" style="color: black;"
                    onmouseover="turnOnlight(this)" onmouseout="turnOfflight(this)"><img
                    src="/test-main/homePageImg/shopping-cart.png" width="25"
                    height="25"></a>

                <a class="nav-link py-0 nologin-1" href="#"
                    style="color: black;" onmouseover="turnOnlight(this)"
                    onmouseout="turnOfflight(this)">註冊</a>
                <span class="p-0 nologin"> | </span>
                <a class="nav-link py-0 nologin" href="LoginCookieServlet" style="color: black;"
                    onmouseover="turnOnlight(this)" onmouseout="turnOfflight(this)">登入</a>
                <a class="nav-link" href="#" style="color: black;"
                    onmouseover="turnOnlight(this)" onmouseout="turnOfflight(this)">賣家中心</a>
				
				    <script>
			        let loginTag = "<span class='dropdown'>"
			        +"<a class='nav-link py-0 dropdown-toggle' data-bs-toggle='dropdown'"
			        +"href='#' style='color: black;' "
			        +"onmouseover='turnOnlight(this)' onmouseout='turnOfflight(this)'>"
			        +"<img src='/test-main/homePageImg/cat0.png' width='15'"
           			+"height='15'> ${sessionScope.username}</a>"
           			+"<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>"
           			+"<li><a class='dropdown-item' href='#'>會員中心</a></li>"
           			+"<li><a class='dropdown-item' href='logoutServlet' >登出</a></li>";
           			
					let userid = '${sessionScope.username}';
					if (userid != '') {
	  		            $(".nologin-1").replaceWith(loginTag);
 			            $(".nologin").remove();
					}


    				</script>
				
				
					<!-- 登入後須顯示為使用者圖像，並有下拉式選單 
                參考資料: https://getbootstrap.com/docs/5.1/components/dropdowns/
                -->

			</div>
	</nav>


	<!--      body div      -->
	<div class="container-xl">


		<!--    Carousel area : 圖片顯示框大小需要一致   -->
		<div id="carouselExampleIndicators" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="3" aria-label="Slide 4"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="homePageImg/product/amazon.jpg" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="homePageImg/product/testla.PNG" class="d-block w-100"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="homePageImg/product/Source-@brandwithravi-Twitter.png"
						class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="homePageImg/FDKq5_uakAAJhup.png" class="d-block w-100"
						alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>



		<!-- Three columns of text below the carousel -->
		<!-- img檔案要規定大小-->
		<div class="row testdiv pt-3">
			<c:forEach items="${products}" var="product">

				<div class="col-lg-4">
					<div class="productImg">
						<a href="showProductInfo?pnum=${product.id}"><img
							src="showImage?pnum=${product.id}" class="w-100"></a>
					</div>
					<p>${product.name}</p>
					<h2 class="prices">&dollar;${product.price}</h2>
				</div>
				<!-- /.col-lg-4 -->

			</c:forEach>
		</div>
		<!-- /.row -->
	</div>
	<!-- /.body div -->



	<footer
		class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"
		style="background-color: #e3f2fd;">
		<p class="col-md-4 mb-0 text-muted">&copy; 2021 狗狗商城 版權所有</p>

		<a href="/test-main"
			class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
			<img src="homePageImg/pawprint.png" alt="" width="30" height="24"
			class="d-inline-block align-text-top">
		</a>

		<ul class="nav col-md-4 justify-content-end">
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">首頁</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">聯絡客服</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">關於我們</a></li>
		</ul>
	</footer>
</body>
</html>