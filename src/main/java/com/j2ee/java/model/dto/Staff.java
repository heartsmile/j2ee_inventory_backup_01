package com.j2ee.java.model.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="staff")
public class Staff {
	
	@Id
	@GeneratedValue
	@Column(name="StaffID")
	private int staffID;
	
	@Column(name="StaffName")
	private String staffName;
	
	@Column(name="Birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="IndentifyNum")
	private int identifyNum;
	
	@Column(name="Tel")
	private String Tel;
	
	@Column(name="Address")
	private String address;
	
	@OneToMany(mappedBy = "managerID")
	private transient Set<Stock> staffStock = new HashSet<Stock>();
	
	@OneToMany(mappedBy = "staffID")
	private transient Set<Adjustment> staffAdjusment = new HashSet<Adjustment>();
	
	@OneToMany(mappedBy = "staffID")
	private transient Set<AssetLiquidation> staffAssetLiquidation = new HashSet<AssetLiquidation>();
	
	@OneToMany(mappedBy = "staffID")
	private transient Set<StockOutward> staffStockOuward = new HashSet<StockOutward>();
	/**
	 * 
	 */
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param staffID
	 * @param staffName
	 * @param birthday
	 * @param email
	 * @param identifyNum
	 * @param tel
	 * @param address
	 */
	public Staff(int staffID, String staffName, Date birthday, String email,
			int identifyNum, String tel, String address) {
		super();
		this.staffID = staffID;
		this.staffName = staffName;
		this.birthday = birthday;
		this.email = email;
		this.identifyNum = identifyNum;
		Tel = tel;
		this.address = address;
	}
	/**
	 * @return the staffID
	 */
	public int getStaffID() {
		return staffID;
	}
	/**
	 * @param staffID the staffID to set
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 * @return the identifyNum
	 */
	public int getIdentifyNum() {
		return identifyNum;
	}
	/**
	 * @param identifyNum the identifyNum to set
	 */
	public void setIdentifyNum(int identifyNum) {
		this.identifyNum = identifyNum;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return Tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		Tel = tel;
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
	
	public Set<Stock> getStaffStock() {
		return staffStock;
	}
	
	public void setStaffStock(Set<Stock> staffStock) {
		this.staffStock = staffStock;
	}
	
	public Set<Adjustment> getStaffAdjusment() {
		return staffAdjusment;
	}
	public void setStaffAdjusment(Set<Adjustment> staffAdjusment) {
		this.staffAdjusment = staffAdjusment;
	}
	public Set<AssetLiquidation> getStaffAssetLiquidation() {
		return staffAssetLiquidation;
	}
	public void setStaffAssetLiquidation(Set<AssetLiquidation> staffAssetLiquidation) {
		this.staffAssetLiquidation = staffAssetLiquidation;
	}
	public Set<StockOutward> getStaffStockOuward() {
		return staffStockOuward;
	}
	public void setStaffStockOuward(Set<StockOutward> staffStockOuward) {
		this.staffStockOuward = staffStockOuward;
	}
	
}
