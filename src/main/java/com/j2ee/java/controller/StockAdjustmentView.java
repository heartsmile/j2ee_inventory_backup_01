package com.j2ee.java.controller;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockAdjustmentView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productID;
	
	private String productName;
	
	private int realQuantity;
	
	private int stockQuantity;
	
	private int differentQuantity;
	
	private BigDecimal price;
	
	private BigDecimal subTotal;
	
	public StockAdjustmentView() {
		
	}

	public StockAdjustmentView(int productID, String productName,
			int realQuantity, int stockQuantity, int differentQuantity,
			BigDecimal price, BigDecimal subTotal) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.realQuantity = realQuantity;
		this.stockQuantity = stockQuantity;
		this.differentQuantity = differentQuantity;
		this.price = price;
		this.subTotal = subTotal;
	}

	public int getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(int realQuantity) {
		this.realQuantity = realQuantity;
	}

	public int getDifferentQuantity() {
		return differentQuantity;
	}

	public void setDifferentQuantity(int differentQuantity) {
		this.differentQuantity = differentQuantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
