package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxTest3Servlet
 */
@WebServlet(name = "AjaxTest3", urlPatterns = { "/ajaxTest3" })
public class AjaxTest3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxTest3Servlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 값 추출
		int firstNum = Integer.parseInt(request.getParameter("firstNum")); 
		int secondNum = Integer.parseInt(request.getParameter("secondNum")); 
		//3. 비지니스 로직
		int result = firstNum + secondNum;
		//4. 결과처리
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(firstNum + " + " + secondNum + " = " + result);
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
