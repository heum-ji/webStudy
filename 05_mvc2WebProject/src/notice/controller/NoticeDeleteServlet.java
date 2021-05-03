package notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "NoticeDelete", urlPatterns = { "/noticeDelete" })
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeDeleteServlet() {
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
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		// 3. 비지니스로직
		NoticeService service = new NoticeService();
		Notice n = service.selectOneNotice(noticeNo);
		int result = service.deleteNotice(noticeNo); // 삭제
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result > 0) {
			if (n.getFilepath() != null) { // 삭제한 공지사항이 첨부파일이 있는 경우
				String root = getServletContext().getRealPath("/");
				String file = root + "upload/notice/" + n.getFilepath();

				File delFile = new File(file);
				delFile.delete();
			}
			request.setAttribute("msg", "공지사항 삭제 성공!");
			request.setAttribute("loc", "/noticeList?reqPage=1"); // 성공 시 전체글 리스트로 가기
		} else {
			request.setAttribute("msg", "공지사항 삭제 실패!");
			request.setAttribute("loc", "/noticeView?noticeNo=" + noticeNo); // 실패 시 해당 글 다시 보기
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
