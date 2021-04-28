<%@page import="kr.or.iei.member.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. 인코딩
	request.setCharacterEncoding("utf-8");
	// 2. 값 추출
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	// 3. 로직 처리
	ArrayList<Member> list = new MemberDao().selectAllMember();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>allMember</title>
</head>
<body>
	<h1>전체 회원 정보</h1>
	<hr>
	<%@include file="/view/header.jsp"%>
	<table border="1">
		<thead>
			<tr>
				<th>MEMBER_NO</th>
				<th>MEMBER_ID</th>
				<th>MEMBER_PW</th>
				<th>MEMBER_NAME</th>
				<th>PHONE</th>
				<th>ADDRESS</th>
				<th>MEMBER_LEVEL</th>
				<th>ENROLL_DATE</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (!list.isEmpty()) {
					for (Member m : list) {
			%><tr>
				<td><%=m.getMemberNo()%></td>
				<td><%=m.getMemberId()%></td>
				<td><%=m.getMemberPw()%></td>
				<td><%=m.getMemberName()%></td>
				<td><%=m.getPhone()%></td>
				<td><%=m.getAddress()%></td>
				<td><%=m.getMemberLevel()%></td>
				<td><%=m.getEnrollDate()%></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
</body>
</html>