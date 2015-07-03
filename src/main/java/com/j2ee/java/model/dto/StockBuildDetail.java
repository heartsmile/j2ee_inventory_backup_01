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
@Table(name="stock_build_detail")
public class StockBuildDetail {
	
	@Id
	@GeneratedValue
	@Column(name="BuildDetailID")
	private int buildDetailID;
	
    @ManyToOne
    @JoinColumn(name = "BuildID")  
	private StockBuild buildID;
	
    @ManyToOne
    @JoinColumn(name = "ComponentID")  
	private Product componentID;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="Price")
	private BigDecimal price;
	
	@Column(name="SubTotal")
	private BigDecimal subTotal;
	/**
	 * 
	 */
	public StockBuildDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param buildDetailID
	 * @param buildID
	 * @param componentID
	 * @param quantity
	 * @param price
	 * @param amount
	 */
	public StockBuildDetail(int buildDetailID, StockBuild buildID, Product componentID,
			int quantity, BigDecimal price, BigDecimal subTotal) {
		super();
		this.buildDetailID = buildDetailID;
		this.buildID = buildID;
		this.componentID = componentID;
		this.quantity = quantity;
		this.price = price;
		this.subTotal = subTotal;
	}
	/**
	 * @return the buildDetailID
	 */
	public int getBuildDetailID() {
		return buildDetailID;
	}
	/**
	 * @param buildDetailID the buildDetailID to set
	 */
	public void setBuildDetailID(int buildDetailID) {
		this.buildDetailID = buildDetailID;
	}
	/**
	 * @return the buildID
	 */
	public StockBuild getBuildID() {
		return buildID;
	}
	/**
	 * @param buildID the buildID to set
	 */
	public void setBuildID(StockBuild buildID) {
		this.buildID = buildID;
	}
	/**
	 * @return the componentID
	 */
	public Product getComponentID() {
		return componentID;
	}
	/**
	 * @param componentID the componentID to set
	 */
	public void setComponentID(Product componentID) {
		this.componentID = componentID;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	
}
