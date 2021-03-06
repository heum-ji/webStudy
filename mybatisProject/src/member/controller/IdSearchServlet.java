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
 * Servlet implementation class IdSearchServlet
 */
@WebServlet(name = "IdSearch", urlPatterns = { "/idSearch" })
public class IdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdSearchServlet() {
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
		// 2. 값 추출
		String memberName = request.getParameter("memberName");
		String phone = request.getParameter("phone");
		// 3. 비지니스 로직
		String memberId = new MemberService().selectOneMemberId(memberName, phone);
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (memberId != null) {
			request.setAttribute("msg", "회원님의 아이디는 [ " + memberId + " ] 입니다.");
			request.setAttribute("loc", "/");
		} else {
			request.setAttribute("msg", "정보를 조회할 수 없습니다.");
			request.setAttribute("loc", "/searchFrm");
		}
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
