package board.controller;

import java.io.File;
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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet(name = "BoardUpdate", urlPatterns = { "/boardUpdate" })
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardUpdateServlet() {
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
		if (!ServletFileUpload.isMultipartContent(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			request.setAttribute("msg", "게시글 수정 오류[enctype]");
			request.setAttribute("loc", "/");

			rd.forward(request, response);
			return;
		}
		// 1) 업로드 경로 지정
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload/board";
		// 2) 업로드 파일 최대 크기 지정 - 10MB
		int maxSize = 10 * 1024 * 1024;
		// 3) request-> MultipartRequest로 변환 시 업로드
		MultipartRequest mRequest = new MultipartRequest(request, saveDirectory, maxSize, "UTF-8",
				new DefaultFileRenamePolicy());

		int boardNo = Integer.parseInt(mRequest.getParameter("boardNo"));
		String boardTitle = mRequest.getParameter("boardTitle");
		String filename = mRequest.getOriginalFileName("filename");
		String filepath = mRequest.getFilesystemName("filename");
		String boardContent = mRequest.getParameter("boardContent");
		// 기존 파일 이름 및 경로
		String oldFilename = mRequest.getParameter("oldFilename");
		String oldFilepath = mRequest.getParameter("oldFilepath");

		// 기존 파일 삭제 확인 용
		String status = mRequest.getParameter("status");

		// 기존 파일이 있는 경우
		if (status.equals("delete")) { // 기존 첨부 파일을 삭제 했을 때
			File delFile = new File(saveDirectory + "/" + oldFilepath);
			delFile.delete();
		} else if (oldFilename != null) { // 기존 첨부파일을 수정 안 한 경우
			filename = oldFilename;
			filepath = oldFilepath;
		}
		// 최종 전달할 값 저장 - 게시물 번호, 제목, 내용, 첨부파일 사용자 이름 / 서버 파일 이름
		Board b = new Board(0, boardNo, boardTitle, null, boardContent, null, filename, filepath);

		// 3. 비지니스로직
		int result = new BoardService().updateBoard(b);
		// 4. 결과처리
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");

		if (result > 0) {
			request.setAttribute("msg", "수정 성공");
		} else {
			request.setAttribute("msg", "수정 실패");
		}
		request.setAttribute("loc", "/boardView?boardNo=" + boardNo); // 수정 후 해당 게시물 상세페이지로~

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
