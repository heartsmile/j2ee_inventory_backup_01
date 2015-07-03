package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockOutward;

public interface StockOutwardDAO {

	public StockOutward getByID(int id);
	
	public List<StockOutward> getAllStockOutward();
	
	public boolean insertStockOutward(StockOutward sOutward);
	
	public boolean updateStockOutward(StockOutward sOutward);
	
	public boolean deleteStockOutward(StockOutward sOutward);
	
	public int getMaxStockOutID();
}
