/**
 * 
 */
package com.j2ee.java.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name="asset_report")
public class AssetReport {
	
	@Id
	@GeneratedValue
	@Column(name="AssetReportID")
	private int assetReportID;
	
	@Column(name="Date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="StaffID")
	private int staffID;
	
	@Column(name="Reason")
	private String reason;
	/**
	 * 
	 */
	public AssetReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param assetReportID
	 * @param date
	 * @param staffID
	 * @param reason
	 */
	public AssetReport(int assetReportID, Date date, int staffID, String reason) {
		super();
		this.assetReportID = assetReportID;
		this.date = date;
		this.staffID = staffID;
		this.reason = reason;
	}
	/**
	 * @return the assetReportID
	 */
	public int getAssetReportID() {
		return assetReportID;
	}
	/**
	 * @param assetReportID the assetReportID to set
	 */
	public void setAssetReportID(int assetReportID) {
		this.assetReportID = assetReportID;
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
	public int getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
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
}
