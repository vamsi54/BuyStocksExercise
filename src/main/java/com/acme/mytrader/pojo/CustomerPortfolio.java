package com.acme.mytrader.pojo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CustomerPortfolio {
	LinkedHashMap<String, Stock> stocks;

	public CustomerPortfolio() {
		stocks = new LinkedHashMap<String, Stock>();
		stocks.put("IBM", new Stock("IBM", 40, 50));
		stocks.put("AAPL", new Stock("AAPL", 40, 50));
		stocks.put("SQ", new Stock("SQ", 40, 50));
		stocks.put("MSFT", new Stock("MSFT", 40, 50));
		stocks.put("TSLA", new Stock("TSLA", 40, 50));
	}

	public void removeAllStocks() {
		stocks = new LinkedHashMap<String, Stock>();
	}
	public List<Stock> getCustomerStocks() {
		return new ArrayList<Stock>(stocks.values());
	}

	public void updateStockPrice(String security,double price,int qty) {
		Stock order = stocks.get(security);
		order.setStockPrice(price);
		order.setStockQty(qty);
		stocks.put(security,order);
	}
	
	public double calculateCustomerStockPrice(List<Stock> custOrdList) {
		double Total = 0;
		for (Stock ord : custOrdList) {
			Total = Total + ord.getStockPrice();
		}
		return Total;
	}

	public int calculateCustTotalStockQty(List<Stock> custOrdList) {
		int TotalQty = 0;
		for (Stock ord : custOrdList) {
			TotalQty = TotalQty + ord.getStockQty();
		}
		return TotalQty;
	}

}
