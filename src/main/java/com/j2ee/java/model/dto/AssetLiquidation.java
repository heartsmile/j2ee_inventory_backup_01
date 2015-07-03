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
@Table(name="asset_liquidation")
public class AssetLiquidation {
	
	@Id
	@GeneratedValue
	@Column(name="LiquidID")
	private int liquidID;
	
	@Column(name="Date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
    @ManyToOne
    @JoinColumn(name = "StaffID")  
	private Staff staffID;
	
	@Column(name="TotalNumber")
	private int totalNumber;
	
	@Column(name="TotalAmount")
	private BigDecimal totalAmount;
	
	@Column(name="Reason")
	private String reason;
	
	@OneToMany(mappedBy = "liquidID")
	private transient Set<AssetLiquidationDetail> assetliquiAssetLiquiDetail = new HashSet<AssetLiquidationDetail>();
	/**
	 * 
	 */
	public AssetLiquidation() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param liquidID
	 * @param date
	 * @param staff
	 * @param totalNumber
	 * @param totalAmount
	 * @param reason
	 */
	public AssetLiquidation(int liquidID, Date date, Staff staff,
			int totalNumber, BigDecimal totalAmount, String reason) {
		super();
		this.liquidID = liquidID;
		this.date = date;
		this.staffID = staff;
		this.totalNumber = totalNumber;
		this.totalAmount = totalAmount;
		this.reason = reason;
	}
	/**
	 * @return the liquidID
	 */
	public int getLiquidID() {
		return liquidID;
	}
	/**
	 * @param liquidID the liquidID to set
	 */
	public void setLiquidID(int liquidID) {
		this.liquidID = liquidID;
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
	 * @return the staffID
	 */
	public Staff getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(Staff staff) {
		this.staffID = staff;
	}
	/**
	 * @return the totalNumber
	 */
	public int getTotalNumber() {
		return totalNumber;
	}
	/**
	 * @param totalNumber the totalNumber to set
	 */
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
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
	public Set<AssetLiquidationDetail> getAssetliquiAssetLiquiDetail() {
		return assetliquiAssetLiquiDetail;
	}
	
	public void setAssetliquiAssetLiquiDetail(
			Set<AssetLiquidationDetail> assetliquiAssetLiquiDetail) {
		this.assetliquiAssetLiquiDetail = assetliquiAssetLiquiDetail;
	}
	
}
