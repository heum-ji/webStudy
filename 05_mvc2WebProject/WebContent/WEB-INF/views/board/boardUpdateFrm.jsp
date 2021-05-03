<%@page import="board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Board b = (Board) request.getAttribute("b");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardUpdateFrm</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<form action="/boardUpdate" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>">
			<fieldset>
				<legend>게시물 수정</legend>
				<table class="table" style="width: 100%;">
					<tr class="table-active">
						<th>제목</th>
						<td colspan="3"><input type="text" class="form-control"
							name="boardTitle" value="<%=b.getBoardTitle()%>"></td>
					</tr>
					<tr class="table-active">
						<th>작성자</th>
						<td><%=m.getMemberId()%></td>
						<th>첨부파일</th>
						<td style="text-align: left">
						<input type="hidden" name="status" value="stay">
							<!-- 첨부파일이 있는 경우  -->
							<%
 								if (b.getFilename() != null) {
 							%>
  							<img src="/img/file.png" width="16px" class="delFile">
  							<span class="delFile"><%=b.getFilename()%></span>
							<button type="button" id="delBtn" class="btn btn-primary btn-sm delFile">삭제</button>
							
							<!-- 삭제 버튼 클릭 시 file 첨부 할 수 있게 하기 -->
							<input type="file" name="filename" id="file" style="display: none;">
							<input type="hidden" name="oldFilename" value="<%=b.getFilename()%>">
							<input type="hidden" name="oldFilepath" value="<%=b.getFilepath()%>">
							
							<!-- 첨부파일이 없는 경우 첨부 가능하게 하기 -->
							 <%
 								} else {
							 %>
							<input type="file" name="filename">
						</td>
						<%
							}
						%>
					</tr>
					<tr class="table-active">
						<th>내용</th>
						<td colspan="3"><textarea name="boardContent"
								class="form-control"><%=b.getBoardContent()%></textarea></td>
					</tr>
					<tr class="table-active">
						<th colspan="4">
							<button type="submit" class="btn btn-danger btn-block">수정하기</button>
						</th>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
	<script>
		$("#delBtn").on("click", function() {
			if (confirm("첨부파일을 삭제하시겠습니까?")) {
				$(".delFile").hide();
				$("#file").show();
				$("[name=status]").val("delete");
			}
		});
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>