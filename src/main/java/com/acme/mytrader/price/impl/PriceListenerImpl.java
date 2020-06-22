package com.acme.mytrader.price.impl;

import org.jmock.auto.Auto;

import com.acme.mytrader.price.PriceListener;

public class PriceListenerImpl implements PriceListener{
	@Override
	public void priceUpdate(String security, double price) {
		PriceSourceImpl priceSourceImpl = new PriceSourceImpl();
		priceSourceImpl.addPriceListener(null);
		
	}

	

	
}
