<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstlTest2</title>
</head>

<body>
	<c:if test="${ m != null }">
		<h1>[${m.memberName }]</h1>
	</c:if>
	<%-- <c:if test="${m == null }"> --%>
	<c:if test="${ empty m }">
		<h1>회원을 조회할 수 없습니다.</h1>
	</c:if>
</body>

</html>