<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<!-- daum map API -->
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<!-- naver map API -->
		<script type="text/javascript"
			src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=o95rh34m1q&submodules=geocoder"></script>
	</head>

	<body>
		<%@include file="/WEB-INF/views/common/header.jsp" %>
			<div class="container">
				<h3>1. 다음 주소찾기</h3>
				<div>
					<input type="text" id="postCode" class="short form-control" readonly>
					<button onclick="addrSearch();" class="btn btn-primary">주소검색</button>
				</div>
				<div>
					<input type="text" id="roadAddr" class="form-control" placeholder="도로명주소">
				</div>
				<div>
					<input type="text" id="jibunAddr" class="form-control" placeholder="지번주소">
				</div>
				<div>
					<input type="text" id="detailAddr" class="form-control" placeholder="상세주소">
				</div>
				<hr>
				<h3>2. 네이버 지도 API</h3>
				<div id="map" style="width: 100%; height: 500px;"></div>
			</div> <!-- /container -->
			<script>
				// 네이버 지도 사용
				window.onload = function () {
					// 아무 설정 없이 지도 객체를 만드는 경우 서울시청이 지도의 중심
					var map = new naver.maps.Map('map', {
						center: new naver.maps.LatLng(37.533807, 126.896772), // KH 학원 위치로 중심 변경
						zoom: 17,
						zoomControl: true,
						zoomControlOptions: {
							position: naver.maps.Position.TOP_RIGHT,
							style: naver.maps.ZoomControlStyle.SMALL
						}
					});

					// marker 설정
					var marker = new naver.maps.Marker({
						position: new naver.maps.LatLng(37.533807, 126.896772),
						map: map
					});

					// kh 주소 정보창 msg
					var contentString = [
						'<div class="iw_inner">',
						'  <h3>KH정보교육원</H3>',
						'  <p>서울시 영등포구 선유동2로 57 이레빌딩 19F, 20F</p>',
						'</div>'
					].join("");

					// 지도 위 정보 창
					var inforWindow = new naver.maps.InfoWindow();

					naver.maps.Event.addListener(marker, "click", function (e) {
						if (inforWindow.getMap()) {
							inforWindow.close();
						} else {
							inforWindow = new naver.maps.InfoWindow({
								content: contentString
							});
							inforWindow.open(map, marker);
						}
					});

					naver.maps.Event.addListener(map, "click", function (e) {
						marker.setPosition(e.coord); // 마커 위치를 클릭한 위치로 이동

						// 클릭 시 정보 창 있으면 지우기
						if (inforWindow.getMap()) {
							inforWindow.close();
						}

						// reverseGeocoder를 이용해서 위/경도 값을 주소로 변환
						naver.maps.Service.reverseGeocode({
							location: new naver.maps.LatLng(e.coord.lat(), e.coord.lng()),
						}, function (status, response) {
							// 범위를 벗어난 주소인 경우
							if (status != naver.maps.Service.Status.OK) {
								return alert("주소를 찾을 수 없습니다.");
							}
							var result = response.result;
							items = result.items;

							var address = items[1].address;
							contentString = [
								'<div class="iw_inner">',
								'<p>' + address + '</p>',
								'</div>'
							].join("");
						});
					});
				}

				// daum 주소 검색
				function addrSearch() {
					new daum.Postcode({
						oncomplete: function (data) {
							$("#postCode").val(data.zonecode); // 우편번호
							$("#roadAddr").val(data.roadAddress); // 도로명 주소
							$("#jibunAddr").val(data.jibunAddress);
							$("#detailAddr").focus();
						}
					}).open();
				}
			</script>
			<%@include file="/WEB-INF/views/common/footer.jsp" %>
	</body>

	</html>