package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class CheckedChangeLevelServlet
 */
@WebServlet(name = "CheckedChangeLevel", urlPatterns = { "/checkedChangeLevel" })
public class CheckedChangeLevelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckedChangeLevelServlet() {
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
		// 2.값 추출
		String num = request.getParameter("num");
		String level = request.getParameter("level");
		// 3.비지니스 로직
		boolean result = new MemberService().checkedChangeLevel(num, level);
		// 4.결과처리
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/common/msg.jsp");

		if (result) {
			request.setAttribute("msg", "변경 성공");
		} else {
			request.setAttribute("msg", "변경 실패");
		}
		request.setAttribute("loc", "/adminPage");
		rd.forward(request, response);
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
