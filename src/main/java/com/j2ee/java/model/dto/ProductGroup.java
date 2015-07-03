/**
 * 
 */
package com.j2ee.java.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author John Tran
 *
 */
@Component
@Entity
@Table(name="product_group")
public class ProductGroup {
	
	@Id
	@GeneratedValue
	@Column(name="groupID")
	private int groupID;
	
	@Column(name="groupName")
	private String groupName;
	
	@Column(name="description")
	private String description;
	/**
	 * 
	 */
	public ProductGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param groupID
	 * @param groupName
	 * @param description
	 */
	public ProductGroup(int groupID, String groupName, String description) {
		super();
		this.groupID = groupID;
		this.groupName = groupName;
		this.description = description;
	}
	/**
	 * @return the groupID
	 */
	public int getGroupID() {
		return groupID;
	}
	/**
	 * @param groupID the groupID to set
	 */
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
}
