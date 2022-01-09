<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Tom Lin">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>
	${pro.name}
	<img src="showImage?pnum=${pro.id}">
	
	
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