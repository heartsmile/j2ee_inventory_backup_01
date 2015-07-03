package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.AdjustmentDetail;

public interface AdjustmentDetailDAO {

	public AdjustmentDetail getByID(int id);
	
	public List<AdjustmentDetail> getAllAdjustment();
	
	public boolean insertAdjustmentDetail(AdjustmentDetail adDetail);
	
	public boolean updateAdjustmentDetail(AdjustmentDetail adDetail);
	
	public boolean deleteAdjustmentDetail(AdjustmentDetail adDetail);
}
