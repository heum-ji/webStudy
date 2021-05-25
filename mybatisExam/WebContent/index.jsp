<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- JSTL Core 태그 -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>Player</h1>
	<hr>
	<c:choose>
		<%-- 로그인이 되지 않은 경우 --%>
		<c:when test="${empty sessionScope.p }">
			<form action="/login" method="post">
				<fieldset>
					<legend>로그인</legend>
						아이디 : <input type="text" name="playerId"><br>
						비밀번호 : <input type="password" name="playerPw"><br>
					  	<input type="submit" value="로그인">
					  	<input type="reset" value="초기화"><br>
					  	<a href="/joinFrm">회원가입</a>
					  	<a href="/searchFrm">아이디/비밀번호찾기</a>
				</fieldset>
			</form>
		</c:when>

		<c:otherwise>
			<h3>[ ${ sessionScope.p.nickname } ]님 환영합니다.</h3>
			<a href="/selectAllPlayer">전체회원조회</a><br>
			<a href="/mypage?playerId=${sessionScope.p.playerId }">마이페이지</a><br>
			<a href="/logout">로그아웃</a><br>
			<a href="/delete?playerId=${sessionScope.p.playerId }">회원 탈퇴</a>
		</c:otherwise>
	</c:choose>
</body>
</html>