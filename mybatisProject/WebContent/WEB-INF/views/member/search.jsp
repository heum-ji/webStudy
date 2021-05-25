<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search</title>
</head>
<body>
	<h1>아이디/비밀번호 찾기</h1>
	<hr>
	<form action="/idSearch">
		<fieldset>
			<legend>아이디 찾기</legend>
			이름 입력 : <input type="text" name="memberName"><br>
			전화번호 입력 : <input type="text" name="phone"><br>
			<input type="submit" value="아이디찾기">
		</fieldset>
	</form>
	
	<form action="/pwSearch">
		<fieldset>
			<legend>비밀번호 찾기</legend>
			아이디 입력 : <input type="text" name="memberId"><br>
			전화번호 입력 : <input type="text" name="phone"><br>
			<input type="submit" value="비밀번호찾기">
		</fieldset>
	</form>
</body>
</html>