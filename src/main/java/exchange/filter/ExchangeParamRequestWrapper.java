package exchange.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ExchangeParamRequestWrapper extends HttpServletRequestWrapper {
	
	public ExchangeParamRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		if(name.equals("amount") && 
				(super.getParameter("amount") == null || super.getParameter("amount").trim().length()==0)) {
			return "1";
		}
		return super.getParameter(name);
	}
	
}
