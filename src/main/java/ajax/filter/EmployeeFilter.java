package ajax.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/ajax/employees/*")
public class EmployeeFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.setHeader("Cache-Control", "must-revaladate, no-cache, no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", -1);
		chain.doFilter(req, res);
	}

}
