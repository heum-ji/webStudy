package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateMember1Servlet
 */
@WebServlet(name = "UpdateMember1", urlPatterns = { "/updateMember1" })
public class UpdateMember1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMember1Servlet() {
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
		// 2. 값추출
		Member m = new Member();
		m.setMemberPw(request.getParameter("myPw"));
		m.setAddress(request.getParameter("myAddr"));
		m.setPhone(request.getParameter("myPhone"));
		m.setMemberId(request.getParameter("myId"));
		// 3. 비지니스로직
		int result = new MemberService().updateMember(m);

		// 4. 결과처리
		// 결과처리할 페이지 지정
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		// 페이지에서 사용할 데이터 등록
		if (result > 0) {
			request.setAttribute("msg", "정보변경 성공!!");
		} else {
			request.setAttribute("msg", "정보변경 실패 ㅠ");
		}
		request.setAttribute("loc", "/mypage1"); // 마이페이지로 이동시키기 위해 서블릿 매핑값을 전달
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
