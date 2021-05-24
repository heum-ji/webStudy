package el;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeViewData;

/**
 * Servlet implementation class ElTest3Servlet
 */
@WebServlet(name = "ElTest3", urlPatterns = { "/elTest3" })
public class ElTest3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElTest3Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 2. 값추출
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		// 3. 비지니스로직
		NoticeViewData nvd = new NoticeService().selectNoticeView(noticeNo);
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/view/el/elTest3.jsp");
		request.setAttribute("n", nvd.getN());

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
