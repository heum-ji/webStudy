<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Member member = (Member) request.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/updateMember1" method="post">
			<legend>내정보</legend>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myId">아이디</label> <input
						type="text" id="myId" name="myId" class="form-control"
						value="<%=member.getMemberId()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myPw">비밀번호</label> <input
						type="password" id="myPw" name="myPw" class="form-control"
						value="<%=member.getMemberPw()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myName">이름</label> <input
						type="text" id="myName" name="myName" class="form-control"
						value="<%=member.getMemberName()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myPhone">전화번호</label> <input
						type="text" id="myPhone" name="myPhone" class="form-control"
						value="<%=member.getPhone()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myAddr">주소</label> <input
						type="text" id="myAddr" name="myAddr" class="form-control"
						value="<%=member.getAddress()%>">
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset>
					<label class="control-label" for="myEnrollDate">가입일</label> <input
						type="text" id="myEnrollDate" name="myEnrollDate"
						class="form-control" value="<%=member.getEnrollDate()%>" readonly>
				</fieldset>
			</div>
			<div class="form-group">
				<fieldset style="text-align: center">
					<button type="submit" class="btn btn-outline-primary">정보수정</button>
					<%
						if (member.getMemberLevel() == 1) {
					%>
					<a href="/adminPage" class="btn btn-outline-danger">회원관리</a>
					<%
						} else {
					%>
					<a href="/deleteMember?memberNo=<%=member.getMemberNo()%>"
						class="btn btn-outline-danger">회원탈퇴</a>
					<%
						}
					%>

				</fieldset>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>