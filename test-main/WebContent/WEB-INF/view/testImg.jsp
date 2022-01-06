<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>


</head>
<body>
	
	<img src="/WEB-INF/view/shopping-cart1.png" width="25" height="25">
	
	<h1>Test input</h1>
	<form runat="server">
        <input accept="image/*" type='file' id="updateProductImgInput" >
        <p id="fileupload-product-img"></p>

    </form>
	
	
	
	<script>
	updateProductImgInput.onchange = evt => {
        const [file] = updateProductImgInput.files;

        $("#fileupload-product-img").html("<img id='updateProductImg' src='#'  width='300px' height='300px'>");
        if (file) {
            updateProductImg.src = URL.createObjectURL(file);
        }
    }
	</script>
</body>
</html>