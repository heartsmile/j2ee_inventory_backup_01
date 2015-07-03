package com.j2ee.java.model.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue
	@Column(name = "StockID")
	private int stockID;

	@Column(name = "StockName")
	private String stockName;

	@Column(name = "Address")
	private String address;

	@Column(name = "Size")
	private int size;

	@ManyToOne
	@JoinColumn(name = "ManagerID")
	private Staff managerID;

	@Column(name = "IsActive")
	private boolean isActive;

	@Column(name = "Desciption")
	private String desciption;

	@OneToMany(mappedBy = "stockID")
	private transient Set<Adjustment> stockAdjustment = new HashSet<Adjustment>();

	@OneToMany(mappedBy = "stockID")
	private transient Set<StockInventory> stockStockInventory = new HashSet<StockInventory>();

	@OneToMany(mappedBy = "stockID")
	private transient Set<StockInwardDetail> stockStockInwardDetail = new HashSet<StockInwardDetail>();

	@OneToMany(mappedBy = "stockID")
	private transient Set<StockOutwardDetail> stockStockOutwardDetail = new HashSet<StockOutwardDetail>();

	@OneToMany(mappedBy = "fromStock")
	private transient Set<StockTransfer> stockStockTransferFrom = new HashSet<StockTransfer>();

	@OneToMany(mappedBy = "toStock")
	private transient Set<StockTransfer> stockStockTransferTo = new HashSet<StockTransfer>();

	/**
	 * 
	 */
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param stockID
	 * @param stockName
	 * @param address
	 * @param size
	 * @param managerid
	 * @param isActive
	 * @param desciption
	 */
	public Stock(int stockID, String stockName, String address, int size,
			Staff managerid, boolean isActive, String desciption) {
		super();
		this.stockID = stockID;
		this.stockName = stockName;
		this.address = address;
		this.size = size;
		this.managerID = managerid;
		this.isActive = isActive;
		this.desciption = desciption;
	}

	/**
	 * @return the stockID
	 */
	public int getStockID() {
		return stockID;
	}

	/**
	 * @param stockID
	 *            the stockID to set
	 */
	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	/**
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}

	/**
	 * @param stockName
	 *            the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the managerid
	 */
	public Staff getManagerID() {
		return managerID;
	}

	/**
	 * @param managerid
	 *            the managerid to set
	 */
	public void setManagerID(Staff managerid) {
		this.managerID = managerid;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the description
	 */
	public String getDesciption() {
		return desciption;
	}

	/**
	 * @param desciption
	 *            the description to set
	 */
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Set<Adjustment> getStockAdjustment() {
		return stockAdjustment;
	}

	public void setStockAdjustment(Set<Adjustment> stockAdjustment) {
		this.stockAdjustment = stockAdjustment;
	}

	public Set<StockInventory> getStockStockInventory() {
		return stockStockInventory;
	}

	public void setStockStockInventory(Set<StockInventory> stockStockInventory) {
		this.stockStockInventory = stockStockInventory;
	}

	public Set<StockInwardDetail> getStockStockInwardDetail() {
		return stockStockInwardDetail;
	}

	public void setStockStockInwardDetail(
			Set<StockInwardDetail> stockStockInwardDetail) {
		this.stockStockInwardDetail = stockStockInwardDetail;
	}

	public Set<StockOutwardDetail> getStockStockOutwardDetail() {
		return stockStockOutwardDetail;
	}

	public void setStockStockOutwardDetail(
			Set<StockOutwardDetail> stockStockOutwardDetail) {
		this.stockStockOutwardDetail = stockStockOutwardDetail;
	}

	public Set<StockTransfer> getStockStockTransferFrom() {
		return stockStockTransferFrom;
	}

	public void setStockStockTransferFrom(
			Set<StockTransfer> stockStockTransferFrom) {
		this.stockStockTransferFrom = stockStockTransferFrom;
	}

	public Set<StockTransfer> getStockStockTransferTo() {
		return stockStockTransferTo;
	}

	public void setStockStockTransferTo(Set<StockTransfer> stockStockTransferTo) {
		this.stockStockTransferTo = stockStockTransferTo;
	}

}
