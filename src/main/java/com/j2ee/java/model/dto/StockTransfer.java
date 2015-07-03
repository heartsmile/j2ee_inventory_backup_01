/**
 * 
 */
package com.j2ee.java.model.dto;

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

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name = "stock_transfer")
public class StockTransfer {

	@Id
	@GeneratedValue
	@Column(name = "TransferID")
	private int transferID;

	@ManyToOne
	@JoinColumn(name = "StaffID")
	private Staff staffID;

	@ManyToOne
	@JoinColumn(name = "ProductID")
	private Product productID;

	@Column(name = "ExpectedDate")
	@Temporal(TemporalType.DATE)
	private Date expectedDate;

	@Column(name = "Quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "FromStockID")
	private Stock fromStock;

	@ManyToOne
	@JoinColumn(name = "ToStockID")
	private Stock toStock;

	@Column(name = "Priority")
	private int priority;
	
	@Column(name = "StatusID")
	private int statusID; 

	@Column(name = "Description")
	private String description;

	/**
	 * 
	 */
	public StockTransfer() {
		super();
	}

	/**
	 * @return the transferID
	 */
	public int getTransferID() {
		return transferID;
	}

	/**
	 * @param transferID
	 *            the transferID to set
	 */
	public void setTransferID(int transferID) {
		this.transferID = transferID;
	}

	/**
	 * @return the staffID
	 */
	public Staff getStaffID() {
		return staffID;
	}

	/**
	 * @param staffID
	 *            the staffID to set
	 */
	public void setStaffID(Staff staffID) {
		this.staffID = staffID;
	}

	/**
	 * @return the totalNumber
	 */
	public int getTotalNumber() {
		return quantity;
	}

	/**
	 * @param totalNumber
	 *            the totalNumber to set
	 */
	public void setTotalNumber(int totalNumber) {
		this.quantity = totalNumber;
	}

	public Product getProductID() {
		return productID;
	}

	public void setProductID(Product productID) {
		this.productID = productID;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Stock getFromStock() {
		return fromStock;
	}

	public void setFromStock(Stock fromStock) {
		this.fromStock = fromStock;
	}

	public Stock getToStock() {
		return toStock;
	}

	public void setToStock(Stock toStock) {
		this.toStock = toStock;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

}
