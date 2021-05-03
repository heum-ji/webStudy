<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Notice n = (Notice) request.getAttribute("n");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeView</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<fieldset>
			<legend>공지사항</legend>
			<table class="table" id="noticeView" style="width: 100%;">
				<tr class="table-info">
					<th colspan="4"><%=n.getNoticeTitle()%></th>
				</tr>
				<tr class="table-light">
					<th>작성자</th>
					<th><%=n.getNoticeWriter()%></th>
					<th>작성일</th>
					<th><%=n.getNoticeDate()%></th>
				</tr>
				<tr class="table-light">
					<th>첨부파일</th>
					<th colspan="3">
						<%
							if (n.getFilename() != null) {
						%> <img src="/img/file.png" width="16px"><%=n.getFilename()%>
						<%
							}
						%>
					</th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<th colspan="3"><%=n.getNoticeContentBr()%></th>
				</tr>
			</table>
		</fieldset>
	</div>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>