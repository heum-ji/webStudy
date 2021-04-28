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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 3. 로직처리
		MemberDao dao = new MemberDao();
		Member member = dao.selectOneMember(id, pw);
		
		// 4. 결과 처리
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter(); 
		// HTML 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>로그인 결과</title></head><body>");
		out.println("<h1>로그인 결과</h1><hr>");
		
		if(member == null) {
			// 로그인을 실패한 경우
			out.println("<h2>로그인 실패</h2>");
			out.println("<script>alert('아이디 또는 비밀번호를 확인하세요');</script>");
		} else {
			// 로그인을 성공한 경우
			out.println("<h2>[" + member.getMemberName() + "]님 환영합니다. </h2>");
			out.println("<script>alert('로그인 성공!!');</script>");
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
