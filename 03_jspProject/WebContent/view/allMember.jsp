<%@page import="kr.or.iei.member.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.or.iei.member.dao.MemberDao"%>
<%@page import="common.JdbcTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//1. 인코딩
	request.setCharacterEncoding("utf-8");
	// 2. 값 추출 - 없음
	// 3. 로직 처리
	MemberDao dao = new MemberDao();
	ArrayList<Member> list = dao.selectAllMember();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>allMember</title>
</head>
<body>
	<h2>전체 회원 조회</h2>
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
					for (Member member : list) {
			%><tr>
				<td><%=member.getMemberNo()%></td>
				<td><%=member.getMemberId()%></td>
				<td><%=member.getMemberPw()%></td>
				<td><%=member.getMemberName()%></td>
				<td><%=member.getPhone()%></td>
				<td><%=member.getAddress()%></td>
				<td><%=member.getMemberLevel()%></td>
				<td><%=member.getEnrollDate()%></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
</body>
</html>