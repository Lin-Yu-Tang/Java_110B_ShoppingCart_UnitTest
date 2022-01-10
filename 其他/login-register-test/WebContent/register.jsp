<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<form action="UserServlet" method="post">
<h2>註冊</h2>

<table>
				<tr>
					<td>帳號:</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<br>
				<tr>
					<td>密碼:</td>
					<td><input type="password" name="password" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="註冊" /></td>
					<td><a href="login.jsp">返回</td>
				</tr>
			</table>


</form>


</div>
</body>
</html>