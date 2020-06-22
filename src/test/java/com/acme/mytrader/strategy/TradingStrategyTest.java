package com.acme.mytrader.strategy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.acme.mytrader.pojo.CustomerPortfolio;
import com.acme.mytrader.pojo.Stock;

public class TradingStrategyTest {
	CustomerPortfolio custPort = null;
	@Before
	public void setup() {
		custPort = new CustomerPortfolio();
		
	}
	
    @Test
    public void testWithBlankStocks() {
    	custPort.removeAllStocks();
    	List<Stock> orders = custPort.getCustomerStocks();
    	Integer stockSize = null;
    	if (orders != null && orders.size() > 0) stockSize = orders.size();
    	assertNull("Customer Portfolio is empty",stockSize);
    }
    
    @Test
    public void testWithStocks() {
    	List<Stock> orders = custPort.getCustomerStocks();
    	Integer stockSize = null;
    	if (orders != null && orders.size() > 0) stockSize = orders.size();
    	assertNotNull("Customer Portfolio is empty",stockSize);
    }
}
