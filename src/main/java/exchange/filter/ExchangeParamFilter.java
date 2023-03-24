package exchange.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/exchange")
public class ExchangeParamFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		if(req.getMethod().equalsIgnoreCase("POST")) {
			//res.getWriter().print("amount:" + req.getParameter("amount"));
			ExchangeParamRequestWrapper requestWrapper = new ExchangeParamRequestWrapper(req);
			chain.doFilter(requestWrapper, res);
		} else {
			chain.doFilter(req, res);
		}
		
	}
	
}
