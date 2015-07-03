/**
 * 
 */
package com.j2ee.java.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="asset_report_detail")
public class AssetReportDetail {
	
	@Id
	@GeneratedValue
	@Column(name="AssetReportDetailID")
	private int assetReportDetailID;
	
	@Column(name="AssetReportID")
	private int assetReportID;
	
	@Column(name="AssetID")
	private int assetID;
	
	@Column(name="Number")
	private int number;
	
	@Column(name="UseState")
	private String useState;
	/**
	 * 
	 */
	public AssetReportDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param assetReportDetailID
	 * @param assetReportID
	 * @param assetID
	 * @param number
	 * @param useState
	 */
	public AssetReportDetail(int assetReportDetailID, int assetReportID,
			int assetID, int number, String useState) {
		super();
		this.assetReportDetailID = assetReportDetailID;
		this.assetReportID = assetReportID;
		this.assetID = assetID;
		this.number = number;
		this.useState = useState;
	}
	/**
	 * @return the assetReportDetailID
	 */
	public int getAssetReportDetailID() {
		return assetReportDetailID;
	}
	/**
	 * @param assetReportDetailID the assetReportDetailID to set
	 */
	public void setAssetReportDetailID(int assetReportDetailID) {
		this.assetReportDetailID = assetReportDetailID;
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
	 * @return the assetID
	 */
	public int getAssetID() {
		return assetID;
	}
	/**
	 * @param assetID the assetID to set
	 */
	public void setAssetID(int assetID) {
		this.assetID = assetID;
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
	 * @return the useState
	 */
	public String getUseState() {
		return useState;
	}
	/**
	 * @param useState the useState to set
	 */
	public void setUseState(String useState) {
		this.useState = useState;
	}
}
