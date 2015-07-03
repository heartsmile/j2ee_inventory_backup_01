/**
 * 
 */
package com.j2ee.java.model.dto;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name="ProductID")
	private int productID;
	
	@Column(name="ProductName")
	private String productName;
	
    @ManyToOne
    @JoinColumn(name = "TypeID")  
	private ProductType typeID;
	
    @ManyToOne
    @JoinColumn(name = "ProviderID")
	private Provider providerID;
	
    @ManyToOne
    @JoinColumn(name = "ManufacturerID")
	private Manufacture manufactureID;
	
    @ManyToOne
    @JoinColumn(name = "UnitID")
	private ProductUnit unitID;
	
	@Column(name="SalePrice")
	private BigDecimal salePrice;
	
	@Column(name="OrgPrice")
	private BigDecimal orgPrice;
	
	@Column(name="Description")
	private String description;

	@Column(name="MinStock")
	private int minStock;
	
	@Column(name="MaxStock")
	private int maxStock;

	@Column(name="Photo")
	@Lob
	private Blob photo;
	
	@OneToMany(mappedBy = "componentID")
	private transient Set<ProductComponent> productIDProductComponent = new HashSet<ProductComponent>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<ProductComponent> componentIDProductComponent = new HashSet<ProductComponent>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<StockInventory> productStockInventory = new HashSet<StockInventory>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<StockInwardDetail> productStockInwardDetail = new HashSet<StockInwardDetail>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<StockOutwardDetail> productStockOutwardDetail = new HashSet<StockOutwardDetail>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<StockTransfer> productStockTransfer = new HashSet<StockTransfer>();
	
	@OneToMany(mappedBy = "productID")
	private transient Set<AdjustmentDetail> proAdjustmentDetail = new HashSet<AdjustmentDetail>();
	/**
	 * 
	 */
	public Product() {
		super();
	}
	/**
	 * @param productID
	 * @param productName
	 * @param typeID
	 * @param providerID
	 * @param manufactureID
	 * @param unitID
	 * @param salePrice
	 * @param orgPrice
	 * @param description
	 * @param minStock
	 * @param maxStock
	 * @param photo
	 */
	public Product(String productName, ProductType typeID,
			Provider providerID, Manufacture manufactureID, ProductUnit unitID, BigDecimal salePrice,
			BigDecimal orgPrice, String description, int minStock, int maxStock,
			Blob photo) {
		super();
		this.productName = productName;
		this.typeID = typeID;
		this.providerID = providerID;
		this.manufactureID = manufactureID;
		this.unitID = unitID;
		this.salePrice = salePrice;
		this.orgPrice = orgPrice;
		this.description = description;
		this.minStock = minStock;
		this.maxStock = maxStock;
		this.photo = photo;
	}
	
	/**
	 * @return the productID
	 */
	public int getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(int productID) {
		this.productID = productID;
	}
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the typeID
	 */
	public ProductType getTypeID() {
		return typeID;
	}
	/**
	 * @param typeID the typeID to set
	 */
	public void setTypeID(ProductType typeID) {
		this.typeID = typeID;
	}
	/**
	 * @return the providerID
	 */
	public Provider getProviderID() {
		return providerID;
	}
	/**
	 * @param providerID the providerID to set
	 */
	public void setProviderID(Provider providerID) {
		this.providerID = providerID;
	}
	/**
	 * @return the manufactureID
	 */
	public Manufacture getManufactureID() {
		return manufactureID;
	}
	/**
	 * @param manufactureID the manufactureID to set
	 */
	public void setManufactureID(Manufacture manufactureID) {
		this.manufactureID = manufactureID;
	}
	/**
	 * @return the unitID
	 */
	public ProductUnit getUnitID() {
		return unitID;
	}
	/**
	 * @param unitID the unitID to set
	 */
	public void setUnitID(ProductUnit unitID) {
		this.unitID = unitID;
	}
	/**
	 * @return the salePrice
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * @return the orgPrice
	 */
	public BigDecimal getOrgPrice() {
		return orgPrice;
	}
	/**
	 * @param orgPrice the orgPrice to set
	 */
	public void setOrgPrice(BigDecimal orgPrice) {
		this.orgPrice = orgPrice;
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
	/**
	 * @return the minStock
	 */
	public int getMinStock() {
		return minStock;
	}
	/**
	 * @param minStock the minStock to set
	 */
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	/**
	 * @return the maxStock
	 */
	public int getMaxStock() {
		return maxStock;
	}
	/**
	 * @param maxStock the maxStock to set
	 */
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
	
	public Set<ProductComponent> getProductIDProductComponent() {
		return productIDProductComponent;
	}
	
	public void setProductIDProductComponent(
			Set<ProductComponent> productIDProductComponent) {
		this.productIDProductComponent = productIDProductComponent;
	}
	
	public Set<ProductComponent> getComponentIDProductComponent() {
		return componentIDProductComponent;
	}
	
	public void setComponentIDProductComponent(
			Set<ProductComponent> componentIDProductComponent) {
		this.componentIDProductComponent = componentIDProductComponent;
	}
	
	
	public Set<StockInventory> getProductStockInventory() {
		return productStockInventory;
	}
	
	public void setProductStockInventory(Set<StockInventory> productStockInventory) {
		this.productStockInventory = productStockInventory;
	}
	
	public Set<StockInwardDetail> getProductStockInwardDetail() {
		return productStockInwardDetail;
	}
	
	public void setProductStockInwardDetail(
			Set<StockInwardDetail> productStockInwardDetail) {
		this.productStockInwardDetail = productStockInwardDetail;
	}
	
	public Set<StockOutwardDetail> getProductStockOutwardDetail() {
		return productStockOutwardDetail;
	}
	
	public void setProductStockOutwardDetail(
			Set<StockOutwardDetail> productStockOutwardDetail) {
		this.productStockOutwardDetail = productStockOutwardDetail;
	}
	/**
	 * @return the productStockTransfer
	 */
	public Set<StockTransfer> getProductStockTransfer() {
		return productStockTransfer;
	}
	/**
	 * @param productStockTransfer the productStockTransfer to set
	 */
	public void setProductStockTransfer(Set<StockTransfer> productStockTransfer) {
		this.productStockTransfer = productStockTransfer;
	}
	
	public Set<AdjustmentDetail> getProAdjustmentDetail() {
		return proAdjustmentDetail;
	}
	public void setProAdjustmentDetail(Set<AdjustmentDetail> proAdjustmentDetail) {
		this.proAdjustmentDetail = proAdjustmentDetail;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

}
