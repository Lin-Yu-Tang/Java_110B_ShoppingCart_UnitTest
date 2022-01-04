<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>
	<h1>商品列表</h1>
	
	
	
	
<h1> 測試jquery</h1>
	
	<div id="title">
		hellllllllllllllllo
	</div>
	
	
<script>
$("#title").on("click", function () {
    var color = $( this ).css("background-color");
	
    alert("hello" + color);
    
    /*
    if (color == "rgb(255, 255, 0)") {
        $(this).css("background-color", "white")
    }
    else {
        $(this).css("background-color", "yellow")
    }
    */
});
</script>	
</body>
</html>