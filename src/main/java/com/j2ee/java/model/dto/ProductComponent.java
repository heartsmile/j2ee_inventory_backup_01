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
@Table(name="product_component")
public class ProductComponent {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Integer id;
	
    @ManyToOne
    @JoinColumn(name = "ComponentID")  
	private Product componentID;
	
    @ManyToOne
    @JoinColumn(name = "ProductID")
	private Product productID;
	
	@Column(name="Quantity")
	private int quantity;
	
	@Column(name="UnitPrice")
	private BigDecimal unitPrice;
	
	@Column(name="Total")
	private BigDecimal total;
	/**
	 * 
	 */
	public ProductComponent() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param componentID
	 * @param productID
	 * @param quantity
	 * @param price
	 * @param amount
	 */
	public ProductComponent(Product componentID, Product productID,
			int quantity, BigDecimal unitPrice, BigDecimal amount) {
		super();
		this.componentID = componentID;
		this.productID = productID;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = amount;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param price the price to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getTotal() {
		return total;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	@Override
	public int hashCode() {
		if (id != null) {
	        return id.hashCode();
	    } else {
	        return super.hashCode();
	    }
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductComponent other = (ProductComponent) obj;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (componentID == null) {
			if (other.componentID != null)
				return false;
		} else if (!componentID.equals(other.componentID))
			return false;
		if (id != other.id)
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
}
