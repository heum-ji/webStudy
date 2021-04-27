<%@page import="kr.or.iei.member.vo.Member"%>
<%@page import="kr.or.iei.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. 인코딩
	request.setCharacterEncoding("utf-8");
	// 2. 값추출
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	String memberName = request.getParameter("memberName");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");

	// insert용 member 세팅
	Member member = new Member();

	member.setMemberId(memberId);
	member.setMemberPw(memberPw);
	member.setMemberName(memberName);
	member.setPhone(phone);
	member.setAddress(address);

	//3. 로직처리
	MemberDao dao = new MemberDao();
	int result = dao.insertMember(member);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입 결과</h1>
	<hr>
	<%
		if (result > 0) {
	%>
	<h2>회원가입성공</h2>
	<h2>신규 [<%=member.getMemberName() %>]님 환영합니다.</h2>
	<%
		} else {
	%>
	<h2>회원가입 실패</h2>
	<%
		}
	%>
</body>
</html>