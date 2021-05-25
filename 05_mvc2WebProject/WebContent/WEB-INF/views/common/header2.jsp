
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 태그 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <% Member m=(Member) session.getAttribute("m"); %> --%>

<!-- 폰트어썸(아이콘) -->
<link rel="stylesheet" href="/fontawesome/css/all.css">
<script type="text/javascript" src="/fontawesome/js/all.js"></script>
<!-- 부트스트랩 CSS -->
<link rel="stylesheet" href="/css/bootstrap.css">
<!-- 글꼴적용(NotoSans 폰트) -->
<link rel="stylesheet" href="/css/font.css">
<!-- 기본 CSS -->
<link rel="stylesheet" href="/css/default.css">
<!-- jQuery라이브러리 추가(2개) -->
<script type="text/javascript" src="/js/jquery-3.3.1.js"></script>
<!-- 부트스트랩용 jQuery -->
<script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="/">Heum's World</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="/noticeList?reqPage=1">공지사항</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/boardList?reqPage=1">자유게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="/photoList">사진게시판</a></li>
			<li class="nav-item"><a class="nav-link" href="/view/ajax.jsp">AJAX</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
				role="button" aria-haspopup="true" aria-expanded="false">API</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/view/mail.jsp">메일 API</a> <a
						class="dropdown-item" href="/view/map.jsp">지도 API</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/view/payment.jsp">결제모듈</a> <a
						class="dropdown-item" href="/view/movie.jsp">영화순위 API</a> <a
						class="dropdown-item" href="/view/el/el.jsp">EL</a> <a
						class="dropdown-item" href="/view/jstl/jstl.jsp">JSTL</a>
				</div></li>
		</ul>
		<c:choose>
			<c:when test="${ empty sessionScope.m}">
				<button class="btn btn-secondary my-2 my-sm-0" data-toggle="modal"
					data-target=".modal">로그인</button>
				<a class="btn btn-secondary my-2 my-sm-0" href="/joinFrm">회원가입</a>
			</c:when>
			<c:otherwise>
				<a class="btn btn-secondary my-2 my-sm-0" href="/mypage1">${ sessionScope.m.memberName}</a>
				<a class="btn btn-secondary my-2 my-sm-0" href="/logout">로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>
</nav>

<c:if test="${empty sessionScope.m }">

	<div class="modal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">로그인</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="/login" method="post" name="loginFrm">
					<div class="modal-body">
						<div class="form-group">
							<label for="loginId">아이디</label> <input type="text"
								class="form-control" name="loginId" id="loginId"
								placeholder="아이디 입력">
						</div>
						<div class="form-group">
							<label for="loginPw">비밀번호</label> <input type="password"
								class="form-control" name="loginPw" id="loginPw"
								placeholder="비밀번호 입력">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary">로그인</button>
						<button type="button" class="btn btn-secondary cls"
							data-dismiss="modal" onclick="initInputs();">닫기</button>
					</div>
					<div class="modal-footer">
						<a href="#">아이디/비밀번호찾기</a>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script>
		function initInputs() {
			$("[name=loginFrm] input").val("");
		}
	</script>
</c:if>