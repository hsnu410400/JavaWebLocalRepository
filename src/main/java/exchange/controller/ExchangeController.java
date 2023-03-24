package exchange.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exchange.service.ExchangeService;

@WebServlet("/exchange")
public class ExchangeController extends HttpServlet {
	
	private ExchangeService service = new ExchangeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/exchange/exchange_form.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] symbols = req.getParameterValues("symbol");
		int amount = Integer.parseInt(req.getParameter("amount"));
		double total = 0.0;
		if(symbols != null && symbols.length == 2) {
			String symbol = symbols[0] + symbols[1] + "=X";
			total = amount * service.getPrice(symbol); // 換匯後的結果
		}		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/exchange/exchange_result.jsp");
		req.setAttribute("symbols", symbols);
		req.setAttribute("amount", amount);
		req.setAttribute("total", total);
		rd.forward(req, resp);
	}
	
}
