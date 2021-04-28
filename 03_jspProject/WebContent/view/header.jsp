<%@page import="kr.or.iei.member.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		if (member == null) {
	%>
	<form action="/view/login.jsp" method="post">
		<fieldset>
			<legend>로그인</legend>
			아이디 : <input type="text" name="id"><br> 비밀번호 : <input
				type="password" name="pw"><br> <input type="submit"
				value="로그인"> <input type="reset" value="취소">
		</fieldset>
	</form>
	<%
		} else {
	%>

	<h2>
		[<%=member.getMemberName()%>]님
	</h2>
	<h3>
		<a href="/view/logout.jsp">로그아웃</a>
	</h3>
	<%
		}
	%>
</body>
</html>