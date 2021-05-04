package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeCommentupdateServlet
 */
@WebServlet(name = "NoticeCommentupdate", urlPatterns = { "/noticeCommentupdate" })
public class NoticeCommentupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeCommentupdateServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int ncNo = Integer.parseInt(request.getParameter("ncNo"));
		String ncContent = request.getParameter("ncContent");
		// 3. 비지니스 로직
		int result = new NoticeService().updateNoticeComment(ncNo, ncContent);
		// 4. 결과 처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result > 0) {
			request.setAttribute("msg", "댓글 수정 완료");
		} else {
			request.setAttribute("msg", "댓글 수정 실패");
		}
		request.setAttribute("loc", "/noticeView?noticeNo=" + noticeNo);
		
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
