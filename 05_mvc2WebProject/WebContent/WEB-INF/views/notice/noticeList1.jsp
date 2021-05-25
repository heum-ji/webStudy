<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeList1</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />
	<div class="container">
		<fieldset>
			<legend>공지사항 JSTL</legend>
			<!-- 로그인하고, 등급이 관리자인 경우 -->
			<c:if
				test="${not empty sessionScope.m && sessionScope.m.memberLevel == 1}">
				<div>
					<a class="btn btn-info writeBtn" href="noticeWriteFrm">글쓰기</a>
				</div>
			</c:if>

			<table class="table-hover" style="width: 100%;">
				<tr class="table-primary">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
				<c:forEach items="${list }" var="n">
					<tr class="table-light">
						<td>${n.rnum}</td>
						<td style="text-align: left;"><a
							href="/noticeView?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
						<td>${n.noticeWriter}</td>
						<td>${ n.noticeDate}</td>
					</tr>
				</c:forEach>
			</table>
			<div id="pageNavi">${pageNavi }</div>
		</fieldset>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>