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
@Table(name="provider")
public class Provider {
	
	@Id
	@GeneratedValue
	@Column(name="ProviderID")
	private int providerID;
	
	@Column(name="ProviderName")
	private String providerName;
	
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
	
	@OneToMany(mappedBy = "providerID")
	private transient Set<Product> providerProduct = new HashSet<Product>();
	
	@OneToMany(mappedBy = "providerID")
	private transient Set<StockInward> providerSInward = new HashSet<StockInward>();
	/**
	 * 
	 */
	public Provider() {
		super();
	}
	
	
	/**
	 * @param providerID
	 * @param name
	 * @param adrress
	 * @param tel
	 * @param email
	 * @param website
	 * @param description
	 */
	public Provider(String name, String adrress, String tel,
			String email, String website, String description) {
		super();
		this.providerName = name;
		this.address = adrress;
		this.tel = tel;
		this.email = email;
		this.website = website;
		this.description = description;
	}


	/**
	 * @return the providerID
	 */
	public int getProviderID() {
		return providerID;
	}
	/**
	 * @param providerID the providerID to set
	 */
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	/**
	 * @return the name
	 */
	public String getProviderName() {
		return providerName;
	}
	/**
	 * @param name the name to set
	 */
	public void setProviderName(String name) {
		this.providerName = name;
	}
	/**
	 * @return the adrress
	 */
	public String getAdrress() {
		return address;
	}
	/**
	 * @param adrress the adrress to set
	 */
	public void setAdrress(String adrress) {
		this.address = adrress;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
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

	public Set<Product> getProviderProduct() {
		return providerProduct;
	}

	public void setProviderProduct(Set<Product> providerProduct) {
		this.providerProduct = providerProduct;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Set<StockInward> getProviderSInward() {
		return providerSInward;
	}


	public void setProviderSInward(Set<StockInward> providerSInward) {
		this.providerSInward = providerSInward;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + providerID;
		result = prime * result
				+ ((providerName == null) ? 0 : providerName.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Provider other = (Provider) obj;
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (providerID != other.providerID)
			return false;
		if (providerName == null) {
			if (other.providerName != null)
				return false;
		} else if (!providerName.equals(other.providerName))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}
	
}
