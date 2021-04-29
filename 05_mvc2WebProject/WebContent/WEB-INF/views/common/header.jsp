<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Member m = (Member) session.getAttribute("m");
%>

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
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
    aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarColor01">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item"><a class="nav-link" href="#">Menu1</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Menu2</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Menu3</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Menu4</a></li>
      <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button"
          aria-haspopup="true" aria-expanded="false">Menu5</a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="#">sub-1</a>
          <a class="dropdown-item" href="#">sub-2</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">sub-3</a>
          <a class="dropdown-item" href="#">sub-4</a>
        </div>
      </li>
    </ul>
		<%
			if (m == null) {
		%>
		<button class="btn btn-secondary my-2 my-sm-0" data-toggle="modal" data-target=".modal">로그인</button>
    	<a class="btn btn-secondary my-2 my-sm-0" href="/joinFrm">회원가입</a>
		<%
			} else {
		%>
		<a class="btn btn-secondary my-2 my-sm-0" href="/mypage1"><%=m.getMemberName()%></a>
    	<a class="btn btn-secondary my-2 my-sm-0" href="/logout">로그아웃</a>
		<%
			}
		%>
	</div>
</nav>
<%
	if (m == null) {
%>
<div class="modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">로그인</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="/login" method="post" name="loginFrm">
        <div class="modal-body">
          <div class="form-group">
            <label for="loginId">아이디</label>
            <input type="text" class="form-control" name="loginId" id="loginId" placeholder="아이디 입력">
          </div>
          <div class="form-group">
            <label for="loginPw">비밀번호</label>
            <input type="password" class="form-control" name="loginPw" id="loginPw" placeholder="비밀번호 입력">
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary">로그인</button>
          <button type="button" class="btn btn-secondary cls" data-dismiss="modal" onclick="initInputs();">닫기</button>
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

<%
	}
%>