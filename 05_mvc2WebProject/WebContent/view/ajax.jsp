<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ajax</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<div class="container">
		<fieldset>
			<legend>AJAX</legend>
			<div>
				<h3>1. 서버로 데이터 보내기</h3>
				<input type="text" id="ajax1">
				<button class="btn btn-info" onclick="jsAjax();">보내기(JS)</button>
				<button class="btn btn-danger" id="jQ1">보내기(JQuery)</button>
			</div>
			<div>
				<h3>2. 서버에서 기본형 데이터 받기</h3>
				<button class="btn btn-danger" id="jQ2">데이터 받기</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>3. 서버로 기본형 데이터 보내고 기본형 데이터 받기</h3>
				<input type="text" id="firstNum">
				<input type="text" id="secondNum">
				<button class="btn btn-danger" id="jQ3">두 수의 합 받기</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>4. 서버로 객체 데이터 보내기</h3>
				<input type="text" id="name"><br>
				<input type="text" id="age"><br>
				<input type="text" id="addr"><br>
				<button class="btn btn-danger" id="jQ4">객체보내기</button>
			</div>
			<div>
				<h3>5. 서버로 기본형 데이터를 보내고, 결과로 객체 받기</h3>
				<input type="text" id="ajax5">
				<button class="btn btn-danger" id="jQ5">조회</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>6. 서버에서 리스트타입 결과 받기</h3>
				<button class="btn btn-danger" id="jQ6">조회</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>7. 서버에서 맵타입 결과 받기</h3>
				<button class="btn btn-danger" id="jQ7">조회</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>8. 서버에서 객체타입 결과 받기(GSON)</h3>
				<input type="text" id="ajax8">
				<button class="btn btn-danger" id="jQ8">조회</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>9. 서버에서 리스트타입 결과 받기(GSON)</h3>
				<button class="btn btn-danger" id="jQ9">조회</button>
				<p class="ajaxResult"></p>
			</div>
			<div>
				<h3>10. 서버에서 리스트타입 결과 받기(GSON)</h3>
				<button class="btn btn-danger" id="jQ10">조회</button>
				<p class="ajaxResult"></p>
			</div>
		</fieldset>
	</div>
	<script>
		function jsAjax() {
			// 1. XMLHttpRequest 객체 생성
			var xhttp = new XMLHttpRequest();
			var msg = document.getElementById("ajax1").value; // input 입력값
			// 2. 요청 정보를 설정
			xhttp.open("GET", "/ajaxTest1?msg=" + msg, true);
			// 3. 데이터 처리에 따라 동작할 함수 설정
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) { // 정상적으로 처리되었을 때 호출되는 함수
					console.log("서버 전송 성공");
				} else if (this.readyState == 4 && this.status == 404) { // 요청할 페이지가 없는 경우
					console.log("서블릿 없음");
				}
			}
			// 4. 서버에 요청
			xhttp.send();
		}

		$("#jQ1").on("click", function() {
			var msg = $("#ajax1").val();

			$.ajax({
				url : "/ajaxTest1",
				data : {
					msg : msg
				},
				type : "get",
				success : function() {
					console.log("서버 전송 성공")
				},
				error : function() {
					console.log("에러가 났어요 도와주세요!")
				},
				complete : function() {
					console.log("성공/실패 여부와 상관 없이 호출")
				}
			});
		});

		$("#jQ2").on("click", function() {
			var result = $(this).next();

			$.ajax({
				url : "/ajaxTest2",
				type : "get",
				success : function(data) { // 서버가 주는 데이터가 매개변수로 들어옴
					result.html(data);
				},
				error : function() {
					console.log("실패2");
				}
			});
		});

		$("#jQ3").on("click", function() {
			var result = $(this).next();
			var firstNum = $("#firstNum").val();
			var secondNum = $("#secondNum").val();

			$.ajax({
				url : "/ajaxTest3",
				data : {
					firstNum : firstNum,
					secondNum : secondNum
				},
				type : "get",
				success : function(data) { // 서버가 주는 데이터가 매개변수로 들어옴
					result.html(data);
				},
				error : function() {
					console.log("실패3");
				}
			});
		});
		
		$("#jQ4").on("click", function () {
		    var name = $("#name").val();
		    var age = $("#age").val();
		    var addr = $("#addr").val();
		    // 객체 생성
		    var student = { name: name, age: age, addr: addr };

		    $.ajax({
		      url: "/ajaxTest4",
		      type: "get",
		      data: student,
		      success: function () {
		        console.log("성공");
		      }
		    });
		  });
		
		$("#jQ5").on("click", function () {
		    var memberId = $("#ajax5").val();
		    var result = $(this).next();

		    $.ajax({
		      url: "/ajaxTest5",
		      type: "get",
		      data: { memberId: memberId },
		      success: function (data) {
				if(data == null) {
					result.html("회원 정보가 없습니다.");
				} else {
					result.empty();
					var memberNo = data.memberNo;
					result.append("회원번호 : " + memberNo + "<br>");
					var memberId = data.memberId;
					result.append("회원 아이디 : " + memberId + "<br>");
					var memberName1 = data.memberName1;
					result.append("회원이름1 : " + memberName1 + "<br>");
					var memberName2 = data.memberName2;
					result.append("회원이름2 : " + memberName2 + "<br>");
					var memberName3 = decodeURIComponent(data.memberName2);
					result.append("회원이름3 : " + memberName3);
				}
		      }
		    });
		  }); 
		
		$("#jQ6").on("click", function () {
		    var result = $(this).next();
		    $.ajax({
		      url: "/ajaxTest6",
		      type: "get",
		      success: function (data) {
				if(data.length != 0) {
					var html = "";
					
					for(var i=0; i<data.length; i++) {
						var memberNo = data[i].memberNo;
						var memberName = decodeURIComponent(data[i].memberName);
						var phone = data[i].phone;
						html += "회원번호 : " + memberNo + " / 이름 : " + memberName + " / 전화번호 : " + phone + "<br>";
						if(i == 4) {
							break;
						}
					}
					result.html(html);
				} else {
					result.html("회원정보가 없습니다.")
				}
		      }
		    });
		  });
		
		$("#jQ7").on("click", function () {
		    var result = $(this).next();
			console.log("제발");
		    $.ajax({
		      url: "/ajaxTest7",
		      type: "get",
		      success: function (data) {
		        var html = "";
		        var keys = Object.keys(data); // 객체의 키값들만 전부 가져옴

		        for (var i = 0; i < keys.length; i++) {
		          var memberNo = data[keys[i]].memberNo;
		          var memberName = decodeURIComponent(data[keys[i]].memberName);
		          var phone = data[keys[i]].phone;
		          html += "회원번호 : " + memberNo + " / 이름 : " + memberName + " / 전화번호 : " + phone + "<br>";
		          if (i == 4) {
		              break;
		            }
		        }
		        result.html(html);
		      }
		    });
		  });
		
		$("#jQ8").on("click", function () {
		    var result = $(this).next();
		    var memberId = $("#ajax8").val();

		    $.ajax({
		      url: "/ajaxTest8",
		      type: "get",
		      data: { memberId: memberId },
		      success: function (data) {
		        if (data != null) {
		          var memberNo = data.memberNo;
		          var memberName = data.memberName;
		          var phone = data.phone;

		          result.html("회원번호 : " + memberNo + "<br>이름 : " + memberName + "<br>전화번호 : " + phone + "<br>");
		        } else {
		          result.html("회원정보가 없습니다.");
		        }
		      }
		    });
		  });
		
		$("#jQ9").on("click", function () {
		    var result = $(this).next();

		    $.ajax({
		      url: "ajaxTest9",
		      success: function (data) {
		        if (data.length != 0) {
		          var html = "";

		          for (var i = 0; i < data.length; i++) {
		            var memberNo = data[i].memberNo;
		            var memberName = data[i].memberName;
		            var phone = data[i].phone;
		            html += "회원번호 : " + memberNo + " / 이름 : " + memberName + " / 전화번호 : " + phone + "<br>";
		            if (i == 4) {
		              break;
		            }
		          }
		          result.html(html);
		        } else {
		          result.html("회원정보 없어요 ㅎㅎ");
		        }
		      }
		    });
		  });
		
		$("#jQ10").on("click", function () {
		    var result = $(this).next();

		    $.ajax({
		      url: "/ajaxTest10",
		      success: function (data) {
		        var html = "";
		        // data 객체에서 순서대로 key값을 꺼내서 자동으로 for문 수행
		        for (var key in data) {
		          var memberNo = data[key].memberNo;
		          var memberName = data[key].memberName;
		          var phone = data[key].phone;
		          html += "번호 : " + memberNo + "/ 이름: " + memberName + " / 전화번호 : " + phone + "<br>";
		          
		          console.log(key);
		        }
		        result.html(html);
		      }
		    });
		  });
	</script>
	<%@include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>