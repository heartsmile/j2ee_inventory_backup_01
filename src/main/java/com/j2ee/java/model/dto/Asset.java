/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="asset")
public class Asset {
	
	@Id
	@GeneratedValue
	@Column(name="AssetID")
	private int assetID;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="OrgPrice")
	private BigDecimal orgPrice;
	
	@Column(name="Number")
	private int number;
	
	@OneToMany(mappedBy = "assetID")
	private transient Set<AssetAdjustment> assetAssetAdjustment = new HashSet<AssetAdjustment>();
	
	@OneToMany(mappedBy = "assetID")
	private transient Set<AssetLiquidationDetail> assetAssetLiquiDetail = new HashSet<AssetLiquidationDetail>();
	/**
	 * @param assetID
	 * @param name
	 * @param orgPrice
	 * @param number
	 */
	public Asset(int assetID, String name, BigDecimal orgPrice, int number) {
		super();
		this.assetID = assetID;
		this.name = name;
		this.orgPrice = orgPrice;
		this.number = number;
	}
	/**
	 * 
	 */
	public Asset() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the orgPrice
	 */
	public BigDecimal getOrgPrice() {
		return orgPrice;
	}
	/**
	 * @param orgPrice the orgPrice to set
	 */
	public void setOrgPrice(BigDecimal orgPrice) {
		this.orgPrice = orgPrice;
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
	public Set<AssetAdjustment> getAssetAssetAdjustment() {
		return assetAssetAdjustment;
	}
	
	public void setAssetAssetAdjustment(Set<AssetAdjustment> assetAssetAdjustment) {
		this.assetAssetAdjustment = assetAssetAdjustment;
	}
	
	public Set<AssetLiquidationDetail> getAssetAssetLiquiDetail() {
		return assetAssetLiquiDetail;
	}
	
	public void setAssetAssetLiquiDetail(
			Set<AssetLiquidationDetail> assetAssetLiquiDetail) {
		this.assetAssetLiquiDetail = assetAssetLiquiDetail;
	}

}
