<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>join</title>
</head>
<body>
	<h1>회원가입</h1>
	<form action="/join" method="post">
		<fieldset>
			아이디 : <input type="text" name="memberId"><br>
			비밀번호 : <input type="password" name="memberPw"><br>
			이름 : <input type="text" name="memberName"><br>
			전화번호 : <input type="text" name="phone" placeholder="01012345678"><br>
			주소 : <input type="text" name="address"><br>
			 성별: 
			 	<label>
					<input type="radio" name="gender" value="남"> 남자
				</label>
				<label>
					<input type="radio" name="gender" value="여"> 여자
				</label>
				<br>
				<input type="submit" value="회원가입">
				<input type="reset" value="초기화">	
		</fieldset>
	</form>
</body>
</html>