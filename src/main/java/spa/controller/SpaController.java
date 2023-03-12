package spa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spa.dao.SpaDao;
import spa.entity.Master;
import spa.entity.Member;
import spa.entity.Order;
import spa.entity.Spa;

@WebServlet("/servlet/spa/*")
public class SpaController extends HttpServlet {
	private SpaDao spaDao = new SpaDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("path info = " + req.getPathInfo());
		
		List<Spa> spaList = spaDao.queryAllSpas(); 
		List<Master> masterList = spaDao.queryAllMasters();
		
		HttpSession session = req.getSession(false); // false 不另外產生新的 session, 而使用當下的 session
		List<Order> orderList = null;
		if(session != null && session.getAttribute("member") instanceof Member) {
			Member member = (Member)session.getAttribute("member");
			orderList = spaDao.queryOrdersByMember(member);
		}
		//List<Order> orderList = spaDao.queryOrders(); // 所有的預約單
		
		String dispatcherPath = null;
		switch (req.getPathInfo()) {
			case "/": // Spa 預約網頁 (http://localhost:8080/JavaWeb/servlet/spa/)
				dispatcherPath = "/WEB-INF/view/spa/spa.jsp";
				break;
			case "/list": // Spa 預約結果網頁 (http://localhost:8080/JavaWeb/servlet/spa/list)
				dispatcherPath = "/WEB-INF/view/spa/spa_reserve_result.jsp";
				break;	
			case "/login": // 登入網頁 (http://localhost:8080/JavaWeb/servlet/spa/login)
				dispatcherPath = "/WEB-INF/view/spa/spa_login.jsp";
				break;
			case "/logout": // 登出網頁 (http://localhost:8080/JavaWeb/servlet/spa/logout)
				// 清除 session
				if(session != null) {
					session.invalidate(); // session 失效
				}
				dispatcherPath = "/WEB-INF/view/spa/spa_logout.jsp";
				break;	
			default:
				resp.sendRedirect("http://localhost:8080/JavaWeb/servlet/spa/"); // 重導至首頁
				return;
		}
		// 分派器
		RequestDispatcher rd = req.getRequestDispatcher(dispatcherPath);
		req.setAttribute("spaList", spaList);
		req.setAttribute("masterList", masterList);
		req.setAttribute("orderList", orderList);
		rd.forward(req, resp);
		
	}
	
	// 新增預約按摩
	// spa.jsp 按下表單的預約按鈕後會執行到的方法
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		switch (req.getPathInfo()) {
			case "/": // 新增預約 (http://localhost:8080/JavaWeb/servlet/spa/) 
				doOrder(req, resp);
				break;
			case "/login": // 登入驗證 (http://localhost:8080/JavaWeb/servlet/spa/login) 
				doLogin(req, resp);
				break;
		}
	}
	
	private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 中文編碼配置
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// 抓取表單參數
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 登入驗證
		List<Member> memberList = spaDao.queryAllMembers();
		Optional<Member> optMember = memberList.stream()
				.filter(m -> m.getUsername().equals(username) && m.getPassword().equals(password))
				.findAny();
		boolean isPass = optMember.isPresent();
		
		resp.getWriter().print("login check...<p>");
		resp.getWriter().print(username + "<p>");
		resp.getWriter().print(password + "<p>");
		resp.getWriter().print(isPass + "<p>");
		
		if(isPass) {
			// 將登入資訊寫入 session
			HttpSession session = req.getSession();
			//session.setMaxInactiveInterval(15);		
			session.setAttribute("member", optMember.get());
			session.setAttribute("isPass", isPass);
			// 登入成功導入 Spa 首頁 
			resp.sendRedirect("http://localhost:8080/JavaWeb/servlet/spa/"); // 重導至首頁
		}else {
			RuntimeException re=new RuntimeException();
			throw re;
			//resp.sendError(500,"登入失敗");
		}
	}
	
	private void doOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 中文編碼配置
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// 抓取表單參數
		String userName = req.getParameter("userName");
		Integer spaId = Integer.parseInt(req.getParameter("spaId"));
		Integer masterId = Integer.parseInt(req.getParameter("masterId"));
		String reserve = req.getParameter("reserve");
		// 根據 spaId 取得 Spa 物件
		Spa spa = spaDao.queryAllSpas().stream()
					.filter(sp -> sp.getId().intValue() == spaId.intValue())
					.findFirst()
					.get();
		// 建立預約訂單物件
		Order order = new Order();
		order.setId(1);
		order.setUserName(userName);
		order.setSpaId(spaId);
		order.setSpaPrice(spa.getPrice());
		order.setSpaTime(spa.getTime());
		order.setMasterId(masterId);
		order.setReserve(reserve);
		
		// 將此預約單放入到orderList中
		List<Order> orderList = spaDao.queryOrders();
		orderList.add(order); // 新增訂單到歷史紀錄中

		// 查詢 member 歷史訂單紀錄
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("member") instanceof Member) {
			Member member = (Member)session.getAttribute("member");
			orderList = spaDao.queryOrdersByMember(member);
		} else {
			// 沒有 session 資料重新導向登入
			resp.sendRedirect("http://localhost:8080/JavaWeb/servlet/spa/login");
			return;
		}
		
		// 到歷史紀錄中查詢 member 本次訂單紀錄
		// 取得最新一筆的 order id
		Integer id = orderList.get(orderList.size()-1).getId();
		order = spaDao.getOrderById(id);
		
		// 分派器
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/spa/spa_reserve_result.jsp");
		req.setAttribute("order", order); // 本次預約訂單
		req.setAttribute("orderList", orderList); // 預約單(歷史預約單紀錄)
		rd.forward(req, resp);
	} 
	
}
