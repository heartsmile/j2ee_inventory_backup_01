package com.j2ee.java.model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.j2ee.java.model.dto.StockInward;

public interface StockInwardBO {

	public StockInward getByID(int id);

	public List<StockInward> getAllStockInward();

	public String insertStockInward(HttpServletRequest request);

	public boolean updateStockInward(StockInward stockInward);

	public boolean deleteStockInward(StockInward stockInward);
	
	public int getMaxStockInID();
}
