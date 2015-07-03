package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Adjustment;

public interface AdjustmentDAO {
	
	public Adjustment getByID(int id);
	
	public List<Adjustment> getAllAdjustment();
	
	public boolean insertAdjustment(Adjustment adjustment);
	
	public boolean updateAdjustment(Adjustment adjustment);
	
	public boolean deleteAdjustment(Adjustment adjustment);
}
