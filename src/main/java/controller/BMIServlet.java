package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BMIService;

/*
 * 路徑測試:
 * http://localhost:8080/JavaWeb/servlet/bmi
 * http://localhost:8080/JavaWeb/servlet/bmi?height=170&weight=60
 * */
@WebServlet("/servlet/bmi")
public class BMIServlet extends HttpServlet {
	
	private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.確認編碼
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// 2.取得參數
		String height = req.getParameter("height");
		String weight = req.getParameter("weight");
		
		// 3.建立 BMIService 
		BMIService bmiService = new BMIService();
		// 4.調用 getBmi() 方法
		String output =bmiService.getBmi(height, weight);
		// 建立分派器 
		RequestDispatcher rd = req.getRequestDispatcher("/html/bmi_result.jsp");
		req.setAttribute("output", output);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}
	
}
