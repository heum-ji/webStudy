<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>jstl</title>
  </head>

  <body>
    <h1>JSTL 테스트 페이지</h1>
    <h2> <a href="/view/jstl/jstlTest1.jsp">1. JSTL 기초</a></h2>

    <form action="/jstlTest1">
      <input type="text" name="memberId">
      <input type="submit" value="조회">
    </form>
    <br>

    <form action="/jstlTest2">
      <input type="text" name="memberId">
      <input type="submit" value="조회">
    </form>

    <h2><a href="/jstlTest3">4. JSTL foreach</a></h2>

    <form action="/jstlTest4">
      <input type="text" name="data">
      <input type="submit" value="입력">
    </form>
  </body>

  </html>