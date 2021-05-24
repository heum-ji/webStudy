<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>

	<body>
		<h2>${requestScope.num}</h2>
		<h2>${requestScope.str}</h2>
		<h2>${requestScope.str1}</h2>
		<h2>${sessionScope.num}</h2>
		<h2>${sessionScope.str}</h2>
		<h2>${sessionScope.str2}</h2>
		<hr>
		<h2>${num }</h2>
		<h2>${str }</h2>
		<h2>${str1 }</h2>
		<h2>${str2 }</h2>
	</body>

	</html>