<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String username=(String)session.getAttribute("username");
    if(username!=null){
    	response.getWriter().println("已登入 "+username);
    	
    }else{
    	response.getWriter().println("未登入");
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="login">
<h3>登入成功!!</h3>
<br>

<a href="index.html">返回首頁</a>


</div>
</body>
</html>