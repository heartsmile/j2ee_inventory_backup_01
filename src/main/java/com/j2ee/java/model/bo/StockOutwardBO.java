package com.j2ee.java.model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.j2ee.java.model.dto.StockOutward;

public interface StockOutwardBO {

	public StockOutward getByID(int id);
	
	public List<StockOutward> getAllStockOutward();
	
	public String insertStockOutward(HttpServletRequest request);
	
	public boolean updateStockOutward(StockOutward sOutward);
	
	public boolean deleteStockOutward(StockOutward sOutward);
	
	public int getMaxStockOutID();
}
