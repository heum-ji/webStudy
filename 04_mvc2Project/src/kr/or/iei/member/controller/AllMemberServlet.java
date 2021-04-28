package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.dao.MemberDao;
import kr.or.iei.member.vo.Member;

/**
 * Servlet implementation class AllMemberServlet
 */
@WebServlet(name = "AllMember", urlPatterns = { "/allMember" })
public class AllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllMemberServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 인코딩
		request.setCharacterEncoding("utf-8");

		// 2. 데이터 추출 - 없음

		// 3. 로직처리
		MemberDao dao = new MemberDao();
		ArrayList<Member> memberArr = dao.selectAllMember();

		// 4. 결과 처리
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// HTML 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>조회 결과</title></head><body>");
		out.println("<h1>전체 회원 조회</h1><hr>");

		if (!memberArr.isEmpty()) {
			// 조회 성공
			out.println("<h2>전체 회원 조회 성공</h2>");
			// 제목
			out.println("<table border='1'><thead><tr>");
			out.println("<th>MEMBER_NO</th><th>MEMBER_ID</th><th>MEMBER_PW</th><th>MEMBER_NAME</th>");
			out.println("<th>PHONE</th><th>ADDRESS</th><th>MEMBER_LEVEL</th><th>ENROLL_DATE</th>");
			out.println("</tr></thead><tbody>");
			// 내용
			for (Member member : memberArr) {
				out.println("<tr><td>" + member.getMemberNo() + "</td><td>" + member.getMemberId() + "</td><td>"
						+ member.getMemberPw() + "</td><td>" + member.getMemberName() + "</td><td>" + member.getPhone()
						+ "</td><td>" + member.getAddress() + "</td><td>" + member.getMemberLevel() + "</td><td>"
						+ member.getEnrollDate() + "</td></tr>");
			}
			out.println("</tbody></table>");

		} else {
			// 조회 실패
			out.println("<h2>전체 회원 조회 실패</h2>");
		}
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}