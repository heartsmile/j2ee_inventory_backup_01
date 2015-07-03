/**
 * 
 */
package com.j2ee.java.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="contract")
public class Contract {
	
	@Id
	@GeneratedValue
	@Column(name="ContractID")
	private int contractID;
	
	@Column(name="Customer")
	private String customer;
	
	@Column(name="Date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="ContractNumber")
	private int contractNumber;
	/**
	 * 
	 */
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param contractID
	 * @param customer
	 * @param date
	 * @param contractNumber
	 */
	public Contract(int contractID, String customer, Date date,
			int contractNumber) {
		super();
		this.contractID = contractID;
		this.customer = customer;
		this.date = date;
		this.contractNumber = contractNumber;
	}
	/**
	 * @return the contractID
	 */
	public int getContractID() {
		return contractID;
	}
	/**
	 * @param contractID the contractID to set
	 */
	public void setContractID(int contractID) {
		this.contractID = contractID;
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
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the contractNumber
	 */
	public int getContractNumber() {
		return contractNumber;
	}
	/**
	 * @param contractNumber the contractNumber to set
	 */
	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}
}
