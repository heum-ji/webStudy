package el;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ElTest1Servlet
 */
@WebServlet(name = "ElTest1", urlPatterns = { "/elTest1" })
public class ElTest1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElTest1Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 값추출
		// 3. 비지니스 로직
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/view/el/elTest1.jsp");
		
		request.setAttribute("num", 100);
		request.setAttribute("str", "테스트");
		request.setAttribute("str1", "이건 리퀘스트 영역");
		
		HttpSession session = request.getSession();
		session.setAttribute("num", 200);
		session.setAttribute("str", "테스트2");
		session.setAttribute("str2", "이건 세션 영역");
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
