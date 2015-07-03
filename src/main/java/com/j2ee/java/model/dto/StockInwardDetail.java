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
@Table(name = "stock_inward_detail")
public class StockInwardDetail {
	
	@Id
	@GeneratedValue
	@Column(name = "InwardDetailID")
	private int inwardDetailID;
	
    @ManyToOne
    @JoinColumn(name = "InwardID")  
	private StockInward inwardID;

    @ManyToOne
    @JoinColumn(name = "ProductID")  
	private Product productID;

    @ManyToOne
    @JoinColumn(name = "StockID")
	private Stock stockID;

	@Column(name = "Number")
	private int number;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Amount")
	private BigDecimal amount;
	/**
	 * 
	 */
	public StockInwardDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param inwardDetailID
	 * @param inwardID
	 * @param productID
	 * @param stockID
	 * @param number
	 * @param price
	 * @param amount
	 */
	public StockInwardDetail(int inwardDetailID, StockInward inwardID, Product productID,
			Stock stockID, int number, BigDecimal price, BigDecimal amount) {
		super();
		this.inwardDetailID = inwardDetailID;
		this.inwardID = inwardID;
		this.productID = productID;
		this.stockID = stockID;
		this.number = number;
		this.price = price;
		this.amount = amount;
	}
	/**
	 * @return the inwardDetailID
	 */
	public int getInwardDetailID() {
		return inwardDetailID;
	}
	/**
	 * @param inwardDetailID the inwardDetailID to set
	 */
	public void setInwardDetailID(int inwardDetailID) {
		this.inwardDetailID = inwardDetailID;
	}
	/**
	 * @return the inwardID
	 */
	public StockInward getInwardID() {
		return inwardID;
	}
	/**
	 * @param inwardID the inwardID to set
	 */
	public void setInwardID(StockInward inwardID) {
		this.inwardID = inwardID;
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
	 * @return the stockID
	 */
	public Stock getStockID() {
		return stockID;
	}
	/**
	 * @param stockID the stockID to set
	 */
	public void setStockID(Stock stockID) {
		this.stockID = stockID;
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
