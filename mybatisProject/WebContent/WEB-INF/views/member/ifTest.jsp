<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>allMember</title>
</head>
<body>
	<h1>전체 회원 조회</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<c:if test="${ not empty ckName }">
				<th>이름</th>
			</c:if>
			<c:if test="${ not empty ckPhone }">
				<th>전화번호</th>
			</c:if>
			<c:if test="${ not empty ckAddress }">
				<th>주소</th>
			</c:if>
		</tr>
		<c:forEach items="${list }" var="m" varStatus="i">
			<tr>
				<td>${i.count }</td>
				<td>${m.memberId }</td>
				<c:if test="${not empty ckName }">
					<td>${m.memberName }</td>
				</c:if>
				<c:if test="${not empty ckPhone }">
					<td>${m.phone }</td>
				</c:if>
				<c:if test="${not empty ckAddress }">
					<td>${m.address }</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>