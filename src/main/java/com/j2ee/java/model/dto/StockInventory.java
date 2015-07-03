/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.j2ee.java.model.bo.Utils;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="stock_inventory")
public class StockInventory {
	
	@Id
	@GeneratedValue
	@Column(name="InventoryID")
	private int inventoryID;
	
	@Column(name="Date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
    @ManyToOne
    @JoinColumn(name = "ProductID")  
	private Product productID;
	
    @ManyToOne
    @JoinColumn(name = "StockID") 
	private Stock stockID;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="Price")
	private BigDecimal price;
	
	@Column(name="Amount")
	private BigDecimal amount;
	
	/**
	 * 
	 */
	public StockInventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param inventoryID
	 * @param refID
	 * @param date
	 * @param productID
	 * @param stockID
	 * @param quantity
	 * @param price
	 * @param amount
	 */
	public StockInventory(int inventoryID, Date date, Product productID,
			Stock stockID, int quantity, BigDecimal price, BigDecimal amount) {
		super();
		this.inventoryID = inventoryID;
		this.date = date;
		this.productID = productID;
		this.stockID = stockID;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	/**
	 * @return the inventoryID
	 */
	public int getInventoryID() {
		return inventoryID;
	}
	/**
	 * @param inventoryID the inventoryID to set
	 */
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public String toStringDateWeb(){
		return Utils.DATE_FORMATTER.format(date);
	}
}
