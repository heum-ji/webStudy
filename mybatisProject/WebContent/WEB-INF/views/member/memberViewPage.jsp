<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- JSTL Core 태그 -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memberViewPage</title>
</head>
<body>
	<h1>전체 회원 조회</h1>
	<table border="1">
		<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>주소</th>
		<th>성별</th>
		</tr>
		<c:forEach items="${list }" var="m">
			<tr>
				<td>${m.memberId }</td>
				<td>${m.memberName }</td>
				<td>${m.phone }</td>
				<td>${m.address }</td>
				<td>${m.gender }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>