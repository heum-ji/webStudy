package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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

		// 4. 결과 처리(jsp에게 데이터를 주면서 출력을 요청)

		if (member != null) {
			// 결과를 처리할 페이지를 지정
			RequestDispatcher rd = request.getRequestDispatcher("/view/loginSuccess.jsp");
			// 화면을 구성하기 위한 데이터 등록
			request.setAttribute("memberName", member.getMemberName());
			// 페이지 이동
			rd.forward(request, response);
		} else {
			// 결과를 처리할 페이지를 지정
			RequestDispatcher rd = request.getRequestDispatcher("/view/loginFail.jsp");
			// 화면을 구성하기 위한 데이터 등록
			// 페이지 이동
			rd.forward(request, response);
		}
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
