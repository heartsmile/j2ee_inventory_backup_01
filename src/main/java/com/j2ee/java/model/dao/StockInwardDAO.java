package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockInward;

public interface StockInwardDAO {
	
	public StockInward getByID(int id);
	
	public List<StockInward> getAllStockInward();
	
	public boolean insertStockInward(StockInward stockInward);
	
	public boolean updateStockInward(StockInward stockInward);
	
	public boolean deleteStockInward(StockInward stockInward);
	
	public int getMaxStockInID();
}
