package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockBuildDetail;

public interface StockBuildDetailDAO {

	public StockBuildDetail getByID(int id);
	
	public List<StockBuildDetail> getAllStockBuildDetail();
	
	public boolean insertStockBuildDetail(StockBuildDetail sBuildDetail);
	
	public boolean updateStockBuildDetail(StockBuildDetail sBuildDetail);
	
	public boolean deleteStockBuildDetail(StockBuildDetail sBuildDetail);
}
