<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>狗狗商城</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
    <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>

</head>
<body style="background-color: #e3f2fd;">
    <nav class="navbar  sticky-top navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="/test-main"> <img
				src="/test-main/homePageImg/pawprint.png" width="30" height="24"
				class="d-inline-block align-text-top"> <span
				style="color: black;" onmouseover="turnOnlight(this)"
				onmouseout="turnOfflight(this)">狗狗商城<em> 賣家中心</em></span>
			</a>

	</nav>

    <!-- content -->
    <div class="row">
        <div class="col-4 mb-0 ps-4 p-5" style="background-color: rgb(166, 111, 218); height: 600px;">
            <h1 class="h3 mb-3 fw-bolder text-center">登入賣家中心</h1>
            <form method="POST" action="sellerLoginServlet">
                <div class="mb-3">
                    <label for="floatingInput" style="color: cornsilk;">帳號</label>
                    <input type="text" class="form-control" id="floatingInput" name="sellerAccount" placeholder="請輸入帳號">
                </div>
                <div class="mb-3">
                    <label for="floatingPassword" style="color: cornsilk;">密碼</label>
                    <input type="password" class="form-control" id="floatingPassword" name="passwd" placeholder="請輸入密碼">
                    <input type="hidden" name="login" value="true">
                </div>
                <div class="checkbox mb-3">
                    <label style="color: cornsilk;">
                    <input type="checkbox" value="remember-me">自動登入
                    </label>
                </div>
                <div class="mb-3">
                	 <button class="w-100 btn btn-lg btn-primary" type="submit">登 入</button>
                </div>

            </form>
        </div>
        <div class="col-8 mb-0" style="background-color: rgb(247, 211, 157); height: 600px;">
        </div>
    </div>


    <footer
		class="footer d-flex flex-wrap justify-content-between align-items-center py-3 my-0 border-top"
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
</body>
</html>