<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>狗狗商城</title>
</head>
<body>
	<nav class="navbar  sticky-top navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="homePageImg/pawprint.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
                <span style="color: black;" onmouseover="turnOnlight(this)" onmouseout="turnOfflight(this)">狗狗商城</span>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="輸入商品名稱或關鍵字..." aria-label="Search"
                        size="70">
                    <button class="btn btn-outline-success" type="submit"><img src="homePageImg/magnifying-glass.png"
                            width="25" height="25"></button>
                </form>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item" style="margin-left: auto;">
                    </li>
                </ul>

                <a class="nav-link active " aria-current="page" href="#" style="color: black;"
                    onmouseover="turnOnlight(this)" onmouseout="turnOfflight(this)"><img src="homePageImg/shopping-cart.png"
                        width="25" height="25"></a>
                <a class="nav-link" href="#" style="color: black;" onmouseover="turnOnlight(this)"
                    onmouseout="turnOfflight(this)">登入</a>

                <a class="nav-link" href="#" style="color: black;" onmouseover="turnOnlight(this)"
                    onmouseout="turnOfflight(this)">會員中心</a>
            </div>
        </div>
    </nav>



    <!--      body div      -->
    <div class="container-xl">
        <nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/test-main">首頁</a></li>
                <li class="breadcrumb-item active" aria-current="page">${product.name}</li>
            </ol>
        </nav>

        <div class="card mb-3" >
            <div class="row g-0">
              <div class="col-md-4">
                <img src="showImage?pnum=${product.id}" class="img-fluid rounded-start w-100" >
              </div>
              <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text"><small class="text-muted">123123</small></p>
                    <p class="card-text">賣家: 商城老闆</p>
                    <p class="card-text">價格: <strong style="color: red;">$ ${product.price}</strong></p>
                    <p class="card-text">
                        <span class="input-group col-xs-2">
                            <span style="margin-right: 10px;">數量: </span>
                            <button class="btn btn-sm btn-outline-secondary" type="button" id="button-sub">-</button>
                            <input type="text" class="form-control form-control-sm text-center" placeholder="" aria-label="Example text with button addon" aria-describedby="button-addon1" maxlength="4" style="max-width: 80px;" value="1" id="buy-amount">
                            <button class="btn btn-sm btn-outline-secondary" type="button" id="button-add">+</button>
                            <small class="text-muted" style="padding-left: 10px;">剩餘數量: 123</small>
                        </span>
                        <script>
                            $("#button-add").click(function(){
                               let amount = $("#buy-amount").val();
                               if (amount < 9999){
                                   amount++;
                               }
                               $("#buy-amount").val(amount);
                            });
                    
                            $("#button-sub").click(function(){
                                let amount = $("#buy-amount").val();
                                if (amount > 1) {
                                    amount--;
                                }
                                $("#buy-amount").val(amount);
                            });
                        </script>
    
                    </p>
    
                    <p class="card-text">
                        <button type="button" class="btn btn-outline-danger">加入購物車</button>
                        <button type="button" class="btn btn-danger">直接結帳</button>
                    </p>
                </div>
              </div>
            </div>
        </div>

        <div class="card text-dark bg-light mb-3" >
          <div class="card-header">商品內容</div>
          <div class="card-body">
            <p class="card-text">${product.description}</p>
          </div>
      
          </div>
    </div><!-- /.body div -->
    











    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top"
        style="background-color: #e3f2fd;">
        <p class="col-md-4 mb-0 text-muted">&copy; 2021 狗狗商城 版權所有</p>

        <a href="/test-main"
            class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
			<img src="homePageImg/pawprint.png" alt="" width="30" height="24" class="d-inline-block align-text-top">
        </a>

        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">首頁</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">聯絡客服</a></li>
            <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">關於我們</a></li>
        </ul>
    </footer>
</body>
</html>