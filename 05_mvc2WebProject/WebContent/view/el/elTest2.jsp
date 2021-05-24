<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>elTest2</title>
  </head>

  <body>
    <h1>회원정보 출력</h1>
    <hr>
    <h2>이름 : ${m.memberName}</h2>
    <h2>전화번호 : ${m.phone}</h2>
    <h2>주소 : ${m.address}</h2>
    <h2>가입일 : ${m.enrollDate}</h2>
  </body>

  </html>