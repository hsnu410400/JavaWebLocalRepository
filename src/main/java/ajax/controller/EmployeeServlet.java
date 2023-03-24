package ajax.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ajax.dao.EmployeeDao;
import ajax.entity.Employee;

@WebServlet("/ajax/employees/*")
public class EmployeeServlet extends HttpServlet {
	private EmployeeDao employeeDao = EmployeeDao.getInstance();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		PrintWriter out = resp.getWriter();
		if(pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/*")) {
			// 取得所有 employees
			List<Employee> employees = employeeDao.getAllEmployees();
			out.print(gson.toJson(employees));
		} else {
			// 根據 id 找到該筆 Employee
			Integer id = Integer.parseInt(pathInfo.substring(1));
			Employee employee = employeeDao.getEmployeeById(id);
			if(employee == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				out.print(gson.toJson(employee));
			}
		}
		out.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		BufferedReader reader = req.getReader(); // 取得前端傳來的串流資料(json)
		Employee employee = gson.fromJson(reader, Employee.class);
		// 新增
		employeeDao.addEmployee(employee);
		resp.setStatus(HttpServletResponse.SC_OK);
		out.print("{\"result\": \"ADD OK\"}");
		out.flush();
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		try {
			Integer id = Integer.parseInt(pathInfo.substring(1));
			Employee employee = employeeDao.getEmployeeById(id);
			if(employee == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				PrintWriter out = resp.getWriter();
				BufferedReader reader = req.getReader();
				Employee updateEmployee = gson.fromJson(reader, Employee.class);
				updateEmployee.setId(id);
				employeeDao.updateEmployee(id, updateEmployee);
				out.print("{\"result\": \"UPDATE OK\"}");
				out.flush();
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		try {
			Integer id = Integer.parseInt(pathInfo.substring(1));
			Employee employee = employeeDao.getEmployeeById(id);
			if(employee == null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				PrintWriter out = resp.getWriter();
				employeeDao.deleteEmployee(id);
				out.print("{\"result\": \"DELETE OK\"}");
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}