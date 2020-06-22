package com.acme.mytrader.execution;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.acme.mytrader.pojo.CustomerPortfolio;
import com.acme.mytrader.pojo.Stock;
import com.acme.mytrader.price.impl.PriceSourceImpl;


public class ExecutionServiceImpl implements ExecutionService {
	static String[] setOfSymbols = new String[] { "IBM", "AAPL", "SQ", "MSFT", "TSLA" };
	static int[] percentageDiff = new int[] { -10, -5, -3, -2, -1, 0, 1, 2, 3, 5, 10 };
	static PriceSourceImpl priceSourceImpl = new PriceSourceImpl();
	static CustomerPortfolio customerPortfolio = new CustomerPortfolio();

	public static void main(String[] args) {
		ExecutionServiceImpl executionServiceImpl = new ExecutionServiceImpl();
		List<Stock> custOrdList = customerPortfolio.getCustomerStocks();
		
		System.out.println("Customer Stock Info \n:" + customerPortfolio.getCustomerStocks());

		int simulationTimeInMinutes = 2;

		for (int i = 0; i < simulationTimeInMinutes * 4; i++) {
			final double random = Math.random();

			long randomPercentage = Math.round(random * 100);

			int index = (int) randomPercentage / 10;
			int stockIndex = index % setOfSymbols.length;
			int priceChangeIndex = index % percentageDiff.length;
			Stock stock = priceSourceImpl.getStockDetails(setOfSymbols[stockIndex]);
			double tradedPrice = stock.getStockPrice() + (stock.getStockPrice() * percentageDiff[priceChangeIndex] / 100);
			
			priceSourceImpl.updateStockPrice(setOfSymbols[stockIndex], tradedPrice,null);
			System.out.println("Stock Exchange: " + priceSourceImpl.getSourceStocks());
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		List<Stock> srcOrdList = priceSourceImpl.getSourceStocks();
		executionServiceImpl.checkSourceAndBuy(custOrdList,srcOrdList,"BUY");
		System.out.println("Stock Exchange: " + priceSourceImpl.getSourceStocks());
		System.out.println("Customer Profile: " + customerPortfolio.getCustomerStocks());
	}

	private void checkSourceAndBuy(List<Stock> cstOrdList, List<Stock> srcOrdList, String orderType) {
		
		for (Stock cstOrd : cstOrdList) {
			if (cstOrd != null && orderType.equals("BUY") && !cstOrd.isOrderStatus()) {
				Optional<Stock> srcord = srcOrdList.stream()
						.filter(srcord1 -> srcord1 != null && srcord1.getStockPrice() < 40
								&& srcord1.getStockQty() >= 100 && srcord1.getSecurity().equals(cstOrd.getSecurity()))
						.findAny();

				if (srcord.isPresent()) {
					cstOrd.setStockPrice(srcord.get().getStockPrice());
					cstOrd.setStockQty(cstOrd.getStockQty() + 10);
					cstOrd.setOrderStatus(true);
					System.out.println("Cust stkprice   " + cstOrd.getStockPrice());
					System.out.println("Cust stkQty   " + cstOrd.getStockQty());
					customerPortfolio.updateStockPrice(cstOrd.getSecurity(), cstOrd.getStockPrice(),cstOrd.getStockQty());
					priceSourceImpl.updateStockPrice(cstOrd.getSecurity(), null,(srcord.get().getStockQty()-cstOrd.getStockQty()));
				}
			}
		}
	}

	@Override
	public void buy(String security, double price, int volume) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sell(String security, double price, int volume) {
		// TODO Auto-generated method stub

	}
}
