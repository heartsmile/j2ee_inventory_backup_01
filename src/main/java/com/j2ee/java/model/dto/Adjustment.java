
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
@Table(name="adjustment")
public class Adjustment {
	
	@Id
	@GeneratedValue
	@Column(name="AdjustmentID")
	private int adjustmentID;
	
	@Column(name="Date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
    @ManyToOne
    @JoinColumn(name = "StockID")
    private Stock stockID;
	
	@Column(name="TotalDiffAmount")
	private BigDecimal totalDiffAmount;

    @ManyToOne
    @JoinColumn(name = "StaffID")
	private Staff staffID;
	
	@Column(name="TotalDiffQuantity")
	private int totalDiffQuantity;
	
	@OneToMany(mappedBy = "adjustmentID")
	private transient Set<AdjustmentDetail> adjustDetail = new HashSet<AdjustmentDetail>();
	/**
	 * 
	 */
	public Adjustment() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param date
	 * @param adjustID
	 * @param refType
	 * @param stock
	 * @param totalDiffAmount
	 * @param staffID
	 * @param accept
	 * @param active
	 */
	
	public int getAdjustmentID() {
		return adjustmentID;
	}
	public Adjustment(int adjustmentID, Date date, Stock stockID,
			BigDecimal totalDiffAmount, Staff staffID,
			int totalDiffQuantity) {
		super();
		this.adjustmentID = adjustmentID;
		this.date = date;
		this.stockID = stockID;
		this.totalDiffAmount = totalDiffAmount;
		this.staffID = staffID;
		this.totalDiffQuantity = totalDiffQuantity;
	}
	public void setAdjustmentID(int adjustmentID) {
		this.adjustmentID = adjustmentID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Stock getStockID() {
		return stockID;
	}
	public void setStockID(Stock stockID) {
		this.stockID = stockID;
	}
	public BigDecimal getTotalDiffAmount() {
		return totalDiffAmount;
	}
	public void setTotalDiffAmount(BigDecimal totalDiffAmount) {
		this.totalDiffAmount = totalDiffAmount;
	}
	public Staff getStaffID() {
		return staffID;
	}
	public void setStaffID(Staff staffID) {
		this.staffID = staffID;
	}
	public int getTotalDiffQuantity() {
		return totalDiffQuantity;
	}
	public void setTotalDiffQuantity(int totalDiffQuantity) {
		this.totalDiffQuantity = totalDiffQuantity;
	}
	public Set<AdjustmentDetail> getAdjustDetail() {
		return adjustDetail;
	}
	public void setAdjustDetail(Set<AdjustmentDetail> adjustDetail) {
		this.adjustDetail = adjustDetail;
	}
}
