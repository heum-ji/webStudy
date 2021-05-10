<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>payment</title>
    <!-- iamport payment -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
  </head>

  <body>
    <%@include file="/WEB-INF/views/common/header.jsp" %>
      <div class="container">
        <h3>결제모듈</h3>
        <hr>
        <div class="shopping">
          <div>
            <img id="goods" src="/img/mintchoco.jpg">
          </div>
          <div>
            <h4>민트초코</h4>
            <div class="star">
              <img src="/img/star-on.png">
              <img src="/img/star-on.png">
              <img src="/img/star-on.png">
              <img src="/img/star-on.png">
              <img src="/img/star-off.png">
              <div>4.0</div>
            </div>
            <hr>
            <div class="price">
              <span>2000</span><span>원</span><span>(1개당 가격)</span>
            </div>
            <div class="count">
              <span>수량</span>
              <button class="btn btn-outline-secondary btn-sm">-</button>
              <span class="amount">1</span>
              <button class="btn btn-outline-secondary btn-sm">+</button>
            </div>
            <div class="total">
              총 <span id="totalPrice">2000</span>원
            </div>
            <button id="payment" class="btn btn-outline-danger btn-block">결제하기</button>
          </div>
        </div>
      </div>
      <script>
        // 구매 갯수 + / - 처리
        $(".count > button").on("click", function () {
          var currAmount = Number($(".amount").html());

          if ($(this).html() == "+") {
            $(".amount").html(++currAmount);
          } else {
            if (currAmount != 1) {
              $(".amount").html(--currAmount);
            }
          }
          currAmount = Number($(".amount").html());
          var price = Number($(".price > span").first().html());

          $("#totalPrice").html(currAmount * price);
        });

        // 결제
        $("#payment").on("click", function () {
          var price = $("#totalPrice").html();
          var d = new Date();
          var date = d.getFullYear + "" + (d.getMonth() + 1) + "" + d.getDate() + "" + d.getHours() + "" + d.getMinutes() + "" + d.getSeconds();

          IMP.init('imp21296874'); // 결제 api 사용을 위한 결제 코드 입력
          IMP.request_pay({
            merchant_uid: "상품명_" + date,       // 거래 ID
            name: "결제 테스트",                  // 결제 이름
            amount: price,                        // 결제 금액
            buyer_email: "dragon2009t@gmail.com", // 구매자 email주소
            buyer_name: "백지흠",                 // 구매자 이름
            buyer_phone: "010-5141-4556",         // 구매자 주소
            buyer_addr: "서울시 영등포구 당산동", // 구매자 우편번호
            buyer_postcode: "10109"              // 구매자 우편번호
          }, function (rsp) {
            if (rsp.success) { // 결제 성공 시
              alert("결제 성공");
              console.log("카드 승인번호 : " + rsp.apply_num);
            } else {          // 결제 실패 시
              alert("결제 실패");
            }
          });
        });
      </script>
      <%@include file="/WEB-INF/views/common/footer.jsp" %>
  </body>

  </html>