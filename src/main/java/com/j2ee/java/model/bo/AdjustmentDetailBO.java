package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.AdjustmentDetail;

public interface AdjustmentDetailBO {

	public AdjustmentDetail getByID(int id);
	
	public List<AdjustmentDetail> getAllAdjustment();
	
	public boolean insertAdjustmentDetail(AdjustmentDetail adDetail);
	
	public boolean updateAdjustmentDetail(AdjustmentDetail adDetail);
	
	public boolean deleteAdjustmentDetail(AdjustmentDetail adDetail);
}
