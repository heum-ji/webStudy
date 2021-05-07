<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<% int totalCount=(Integer) request.getAttribute("totalCount"); %>
		<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>

		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>Insert title here</title>
			<style>
				#more-btn {
					margin-top: 100px;
				}

				.photoWrapper {
					padding-top: 20px;
					clear: right;
					display: flex;
					justify-content: space-around;
					flex-wrap: wrap;
				}

				.photo {
					border: 1px solid #ccc;
					margin-top: 30px;
					width: 18%;
					height: 122px;
					overflow: hidden;
					transition-duration: 1s;
				}

				.photo>img {
					width: 100%;
					height: 100px;
				}

				.photo>p {
					text-align: center;
				}

				.photo>img:hover {
					transform: scale(1.4);
				}
			</style>
		</head>

		<body>
			<%@include file="/WEB-INF/views/common/header.jsp" %>
				<div class="container">
					<fieldset>
						<legend>사진게시판</legend>
						<%if (m !=null) {%>
							<div>
								<a class="btn btn-info writeBtn" href="/photoWriteFrm">글쓰기</a>
							</div>
							<%}%>
								<div class="photoWrapper"></div>
								<button class="btn btn-outline-info btn-block" currentCount="0" value="" totalCount="<%=totalCount %>"
									id="more-btn">더보기</button>
					</fieldset>
				</div>
				<script>
					function more(start) {
						$.ajax({
							url: "/photoMore",
							data: { start: start },
							type: "post",
							success: function () {

							}
						});
					}
				</script>
				<%@include file="/WEB-INF/views/common/footer.jsp" %>
		</body>

		</html>