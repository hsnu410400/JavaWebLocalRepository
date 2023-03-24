package rest.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rest.entity.User;
import rest.service.UserService;

@WebServlet("/rest/user/*")
public class UserController extends HttpServlet {
	
	private UserService userService = UserService.getUserServiceInstance();
	
	private Integer parsePathInfo(String pathInfo) {
		/*
		  ^    首
		  \\d  數字
		  +    1..* 個
		  $    尾 
		*/
		Pattern pattern = Pattern.compile("^/\\d+$"); // 正則表示式
		Matcher matcher = pattern.matcher(pathInfo);  // 匹配: pathInfo 有沒有符合 pattern
		
		if(matcher.find()) { // 有匹配到
			return Integer.parseInt(pathInfo.substring(1));
		}
		return null;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = parsePathInfo(req.getPathInfo());
		System.out.println("id = " + id);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/rest/user_form.jsp");
		req.setAttribute("users", userService.queryAll());
		if(id == null) {
			req.setAttribute("_method", "POST");
		} else {
			User user = userService.get(id);
			req.setAttribute("id", id);
			req.setAttribute("user", user);
			
			boolean isDelete = Boolean.parseBoolean(req.getParameter("delete"));
			if(isDelete) { // 刪除
				req.setAttribute("_method", "DELETE");
			} else { // 修改
				req.setAttribute("_method", "PUT");
			}
		}
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		int salary = Integer.parseInt(req.getParameter("salary"));
		User user = new User(name, salary);
		userService.add(user);
		// 重導
		resp.sendRedirect("/JavaWeb/rest/user/");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int salary = Integer.parseInt(req.getParameter("salary"));
		// 修改程序
		User user = new User(name, salary);
		userService.update(id, user);
		// 重導
		resp.sendRedirect("/JavaWeb/rest/user/");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		// 刪除程序
		userService.delete(id);
		// 重導
		resp.sendRedirect("/JavaWeb/rest/user/");
	}

	
}
