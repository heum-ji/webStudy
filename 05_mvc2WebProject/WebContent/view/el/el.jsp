<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
  </head>

  <body>
    <h1>EL TEST</h1>
    <h3><a href="/elTest1">1. EL TEST (일반변수)</a></h3>

    <form action="/elTest2">
      회원아이디 입력 : <input type="text" name="memberId">
      <input type="submit" value="조회">
    </form>

    <form action="/elTest3">
      공지사항번호 입력 : <input type="text" name="noticeNo">
      <input type="submit" value="조회">
    </form>

    <h3><a href="/elTest4">4. EL TEST4 (LIST - 배열도 동일)</a></h3>

    <form action="/elTest5">
      회원아이디 입력 : <input type="text" name="memberId">
      <input type="submit" value="조회">
    </form>

    <h3><a href="/elTest6">6. EL 연산자</a></h3>
  </body>

  </html>