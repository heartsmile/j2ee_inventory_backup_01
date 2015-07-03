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
@Table(name="manufacture")
public class Manufacture {
	
	@Id
	@GeneratedValue
	@Column(name="manufactureID")
	private int manufactureID;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="tel")
	private String tel;
	
	@Column(name="description")
	private String description;
	
	@OneToMany(mappedBy = "manufactureID")
	private transient Set<Product> manufactureProduct = new HashSet<Product>();
	
	/**
	 * 
	 */
	public Manufacture() {
		super();
	}
	
	/**
	 * @param manufactureID
	 * @param name
	 * @param address
	 * @param tel
	 * @param description
	 */
	public Manufacture(String name, String address,
			String tel, String description) {
		super();
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.description = description;
	}

	/**
	 * @return the manufactureID
	 */
	public int getManufactureID() {
		return manufactureID;
	}
	/**
	 * @param manufactureID the manufactureID to set
	 */
	public void setManufactureID(int manufactureID) {
		this.manufactureID = manufactureID;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getManufactureProduct() {
		return manufactureProduct;
	}

	public void setManufactureProduct(Set<Product> manufactureProduct) {
		this.manufactureProduct = manufactureProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + manufactureID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
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
		Manufacture other = (Manufacture) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (manufactureID != other.manufactureID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
	
}
