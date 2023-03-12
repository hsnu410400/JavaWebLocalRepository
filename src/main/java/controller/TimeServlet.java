package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/servlet/time")
public class TimeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 回傳設定 UTF-8
		resp.setCharacterEncoding("UTF-8");
		// 告訴瀏覽器我的文件格式是 text/html, 編碼是 UTF-8
		resp.setContentType("text/html;charset=UTF-8");
		
		// 取得現在時間
		Date now = new Date();
		// 時間格式化
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss E");
		// 時間 + 格式化
		String output = sdf.format(now);
		// 將資料直接傳給前端瀏覽器
		resp.getWriter().print(output);
		
		
	}
	
}
