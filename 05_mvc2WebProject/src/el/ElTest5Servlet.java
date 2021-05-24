package el;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class ElTest5Servlet
 */
@WebServlet(name = "ElTest5", urlPatterns = { "/elTest5" })
public class ElTest5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElTest5Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 값 추출
		String memberId = request.getParameter("memberId");
		// 3. 비지니스로직
		Member m = new MemberService().selectOneMember(memberId);
		// 4. 결과 처리
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", m.getMemberName());
		map.put("phone", m.getPhone());
		map.put("address", m.getAddress());
		
		request.setAttribute("map", map);
		
		RequestDispatcher rd = request.getRequestDispatcher("/view/el/elTest5.jsp");
		
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
