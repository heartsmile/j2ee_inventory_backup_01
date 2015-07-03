/**
 * 
 */
package com.j2ee.java.model.dto;

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
@Table(name="product_type")
public class ProductType {
	
	@Id
	@GeneratedValue
	@Column(name="typeID")
	private int typeID;
	
	@Column(name="TypeName")
	private String typeName;
	
	@OneToMany(mappedBy = "typeID")
	private transient Set<Product> productTypeProduct = new HashSet<Product>();
	/**
	 * 
	 */
	public ProductType() {
		super();
	}
	
	/**
	 * @param typeID
	 * @param name
	 */
	public ProductType(String typeName) {
		super();
		this.typeName = typeName;
	}
	
	/**
	 * @return the typeID
	 */
	public int getTypeID() {
		return typeID;
	}
	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	/**
	 * @return the name
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param name the name to set
	 */
	public void setTypeName(String name) {
		this.typeName = name;
	}

	public Set<Product> getProductTypeProduct() {
		return productTypeProduct;
	}

	public void setProductTypeProduct(Set<Product> productTypeProduct) {
		this.productTypeProduct = productTypeProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + typeID;
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		if (typeID != other.typeID)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		return true;
	}

}
