package com.j2ee.java.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="regulation_config")
public class RegulationInventory {
	
	@Id
	@GeneratedValue
	@Column(name="RegulationID")
	private int regulationID;
	
	@Column(name="KeyCfg")
	private String keyCfg;
	
	@Column(name="ValueCfg")
	private String valueCfg;
	/**
	 * 
	 */
	public RegulationInventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param regulationID
	 * @param keyCfg
	 * @param valueCfg
	 */
	public RegulationInventory(int regulationID, String keyCfg, String valueCfg) {
		super();
		this.regulationID = regulationID;
		this.keyCfg = keyCfg;
		this.valueCfg = valueCfg;
	}
	public int getRegulationID() {
		return regulationID;
	}
	public void setRegulationID(int regulationID) {
		this.regulationID = regulationID;
	}
	public String getKeyCfg() {
		return keyCfg;
	}
	public void setKeyCfg(String keyCfg) {
		this.keyCfg = keyCfg;
	}
	public String getValueCfg() {
		return valueCfg;
	}
	public void setValueCfg(String valueCfg) {
		this.valueCfg = valueCfg;
	}
	

}
