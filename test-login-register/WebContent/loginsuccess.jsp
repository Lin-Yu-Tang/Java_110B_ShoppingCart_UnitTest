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
<div>
<h2>登入成功!!</h2>

<!-- 超連結標籤不需要用到form標籤，如果你只是為了做超連結讓使用者點選
	registersuccess.jsp一樣，你要自己改
 -->
<a href="index.html">返回首頁</a>


</div>
</body>
</html>