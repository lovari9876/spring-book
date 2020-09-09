<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<title>nullPointer 에러</title>

<style>
h1 {
	text-align: center;
}

p {
	text-align: center;
}

div {
	text-align: center;
}

#container {
	margin: 0 auto;
}

table {
	margin-left: auto;
	margin-right: auto;
}

th {
	background-color: #cddc3994; /* hex code of lime green */
}
</style>

</head>


<body>

	<div id="container">
	
		<!-- 타이틀 시작 -->
		<table width="100%" border="1" cellpadding="0" cellpadding="0">
			<tr>
				<th align="center">기본 에러 화면입니다.</th>
			</tr>
		</table>
		<br>
		
		<!-- 에러 메시지 -->
		<table width="100%" border="1" cellpadding="0" cellpadding="0" align="center">
			<tr>
				<td align="center">
				<br><br><br><br><br>
				Message: ${exception.message }
				<br><br><br><br><br>
				</td>
			</tr>
		</table>
		
	</div>
	












</body>