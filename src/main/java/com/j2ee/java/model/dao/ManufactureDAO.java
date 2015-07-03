package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Manufacture;

public interface ManufactureDAO {
	
	public Manufacture getByID(int id);
	
	public List<Manufacture> getAllManufacture();
	
	public boolean insertManufacture(Manufacture manufacture);
	
	public boolean updateManufacture(Manufacture manufacture);
	
	public boolean deleteManufacture(Manufacture manufacture);
}
