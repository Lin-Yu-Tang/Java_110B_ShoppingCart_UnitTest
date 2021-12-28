<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="loginServlet" method="post">
	 
	 <h2>登入</h2>
	<table>
            <tr>
                <td>帳號:</td>
                <td><input type="text" name="username"  /></td>
            </tr>
            <br>
            <tr>
                <td>密碼:</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>自動登入</td>
                <td><input type="checkbox" name="autologin" value="auto" /></td>
            </tr>
            
            
            <tr>
                <td colspan="2"><input type="submit" value="登入" /></td>
			<td><a href="register.jsp">註冊</td>
		</tr>
		</table>

	</form>
</body>
</html>