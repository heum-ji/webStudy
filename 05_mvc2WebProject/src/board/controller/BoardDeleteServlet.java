package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet(name = "BoardDelete", urlPatterns = { "/boardDelete" })
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardDeleteServlet() {
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
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		// 3. 비지니스 로직
		BoardService service = new BoardService();
		Board b = service.selectOneBoard(boardNo); // 삭제 전 객체 저장
		int result = service.deleteBoard(boardNo); // 삭제
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result > 0) {
			if (b.getFilepath() != null) { // 삭제한 게시물이 첨부파일이 있는경우
				String root = getServletContext().getRealPath("/");
				String file = root + "upload/board/" + b.getFilepath();

				File delFile = new File(file);
				delFile.delete(); // 서버에서 실제 첨부파일도 삭제
			}
			request.setAttribute("msg", "게시물 삭제 성공!");
			request.setAttribute("loc", "/boardList?reqPage=1"); // 삭제 성공 시 자유게시판 리스트로 가기
		} else {
			request.setAttribute("msg", "게시물 삭제 실패!");
			request.setAttribute("loc", "/boardView?boardNo=" + boardNo); // 실패 시 해당 글 다시 보기
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
