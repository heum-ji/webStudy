<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeView</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header2.jsp" />
	<div class="container">
		<!-- 공지사항 출력 -->
		<fieldset>
			<legend>공지사항 JSTL</legend>
			<table class="table" id="noticeView" style="width: 100%;">
				<tr class="table-info">
					<th colspan="4">${n.noticeTitle}</th>
				</tr>
				<tr class="table-light">
					<th>작성자</th>
					<th>${n.noticeWriter}</th>
					<th>작성일</th>
					<th>${n.noticeDate}</th>
				</tr>
				<tr class="table-light">
					<th>첨부파일</th>
					<th colspan="3"><c:if test="${not empty n.filename }">
							<img src="/img/file.png" width="16px">
							<a href="/fileDown?noticeNo=${n.noticeNo}">${n.filename}</a>
						</c:if></th>
				</tr>
				<tr class="table-light">
					<th>내용</th>
					<th colspan="3">${n.noticeContentBr}</th>
				</tr>
				<tr class="table-light">
					<th colspan="4" style="text-align: center;">
						<button class="btn btn-info" onclick="history.go(-1);">이전화면</button>
						<c:if
							test="${not empty sessionScope.m && sessionScope.m.memberId.equals(n.noticeWriter) }">
							<a class="btn btn-info"
								href="/noticeUpdateFrm?noticeNo=${n.noticeNo}">수정하기</a>
							<a class="btn btn-info"
								href="/noticeDelete?noticeNo=${n.noticeNo}">삭제하기</a>
						</c:if>
					</th>
				</tr>
			</table>
		</fieldset>
		<c:if test="${not empty sessionScope.m }">
			<div class="inputCommentBox">
				<form action="/insertComment" method="post">
					<ul>
						<li><i class="far fa-user fa-5x"></i></li>
						<li><input type="hidden" name="ncLevel" value="1"> <input
							type="hidden" name="ncWriter" value="${sessionScope.m.memberId}">
							<input type="hidden" name="noticeRef" value="${n.noticeNo}">
							<input type="hidden" name="ncRef" value="0"> <textarea
								class="form-control" name="ncContent"></textarea></li>
						<li>
							<button type="submit" class="btn btn-primary btn-lg btn-block">등록</button>
						</li>
					</ul>
				</form>
			</div>
		</c:if>

		<!-- 댓글 출력 -->
		<div>
			<c:forEach items="${list }" var="nc">
				<c:if test="${nc.ncLevel == 1 }">
					<ul class="comments">
						<li><i class="far fa-user fa-3x"></i>
							<p>${nc.ncWriter}</p>
							<p>${nc.ncDate}</p></li>
						<li>
							<p>${nc.ncContentBr}</p> <textarea name="ncContent"
								class="form-control" style="display: none;">${nc.ncContent}</textarea>

							<!-- 수정/삭제/답글달기 -->
							<p class="commentsBtn">
								<c:if test="${not empty sessionScope.m }">
									<c:if test="${sessionScope.m.memberId.equals(nc.ncWriter) }">
										<a href="javascript:void(0)"
											onclick="modifyComment(this,'${nc.ncNo}','${n.noticeNo}');">수정</a>
										<a href="javascript:void(0)"
											onclick="deleteComment(this,'${nc.ncNo}','${n.noticeNo}');">삭제</a>
									</c:if>
									<!-- 댓글 작성자와 로그인 회원이 일치하는 경우 -->
									<a href="javascript:void(0)" class="recShow">답글달기</a>
								</c:if>
								<!-- 로그인 되어 있는 경우 -->
							</p> <!-- 대댓글 입력창 발생 --> <c:if test="${not empty sessionScope.m }">
								<form action="/insertComment" class="recoment">
									<input type="hidden" name="ncLevel" value="2"> <input
										type="hidden" name="ncWriter"
										value="${ sessionScope.m.memberId}"> <input
										type="hidden" name="noticeRef" value="${ n.noticeNo}">
									<input type="hidden" name="ncRef" value="${ nc.ncNo}">
									<textarea class="form-control" name="ncContent"></textarea>
									<div>
										<button type="submit" class="btn btn-outline-primary">등록</button>
										<button type="button"
											class="btn btn-outline-primary recCancel">취소</button>
									</div>
								</form>
							</c:if>
						</li>
					</ul>
					<c:forEach items="${list }" var="ncc">
						<!-- 대댓글이고, 참조가 댓글  원래 댓글을 가리키고 있는 경우-->
						<c:if test="${ncc.ncLevel == 2 && ncc.ncRef == nc.ncNo }">
							<ul class="recomments">
								<li><i class="fas fa-angle-double-right fa-3x"></i></li>
								<li><i class="far fa-user fa-3x"></i>
									<p>${ncc.ncWriter}</p>
									<p>${ncc.ncDate}</p></li>
								<li>
									<p>${ncc.ncContentBr}</p> <textarea name="ncContent"
										class="form-control" style="display: none;">${ncc.ncContent}</textarea>
									<p class="commentsBtn">
										<c:if
											test="${not empty sessionScope.m && sessionScope.m.memberId.equals(ncc.ncWriter) }">
											<a href="javascript:void(0)"
												onclick="modifyComment(this,'${ncc.ncNo}','${n.noticeNo}');">수정</a>
											<a href="javascript:void(0)"
												onclick="deleteComment(this,'${ncc.ncNo}','${n.noticeNo}');">삭제</a>
										</c:if>
									</p>
								</li>
							</ul>
						</c:if>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</div>

	<script>
		// 답글 달기 클릭 시
		$(".recShow").on("click", function() {
			var idx = $(".recShow").index(this); // 클릭한게 몇번째 답글 달기 버튼인지 확인
			$(this).hide();
			$(".recoment").eq(idx).css("display", "flex");
		});

		$(".recCancel").on("click", function() {
			var idx = $(".recCancel").index(this); // 클릭한게 몇번째 취소 버튼인지 확인
			$(".recoment").hide(); // 답글 달기 숨기기
			$(".recShow").eq(idx).show(); // 수정/삭제/답글 달기 보이기
		});

		// 댓글 수정 클릭
		function modifyComment(obj, ncNo, noticeNo) {
			// textarea를 보여줌
			$(obj).parent().prev().show();
			// 기존 본문 내용을 숨김
			$(obj).parent().prev().prev().hide();
			// 수정 -> 수정완료
			$(obj).html('수정 완료');
			$(obj).attr("onclick",
					"modifyComplete(this, '" + ncNo + "', '" + noticeNo + "')");

			// 삭제 -> 수정 취소
			$(obj).next().html("수정 취소");
			$(obj).next().attr("onclick",
					"modifyCancel(this, '" + ncNo + "', '" + noticeNo + "')");

			// 답글 달기 버튼 삭제
			$(obj).next().next().hide();
		}

		// 댓글 수정 취소
		function modifyCancel(obj, ncNo, noticeNo) {
			// text area 숨기기
			$(obj).parent().prev().hide();
			// 기존 본문 내용 다시 보여줌
			$(obj).parent().prev().prev().show();
			// 수정완료 -> 수정
			$(obj).prev().html("수정");
			$(obj).prev().attr("onclick",
					"modifyComment(this, '" + ncNo + "', '" + noticeNo + "')");
			// 수정 취소 -> 삭제
			$(obj).html("삭제");
			$(obj).attr("onclick",
					"deleteComment(this, '" + ncNo + "', '" + noticeNo + "')");

			// 답글 달기 버튼 보이기
			$(obj).next().show();
		}

		// 댓글 수정 완료
		function modifyComplete(obj, ncNo, noticeNo) {
			var form = $("<form action='/noticeCommentupdate' method='post'></form>");
			form.append($("<input type='text' name='ncNo' value='"+ncNo+"'>"));
			form
					.append($("<input type='text' name='noticeNo' value='" + noticeNo + "'>"));
			form.append($(obj).parent().prev()); // textarea 추가
			$("body").append(form);
			form.submit();
		}

		// 댓글 삭제 완료
		function deleteComment(obj, ncNo, noticeNo) {
			if (confirm("댓글을 삭제하시겠습니까?")) {
				location.href = "/noticeCommentDelete?ncNo=" + ncNo
						+ "&noticeNo=" + noticeNo;
			}
		}
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>