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
@Table(name = "stock_outward_detail")
public class StockOutwardDetail {
	
	@Id
	@GeneratedValue
	@Column(name = "OutwardDetailID")
	private int outwardDetailID;

    @ManyToOne
    @JoinColumn(name = "OutwardID")  
	private StockOutward outwardID;

    @ManyToOne
    @JoinColumn(name = "StockID")  
	private Stock stockID;

    @ManyToOne
    @JoinColumn(name = "ProductID")  
	private Product productID;

	@Column(name = "Number")
	private int number;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "Amount")
	private BigDecimal amount;
	/**
	 * 
	 */
	public StockOutwardDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param outwardDetailID
	 * @param outwardID
	 * @param stockID
	 * @param productID
	 * @param number
	 * @param price
	 * @param amount
	 */
	public StockOutwardDetail(int outwardDetailID, StockOutward outwardID, Stock stockID,
			Product productID, int number, BigDecimal price, BigDecimal amount) {
		super();
		this.outwardDetailID = outwardDetailID;
		this.outwardID = outwardID;
		this.stockID = stockID;
		this.productID = productID;
		this.number = number;
		this.price = price;
		this.amount = amount;
	}
	/**
	 * @return the outwardDetailID
	 */
	public int getOutwardDetailID() {
		return outwardDetailID;
	}
	/**
	 * @param outwardDetailID the outwardDetailID to set
	 */
	public void setOutwardDetailID(int outwardDetailID) {
		this.outwardDetailID = outwardDetailID;
	}
	/**
	 * @return the outwardID
	 */
	public StockOutward getOutwardID() {
		return outwardID;
	}
	/**
	 * @param outwardID the outwardID to set
	 */
	public void setOutwardID(StockOutward outwardID) {
		this.outwardID = outwardID;
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
