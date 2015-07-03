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
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="CustomerID")
	private int customerID;
	
	@Column(name="CustomerName")
	private String customerName;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Tel")
	private String tel;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Website")
	private String website;
	
	@Column(name="Description")
	private String description;
	
	@OneToMany(mappedBy = "CustomerID")
	private transient Set<StockOutward> customerStockOut = new HashSet<StockOutward>();
	/**
	 * 
	 */
	public Customer() {
		super();
	}
	
	public Customer(int customerID, String customerName, String address,
			String tel, String email, String website, String description) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.website = website;
		this.description = description;
	}

	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<StockOutward> getCustomerStockOut() {
		return customerStockOut;
	}
	public void setCustomerStockOut(Set<StockOutward> customerStockOut) {
		this.customerStockOut = customerStockOut;
	}
	
	
}
