<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<title>login</title>

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

	<!-- Element (center) is obsolete. Its use is discouraged in HTML5 documents. -->
	<!-- <center></center> -->
	<div id="container">
		<h1>로그인</h1>
		<hr>
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" value="${user.id }"/></td> <!-- 값이 있으면 입력되어 나온다. -->
					<!-- Command 객체 이름은 클래스 이름의 첫글자를 소문자로 변경하여 자동으로 설정된다. -->
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password" value="${user.password }"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인" />
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>






















</html>