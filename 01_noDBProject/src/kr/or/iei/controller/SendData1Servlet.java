package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendData1Servelet
 */
@WebServlet(name = "SendData1", urlPatterns = { "/sendData1" })
public class SendData1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendData1Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서버 호출 완료");
		// 1. 한글이 넘어오는 경우 인코딩이 깨질 수 있으므로 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. 화면에서 넘어온 데이터 추출
		String name = request.getParameter("name");
		System.out.println("클라이언트가 보낸 데이터 : " + name);		
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
