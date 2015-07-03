package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.StockOutwardDetail;

public interface StockOutwardDetailBO {

	public StockOutwardDetail getByID(int id);
	
	public List<StockOutwardDetail> getAllStockOutwardDetail();
	
	public boolean insertStockOutwardDetail(StockOutwardDetail sOutDetail);
	
	public boolean updateStockOutwardDetail(StockOutwardDetail sOutDetail);
	
	public boolean deleteStockOutwardDetail(StockOutwardDetail sOutDetail);
}
