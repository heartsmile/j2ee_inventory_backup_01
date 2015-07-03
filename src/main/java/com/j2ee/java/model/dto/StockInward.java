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

@Component
@Entity
@Table(name = "stock_inward")
public class StockInward {

	@Id
	@GeneratedValue
	@Column(name = "InwardID")
	private int inwardID;

    @ManyToOne
    @JoinColumn(name = "ProviderID")  
	private Provider providerID;
	
    @ManyToOne
    @JoinColumn(name = "StaffID")  
	private Staff staffID;

	@Column(name = "Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "Reason")
	private String reason;

	@Column(name = "TotalAmount")
	private BigDecimal totalAmount;

	@Column(name = "TotalNumber")
	private int totalNumber;
	
	@Column(name = "Note")
	private String note;

	@OneToMany(mappedBy = "inwardID")
	private transient Set<StockInwardDetail> stockInStockInDetail = new HashSet<StockInwardDetail>();
	/**
	 * 
	 */
	public StockInward() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param inwardID
	 * @param providerID
	 * @param staffID
	 * @param date
	 * @param reason
	 * @param totalAmount
	 * @param totalNumber
	 */
	public StockInward(int inwardID, Provider providerID, Staff staffID,
			Date date, String reason, BigDecimal totalAmount, int totalNumber,
			String note, Set<StockInwardDetail> stockInStockInDetail) {
		super();
		this.inwardID = inwardID;
		this.providerID = providerID;
		this.staffID = staffID;
		this.date = date;
		this.reason = reason;
		this.totalAmount = totalAmount;
		this.totalNumber = totalNumber;
		this.note = note;
		this.stockInStockInDetail = stockInStockInDetail;
	}

	/**
	 * @return the inwardID
	 */
	public int getInwardID() {
		return inwardID;
	}

	/**
	 * @param inwardID
	 *            the inwardID to set
	 */
	public void setInwardID(int inwardID) {
		this.inwardID = inwardID;
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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
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
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount
	 *            the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the totalNumber
	 */
	public int getTotalNumber() {
		return totalNumber;
	}

	/**
	 * @param totalNumber
	 *            the totalNumber to set
	 */
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Set<StockInwardDetail> getStockInStockInDetail() {
		return stockInStockInDetail;
	}

	public void setStockInStockInDetail(Set<StockInwardDetail> stockInStockInDetail) {
		this.stockInStockInDetail = stockInStockInDetail;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Provider getProviderID() {
		return providerID;
	}

	public void setProviderID(Provider providerID) {
		this.providerID = providerID;
	}
}
