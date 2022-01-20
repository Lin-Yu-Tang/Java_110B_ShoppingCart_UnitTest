<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="author" content="Ke-Meng-Yen">
<link rel="stylesheet" href="./css/style.css">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
</head>
<body>
<div>
<form action="loginServlet" class="login" method="post">
	 
	 <h1>登入</h1>
	 <i class="fa fa-user-circle-o"></i>	
	<h2>帳號</h2>
	<input type="text" name="username"  />
	<h2>密碼</h2>
	<input type="password" name="password" />
   <br> 
    <td>自動登入</td>
    <td><input type="checkbox" name="autologin" value="auto" /></td>
</tr>
<br>
<br>
<tr>
    <td colspan="2"><input type="submit" value="登入" /></td>
<td><a href="register.jsp" style="color:azure">註冊</td>
            

	</form>
	</div>
</body>
</html>