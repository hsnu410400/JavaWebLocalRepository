package exchange.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/exchange")
public class ExchangeWatermarkFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		ExchangeWatermarkResponseWrapper responseWrapper = new ExchangeWatermarkResponseWrapper(res);
		chain.doFilter(req, responseWrapper);
		
		String html = responseWrapper.getHTML();
		html = html.replaceAll("<body ", "<body background='/JavaWeb/image/watermark.webp' ");
		res.getWriter().print(html);
	}
	
}
