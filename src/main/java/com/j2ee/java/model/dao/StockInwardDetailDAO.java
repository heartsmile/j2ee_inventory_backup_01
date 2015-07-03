package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockInwardDetail;

public interface StockInwardDetailDAO {

	public StockInwardDetail getByID(int id);
	
	public List<StockInwardDetail> getAllStockInwardDetail();
	
	public boolean insertStockInwardDetail(StockInwardDetail stockInDetail);
	
	public boolean updateStockInwardDetail(StockInwardDetail stockInDetail);
	
	public boolean deleteStockInwardDetail(StockInwardDetail stockInDetail);
}
