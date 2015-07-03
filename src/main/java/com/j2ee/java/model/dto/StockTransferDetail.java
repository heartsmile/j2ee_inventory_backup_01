/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name = "stock_transfer_detail")
public class StockTransferDetail {

	@Id
	@GeneratedValue
	@Column(name = "StockTrDetailID")
	private int stockTrDetailID;

    @ManyToOne
    @JoinColumn(name = "ProductID")  
	private Product productID;

    @ManyToOne
    @JoinColumn(name = "FromStock")  
	private Stock fromStock;

    @ManyToOne
    @JoinColumn(name = "ToStock")  
	private Stock toStock;

	@Column(name = "Number")
	private int number;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Amount")
	private BigDecimal amount;
	/**
	 * 
	 */
	public StockTransferDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param stockTrDetailID
	 * @param productID
	 * @param fromStock
	 * @param toStock
	 * @param number
	 * @param price
	 * @param amount
	 */
	public StockTransferDetail(int stockTrDetailID, Product productID,
			Stock fromStock, Stock toStock, int number, BigDecimal price,
			BigDecimal amount) {
		super();
		this.stockTrDetailID = stockTrDetailID;
		this.productID = productID;
		this.fromStock = fromStock;
		this.toStock = toStock;
		this.number = number;
		this.price = price;
		this.amount = amount;
	}
	/**
	 * @return the stockTrDetailID
	 */
	public int getStockTrDetailID() {
		return stockTrDetailID;
	}
	/**
	 * @param stockTrDetailID the stockTrDetailID to set
	 */
	public void setStockTrDetailID(int stockTrDetailID) {
		this.stockTrDetailID = stockTrDetailID;
	}
	/**
	 * @return the productID
	 */
	public Product getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(Product productID) {
		this.productID = productID;
	}
	/**
	 * @return the fromStock
	 */
	public Stock getFromStock() {
		return fromStock;
	}
	/**
	 * @param fromStock the fromStock to set
	 */
	public void setFromStock(Stock fromStock) {
		this.fromStock = fromStock;
	}
	/**
	 * @return the toStock
	 */
	public Stock getToStock() {
		return toStock;
	}
	/**
	 * @param toStock the toStock to set
	 */
	public void setToStock(Stock toStock) {
		this.toStock = toStock;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
