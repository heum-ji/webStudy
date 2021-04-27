package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.dao.MemberDao;
import kr.or.iei.member.vo.Member;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet(name = "Join", urlPatterns = { "/join" })
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JoinServlet() {
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

		// 2. 데이터 추출
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		Member member = new Member();

		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setPhone(phone);
		member.setAddress(address);

		// 3. 로직처리
		MemberDao dao = new MemberDao();
		int result = dao.insertMember(member);

		// 4. 결과 처리
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// HTML 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>로그인 결과</title></head><body>");
		out.println("<h1>회원가입 결과</h1><hr>");

		if (result > 0) {
			// 회원가입을 성공한 경우
			out.println("<h2>회원가입 성공</h2>");
			out.println("<h2>신규 [" + member.getMemberName() + "]님 환영합니다. </h2>");
		} else {
			// 회원가입을 실패한 경우
			out.println("<h2>회원가입 실패</h2>");
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
