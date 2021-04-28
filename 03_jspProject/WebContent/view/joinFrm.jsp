<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/view/join.jsp" method="post">
		<fieldset>
			<legend>회원가입</legend>
			아이디 : <input type="text" name="memberId"><br> 비밀번호 : <input
				type="password" name="memberPw"><br> 이름 : <input
				type="text" name="memberName"><br> 폰 : <input
				type="text" name="phone"  placeholder ="ex) 01012341234"><br>
			주소 : <input type="text" name="address"><br> <br> <input
				type="submit" value="회원가입"> <input type="reset" value="취소">
		</fieldset>
	</form>
</body>
</html>