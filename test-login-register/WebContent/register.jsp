<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function isValid(form) {
		var x = /^\w{6,12}$/;
		if (!x.test(form.username.value)) {
			alert("帳號請輸入6~12位英文數字！");
			return false;
		}
		if (!x.test(form.password.value)) {
			alert("密碼請輸入6-30位英文數字！");
			return false;

		}
		return true;
	}

	function check()
	{  
	    var username=document.getElementById("username").value;

	    if(window.ActiveXObject)
	    {
	        xmlHTTP=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	    else if(window.XMLHttpRequest)
	    {
	        xmlHTTP=new XMLHttpRequest();
	    }
	    
	    xmlHTTP.open("get","UserServlet?username="+username,true);
	    
	    xmlHTTP.onreadystatechange=function check_status()
	    {
	        if(xmlHTTP.readyState == 4)
	        {
	            if(xmlHTTP.status == 200)
	            {
	                var str=xmlHTTP.responseText;
	                document.getElementById("show").innerHTML="<h1>"+str+"</h1>";
	            }
	        }
	    }
	    xmlHTTP.send();
	}
</script>

</head>
<body>

	<div>
		<form action="UserServlet" method="post"
			onSubmit="return isValid(this);">
			<h2>註冊</h2>

			<table>
				<tr>
					<td>帳號:</td>
					<td><input id="username" type="text" name="username" onblur="check()"> </td>
				</tr>
				<br>
				<tr>
					<td>密碼:</td>
					<td><input type="password" name="password" placeholder="" /></td>
				</tr>
				<tr>
					<td>信箱:</td>
					<td><input type="text" name="email" placeholder="請輸入信箱" /></td>
				</tr>
				<tr>
					<td>手機號碼:</td>
					<td><input type="text" name="phone_number"
						placeholder="請輸入手機號碼" /></td>
				</tr>
				<tr>
					<td>地址:</td>
					<td><input type="text" name="address" /></td>
				</tr>
				<tr>
					<td>照片:</td>
					<td><input type="file" name="picture" /></td>
				</tr>


				<tr>
					<td colspan="2"><input type="submit" value="註冊" /></td>
					<td><a href="login.jsp">返回</td>
				</tr>
			</table>


		</form>


	</div>

	
	<div id="show"></div>
</body>
</html>