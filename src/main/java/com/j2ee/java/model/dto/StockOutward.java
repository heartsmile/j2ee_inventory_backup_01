/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "stock_outward")
public class StockOutward {
	
	@Id
	@GeneratedValue
	@Column(name = "OutwardID")
	private int outwardID;

	@Column(name = "Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "Reason")
	private String reason;

	@Column(name = "Note")
	private String note;
	
    @ManyToOne
    @JoinColumn(name = "CustomerID") 
	private Customer customerID;

    @ManyToOne
    @JoinColumn(name = "StaffID")  
	private Staff staffID;

	@Column(name = "TotalQuantity")
	private int totalQuantity;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;
	
	@OneToMany(mappedBy = "outwardID")
	private transient Set<StockOutwardDetail> stockOutStockOutDetail = new HashSet<StockOutwardDetail>();
	/**
	 * 
	 */
	public StockOutward() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param outwardID
	 * @param date
	 * @param reason
	 * @param customerID
	 * @param staffID
	 * @param totalQuantity
	 * @param totalAmount
	 */
	public StockOutward(int outwardID, Date date, String reason, String note,
			Customer customerID, Staff staffID, int totalQuantity,
			BigDecimal totalAmount) {
		super();
		this.outwardID = outwardID;
		this.date = date;
		this.reason = reason;
		this.note = note;
		this.customerID = customerID;
		this.staffID = staffID;
		this.totalQuantity = totalQuantity;
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the outwardID
	 */
	public int getOutwardID() {
		return outwardID;
	}
	/**
	 * @param outwardID the outwardID to set
	 */
	public void setOutwardID(int outwardID) {
		this.outwardID = outwardID;
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
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the customerID
	 */
	public Customer getCustomerID() {
		return customerID;
	}
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}
	/**
	 * @return the staffID
	 */
	public Staff getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(Staff staffID) {
		this.staffID = staffID;
	}
	/**
	 * @return the totalQuantity
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}
	/**
	 * @param totalQuantity the totalQuantity to set
	 */
	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Set<StockOutwardDetail> getStockOutStockOutDetail() {
		return stockOutStockOutDetail;
	}
	
	public void setStockOutStockOutDetail(
			Set<StockOutwardDetail> stockOutStockOutDetail) {
		this.stockOutStockOutDetail = stockOutStockOutDetail;
	}
	
	
}
