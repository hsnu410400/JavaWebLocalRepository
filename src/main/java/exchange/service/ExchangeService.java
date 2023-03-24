package exchange.service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class ExchangeService {
	
	public double getPrice(String symbol) {
		// symbol 商品代號:
		// 例如: TWDJPY=X, USDTWD=X
		double price = 0;
		try {
			Stock stock = YahooFinance.get(symbol);
			price = stock.getQuote().getPrice().doubleValue();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return price;
	}
	
}
