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
@Table(name="stock_build")
public class StockBuild {
	
	@Id
	@GeneratedValue
	@Column(name="BuildID")
	private int buildID;
	
    @ManyToOne
    @JoinColumn(name = "ProductID")
	private Product productID;
	
    @ManyToOne
    @JoinColumn(name = "StaffID")
	private Staff staffID;
    
    @ManyToOne
    @JoinColumn(name = "StockID")
    private Stock stockID;
	
	@Column(name="TotalAmount")
	private BigDecimal totalAmount;
	
	@Column(name="TotalQuantity")
	private int totalQuantity;
	
	@Column(name="Reason")
	private String reason;
	
	@Column(name="BuildDate")
	@Temporal(TemporalType.DATE)
	private Date buildDate;
	
	@OneToMany(mappedBy = "buildID")
	private transient Set<StockBuildDetail> stockBuildStockBuilDetail = new HashSet<StockBuildDetail>();
	/**
	 * 
	 */
	public StockBuild() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param buildID
	 * @param productID
	 * @param refTypeID
	 * @param staffID
	 * @param totalAmount
	 * @param totalQuantity
	 * @param reason
	 * @param buildDate
	 */
	public StockBuild(int buildID, Product productID, Staff staffID,
			Stock stockID, BigDecimal totalAmount, int totalQuantity, 
			String reason, Date buildDate) {
		super();
		this.buildID = buildID;
		this.productID = productID;
		this.staffID = staffID;
		this.stockID = stockID;
		this.totalAmount = totalAmount;
		this.totalQuantity = totalQuantity;
		this.reason = reason;
		this.buildDate = buildDate;
	}
	/**
	 * @return the buildID
	 */
	public int getBuildID() {
		return buildID;
	}
	/**
	 * @param buildID the buildID to set
	 */
	public void setBuildID(int buildID) {
		this.buildID = buildID;
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
	
	public Stock getStockID() {
		return stockID;
	}
	public void setStockID(Stock stockID) {
		this.stockID = stockID;
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
	/**
	 * @return the buildDate
	 */
	public Date getBuildDate() {
		return buildDate;
	}
	/**
	 * @param buildDate the buildDate to set
	 */
	public void setBuildDate(Date buildDate) {
		this.buildDate = buildDate;
	}
	
	public Set<StockBuildDetail> getStockBuildStockBuilDetail() {
		return stockBuildStockBuilDetail;
	}
	
	public void setStockBuildStockBuilDetail(
			Set<StockBuildDetail> stockBuildStockBuilDetail) {
		this.stockBuildStockBuilDetail = stockBuildStockBuilDetail;
	}
	
}		
