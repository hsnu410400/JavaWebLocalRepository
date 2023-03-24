package rest.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/rest/user/*")
public class UserFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 過濾 _method
		String _method = req.getParameter("_method");
		final String http_method = _method == null ? req.getMethod() : _method;
		
		HttpServletRequestWrapper wrapperRequest = new HttpServletRequestWrapper(req) {
			@Override
			public String getMethod() {
				return http_method;
			}
		};
		chain.doFilter(wrapperRequest, res);
	}
}
