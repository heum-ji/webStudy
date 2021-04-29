<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String memberId = (String) request.getAttribute("memberId");
	boolean result = (Boolean) request.getAttribute("result");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<style>
	#checked-container {
		text-align: center;
		padding-top: 50px;'
	}
	#checked {
		color:red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div id="checked-container">
		<!-- 아이디 사용 가능 -->
		<%
			if (result) {
		%>
		[<span><%=memberId%></span>]는 사용이 가능합니다. <br> <br>
		<button onclick="setMemberId('<%=memberId%>')">닫기</button>
		<!-- 아이디 중복 -->
		<%
			} else {
		%>
		[<span id="checked"><%=memberId%></span>]는 이미 사용 중입니다.
		<br><br>
		<form action="/checkId" method="post">
			<input type="text" name="checkId" placeholder="사용할 아이디를 입력하세요"><br>
			<input type="submit" value="중복검사">
		</form>
		<%
			}
		%>
	</div>
	<script>
		function setMemberId(memberId) {
			$("#memberId", opener.document).val(memberId);
			self.close();
		}
	</script>
</body>
</html>