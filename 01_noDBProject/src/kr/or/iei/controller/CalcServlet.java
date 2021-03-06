package kr.or.iei.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(name = "Calc", urlPatterns = { "/calc" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalcServlet() {
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

		// 2. 데이터 추출
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		String oper = request.getParameter("oper");

		// 3. 로직처리
		int result = 0;
		
		System.out.println(oper);
		if (oper.equals("+")) {
			result = num1 + num2;
		} else if (oper.equals("-")) {
			result = num1 - num2;
		} else if (oper.equals("*")) {
			result = num1 * num2;
		} else if (oper.equals("/")) {
			result = num1 / num2;
		}

		// 4. 사용자 화면 출력(HTML 을 작성)
		response.setContentType("text/html;charset=UTF-8"); // 응답을 HTML파일로하고, 문자셋은 UTF-8
		PrintWriter out = response.getWriter(); // 사용자가 볼 HTML을 작성하는 객체 생성

		// HTML 작성
		out.println("<!DOCTYPE html>");
		out.println("<html><head><title>Calc 결과</title></head><body>");
		out.println("<h1>Calc 결과<h1><hr>");
		out.println("<h2>" + num1 + " " + oper + " " + num2 + " = " + result + "</h2>");
		out.println("</body></html>");
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
