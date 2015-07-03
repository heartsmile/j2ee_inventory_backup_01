package com.j2ee.java.model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.j2ee.java.model.dto.Adjustment;

public interface AdjustmentBO {

	public Adjustment getByID(int id);
	
	public List<Adjustment> getAllAdjustment();
	
	public String insertAdjustment(HttpServletRequest request);
	
	public boolean updateAdjustment(Adjustment Adjustment);
	
	public boolean deleteAdjustment(Adjustment Adjustment);
}
