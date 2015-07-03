package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.StockInwardDetail;

public interface StockInwardDetailBO {

	public StockInwardDetail getByID(int id);
	
	public List<StockInwardDetail> getAllStockInwardDetail();
	
	public boolean insertStockInwardDetail(StockInwardDetail stockInDetail);
	
	public boolean updateStockInwardDetail(StockInwardDetail stockInDetail);
	
	public boolean deleteStockInwardDetail(StockInwardDetail stockInDetail);
}
