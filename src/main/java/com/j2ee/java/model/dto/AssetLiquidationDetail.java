/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="asset_liquidation_detail")
public class AssetLiquidationDetail {
	
	@Id
	@GeneratedValue
	@Column(name="LiDetailID")
	private int liDetailID;
	
    @ManyToOne
    @JoinColumn(name = "LiquidID")  
	private AssetLiquidation liquidID;
	
    @ManyToOne
    @JoinColumn(name = "AssetID")
	private Asset assetID;
	
	@Column(name="Number")
	private int number;
	
	@Column(name="Price")
	private BigDecimal price;
	
	@Column(name="Amount")
	private BigDecimal amount;
	
	@Column(name="Customer")
	private String customer;
	/**
	 * 
	 */
	public AssetLiquidationDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param liDetailID
	 * @param liquidID
	 * @param assetID
	 * @param number
	 * @param price
	 * @param amount
	 * @param customer
	 */
	public AssetLiquidationDetail(int liDetailID, AssetLiquidation liquidID, Asset assetID,
			int number, BigDecimal price, BigDecimal amount, String customer) {
		super();
		this.liDetailID = liDetailID;
		this.liquidID = liquidID;
		this.assetID = assetID;
		this.number = number;
		this.price = price;
		this.amount = amount;
		this.customer = customer;
	}
	/**
	 * @return the liDetailID
	 */
	public int getLiDetailID() {
		return liDetailID;
	}
	/**
	 * @param liDetailID the liDetailID to set
	 */
	public void setLiDetailID(int liDetailID) {
		this.liDetailID = liDetailID;
	}
	/**
	 * @return the liquidID
	 */
	public AssetLiquidation getLiquidID() {
		return liquidID;
	}
	/**
	 * @param liquidID the liquidID to set
	 */
	public void setLiquidID(AssetLiquidation liquidID) {
		this.liquidID = liquidID;
	}
	/**
	 * @return the assetID
	 */
	public Asset getAssetID() {
		return assetID;
	}
	/**
	 * @param assetID the assetID to set
	 */
	public void setAssetID(Asset assetID) {
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
	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
