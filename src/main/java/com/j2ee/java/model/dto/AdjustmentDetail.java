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

@Component
@Entity
@Table(name="adjustment_detail")
public class AdjustmentDetail {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
    @ManyToOne
    @JoinColumn(name = "AdjustmentID")
	private Adjustment adjustmentID;
    
    @ManyToOne
    @JoinColumn(name = "ProductID")
	private Product productID;
    
	@Column(name="UnitPrice")
	private BigDecimal unitPrice;
	
	@Column(name="SubTotal")
	private BigDecimal subTotal;
	
	@Column(name="StockQuantity")
	private int stockQuantity;
	
	@Column(name="DifferentQuantity")
	private int differentQuantity;
	
	@Column(name="RealQuantity")
	private int realQuantity;
	
	public AdjustmentDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdjustmentDetail(int id, Adjustment adjustmentID, Product productID,
			BigDecimal unitPrice, BigDecimal subTotal,
			int stockQuantity, int differentQuantity,
			int realQuantity) {
		super();
		this.id = id;
		this.adjustmentID = adjustmentID;
		this.productID = productID;
		this.unitPrice = unitPrice;
		this.subTotal = subTotal;
		this.stockQuantity = stockQuantity;
		this.differentQuantity = differentQuantity;
		this.realQuantity = realQuantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adjustment getAdjustmentID() {
		return adjustmentID;
	}

	public void setAdjustmentID(Adjustment adjustmentID) {
		this.adjustmentID = adjustmentID;
	}

	public Product getProductID() {
		return productID;
	}

	public void setProductID(Product productID) {
		this.productID = productID;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public int getDifferentQuantity() {
		return differentQuantity;
	}

	public void setDifferentQuantity(int differentQuantity) {
		this.differentQuantity = differentQuantity;
	}

	public int getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(int realQuantity) {
		this.realQuantity = realQuantity;
	}
	
}
