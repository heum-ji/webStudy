package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet(name = "BoardWrite", urlPatterns = { "/boardWrite" })
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardWriteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.인코딩
		request.setCharacterEncoding("utf-8");

		// 2. 값 추출
		// 파일 업로드 시 enctype 필터링
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시글 작성 오류[enctype]");
			request.setAttribute("loc", "/");

			rd.forward(request, response);
		}
		// 파일 업로드 준비
		// 1) 파일 업로드 경로 지정
		String root = getServletContext().getRealPath("/"); // WebContent 경로 가져오기
		String saveDirectory = root + "upload/board"; // 파일 저장 경로지정
		// 2) 업로드 파일의 최대크기 지정 - 10mb
		int maxSize = 10 * 1024 * 1024;
		// 3) request -> MultipartRequest객체로 변환
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		Board b = new Board();
		b.setBoardTitle(mRequest.getParameter("boardTitle"));
		b.setBoardWriter(mRequest.getParameter("boardWriter"));
		b.setBoardContent(mRequest.getParameter("boardContent"));
		b.setFilename(mRequest.getOriginalFileName("filename"));
		b.setFilepath(mRequest.getFilesystemName("filename"));

		// 3. 비지니스 로직
		int result = new BoardService().insertBoard(b);

		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result > 0) {
			request.setAttribute("msg", "게시글 등록 성공");
		} else {
			request.setAttribute("msg", "게시글 등록 실패");
		}
		request.setAttribute("loc", "/boardList?reqPage=1");

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
