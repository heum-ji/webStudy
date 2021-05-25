<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mypage</title>
</head>
<body>
	<h1>마이페이지</h1>
	<form action="/update" method="post">
		<fieldset>
			아이디 : <input type="text" name="memberId" value="${member.memberId }" readonly><br>
			비밀번호 : <input type="password" name="memberPw" value="${member.memberPw }"><br>
			이름 : <input type="text" name="memberName" value="${member.memberName}"><br>
			전화번호 : <input type="text" name="phone" value="${member.phone}"><br>
			주소 : <input type="text" name="address" value="${member.address}"><br>
			성별 : 
			<c:choose>
				<c:when test='${member.gender.equals("남") }'>
					<label>
					 	<input type="radio" name="gender" value="남" checked>
						남자
					</label>
					<label>
						<input type="radio" name="gender" value="여">
						여자
					</label>
				</c:when>
				
				<c:otherwise>
					<label>
					 	<input type="radio" name="gender" value="남">
						남자
					</label>
					<label>
						<input type="radio" name="gender" value="여" checked>
						여자
					</label>
				</c:otherwise>
			</c:choose>
			<br>
			<input type="submit" value="정보수정">
		</fieldset>
	</form>
</body>
</html>