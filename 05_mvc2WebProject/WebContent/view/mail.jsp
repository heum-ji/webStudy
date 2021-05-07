<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mail</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<h3>EMAIL 보내기</h3>
		<input type="text" id="email" class="short form-control" placeholder="email주소입력">
		<button id="sendMail" class="btn btn-success">인증코드 보내기</button>
		<div id="auth">
			<input type="text" id="authCode" class="short form-control" placeholder="인증코드입력">
			<button class="btn btn-primary" id=authBtn>인증하기</button>
			<span id="timeZone"></span>
			<span id="authMsg"></span>
		</div>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>