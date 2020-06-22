package com.acme.mytrader.price.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import com.acme.mytrader.pojo.Stock;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;

public class PriceSourceImpl implements PriceSource {
	LinkedHashMap<String, Stock> stocks;
	
	public PriceSourceImpl() {
		stocks = new LinkedHashMap<String, Stock>();
		stocks.put("IBM",new Stock("IBM", 40, 500));
		stocks.put("AAPL",new Stock("AAPL", 40, 500));
		stocks.put("SQ",new Stock("SQ", 40, 500));
		stocks.put("MSFT",new Stock("MSFT", 40, 500));
		stocks.put("TSLA",new Stock("TSLA", 40, 500));
	}
	
	public List<Stock> getSourceStocks() {
		return new ArrayList<Stock>(stocks.values());
	}

	public Stock getStockDetails(String security) {
		return stocks.get(security);
	}
	
	public void updateStockPrice(String security,Double price,Integer qty) {
		Stock order = stocks.get(security);
		if (price != null) {
			order.setStockPrice(price);
		}
		if (qty != null) {
			order.setStockQty(qty);
		}
		stocks.put(security,order);
	}
	
	public Stock getStockBySecurity(List<Stock> custOrdList,String stockName) {
		Stock stockorder = new Stock();
		PriceSourceImpl priceSourceImpl = new PriceSourceImpl();
		List<Stock> sourceOrderList = priceSourceImpl.getSourceStocks();
		for(Stock csOrdr:custOrdList) {
			if(csOrdr != null ) {
		sourceOrderList.stream().forEach(stock -> {
			if (stock !=null && csOrdr.getSecurity().equals(stockName)
					&& stock.getStockQty() >=1
					&& stock.getStockPrice() < 55){
				csOrdr.setSecurity(stock.getSecurity());
				csOrdr.setStockPrice(csOrdr.getStockPrice() + stock.getStockPrice());
				csOrdr.setStockQty(csOrdr.getStockQty() + stock.getStockQty());
			}else if(! csOrdr.getSecurity().equals(stockName) && stock.getStockQty() >=1
					&& stock.getStockPrice() < 55) {
				stockorder.setSecurity(stock.getSecurity());
				stockorder.setStockPrice(stock.getStockPrice());
				stockorder.setStockQty(stock.getStockQty());
			}

		});
		}
		}
		return stockorder;
		

	}

	public Stock getRandomStockPrice(List<Stock> stockorderList) {
		Random rand = new Random();
		int randomIndex = rand.nextInt(stockorderList.size());
		Stock ord = stockorderList.get(randomIndex);
		return ord;
	}

	@Override
	public void addPriceListener(PriceListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removePriceListener(PriceListener listener) {
		// TODO Auto-generated method stub

	}

}
