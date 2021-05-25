<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!-- JSTL Core 태그 -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>jstlTest1</title>
		</head>

		<body>
			<c:set var="num1" value="100" scope="request" />
			<h1>${num1 }</h1>
			<c:set var="num2" value="200" scope="request" />
			<h1>${num2 }</h1>
			<h1>${num1 + num2 }</h1>
			<c:set var="num1" value="300" scope="session" />
			<h1>${sessionScope.num1 }</h1>
			<c:remove var="num1" scope="request" />
			<h1>${requestScope.num1 }</h1>
			<hr>
			<h1>
				<c:out value="${num2 }" default="없음" />
			</h1>
			<h1>
				<c:out value="${data }" default="없음" />
			</h1>
			<p>
				<c:out value="글씨를 진하게 하려면<b>11</b>를 사용" escapeXml="false" />
			</p>
			<p>
				<c:out value="글씨를 진하게 하려면<b>11</b>를 사용" escapeXml="true" />
			</p>
		</body>

		</html>