package notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownServlet() {
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
		// 3. 비지니스 로직
		Notice n = new NoticeService().selectOneNotice(noticeNo);
		// 4. 결과처리
		// 파일 위치 지정
		String root = getServletContext().getRealPath("/");
		String file = root + "upload/notice/" + n.getFilepath();
		// 파일과 현재 서블릿을 연결해서 읽어온 후 사용자에게 전달
		// 파일을 서블릿으로 가져오기 위한 객체 생성
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis); // 속도 개선용 보조스트림
		// 읽어온 파일을 사용자에게 전달하는 객체
		ServletOutputStream sos = response.getOutputStream(); // 사용자 브라우저와 서블릿 불러오기
		BufferedOutputStream bos = new BufferedOutputStream(sos); // 속도 개선용 보조스트림

		// 브라우저 종류에 따라 파일명을 변경하는 로직
		String resFilename = ""; // 최종 파일명

		// 브라우저가 IE 인지 확인
		boolean bool = request.getHeader("user-agent").indexOf("MSIE") != -1
				|| request.getHeader("user-agent").indexOf("Trident") != -1;

		if (bool) { // IE인 경우
			resFilename = URLEncoder.encode(n.getFilename(), "UTF-8");
			resFilename = resFilename.replaceAll("\\\\", "%20");
		} else {
			resFilename = new String(n.getFilename().getBytes("UTF-8"), "ISO-8859-1");
		}
		// 파일 다운로드를 위한 HTTP header 설정
		response.setContentType("application/octet-stream"); // 파일 다운로드 컨텐츠를 사용자 브라우저에 알림
		response.setHeader("Content-Disposition", "attachment;filename=" + resFilename); // 다운로드할 파일 명 지정

		// 파일 전송
		while (true) {
			int read = bis.read();

			if (read != -1) {
				bos.write(read);
			} else {
				break;
			}
		}
		bos.close();
		bis.close();
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
