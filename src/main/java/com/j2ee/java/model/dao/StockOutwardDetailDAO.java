package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockOutwardDetail;

public interface StockOutwardDetailDAO {

	public StockOutwardDetail getByID(int id);
	
	public List<StockOutwardDetail> getAllStockOutwardDetail();
	
	public boolean insertStockOutwardDetail(StockOutwardDetail sOutDetail);
	
	public boolean updateStockOutwardDetail(StockOutwardDetail sOutDetail);
	
	public boolean deleteStockOutwardDetail(StockOutwardDetail sOutDetail);
}
