<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>elTest4</title>
  </head>

  <body>
    <h1>리스트 출력</h1>
    <hr>
    <h2>첫번째 회원</h2>
    이름 : ${list[0].memberName}<br>
    전화번호 : ${list[0].phone}<br>
    주소 : ${list[0].address} <br>
    <hr>
    <h2>두번째 회원</h2>
    이름 : ${list[1].memberName}<br>
    전화번호 : ${list[1].phone}<br>
    주소 : ${list[1].address} <br>
  </body>

  </html>