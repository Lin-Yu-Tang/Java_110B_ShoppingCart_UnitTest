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
	                document.getElementById("show").innerHTML="<h3>"+str+"<h3>";
	            }
	        }
	    }
	    xmlHTTP.send();
	}
</script>

</head>
<body>

	<div>
		<form action="UserServlet" class="register" method="post"
			onSubmit="return isValid(this);">
			<h1>註冊</h1>
			<p  class="fa fa-user-circle-o"></p>
			<br>	
			
			<h3 >帳號</h3>
			<h3 id="show"></h3>
			<input id="username" type="text" name="username" onblur="check()">
			<br>	
			
			<h3>密碼</h3>
			<input type="password" name="password" placeholder="" />
			<br>	
			<h3>信箱</h3>
			<input type="text" name="email" placeholder="" />
			<br>	
			<h3>手機號碼</h3>
			<input type="text" name="phone_number"
						placeholder="" />
						<br>	
			<h3>地址</h3>
			<input type="text" name="address" />
			<br>	
			<tr>
				<td colspan="2"><input type="submit" value="註冊" /></td>
				<td><a href="login.jsp " style="color: azure;">返回</td>
			</tr>

			


		</form>


	</div>

	
	
</body>
</html>