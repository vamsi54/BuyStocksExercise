package com.acme.mytrader.pojo;
public class Stock {
	private String  security;
	private double  stockPrice;
	private int stockQty;
	boolean orderStatus;



	
	public Stock(String security, double stockPrice, int stockQty, boolean orderStatus) {
		super();
		this.security = security;
		this.stockPrice = stockPrice;
		this.stockQty = stockQty;
		this.orderStatus = orderStatus;
	}



	public String getSecurity() {
		return security;
	}



	public void setSecurity(String security) {
		this.security = security;
	}



	public double getStockPrice() {
		return stockPrice;
	}



	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}



	public int getStockQty() {
		return stockQty;
	}



	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}



	public boolean isOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}




	

	public Stock(String security, double stockPrice, int stockQty) {
		this.security = security;
		this.stockPrice = stockPrice;
		this.stockQty = stockQty;
	}


	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [Security=" + security + ", StockPrice=" + stockPrice + ", StockQty=" + stockQty + "]";
	}
	
	
	
}
