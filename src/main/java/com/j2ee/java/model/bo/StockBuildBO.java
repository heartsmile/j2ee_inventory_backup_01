package com.j2ee.java.model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.j2ee.java.model.dto.StockBuild;

public interface StockBuildBO {

	public StockBuild getByID(int id);
	
	public List<StockBuild> getAllStockBuild();
	
	public String insertStockBuild(HttpServletRequest request);
	
	public boolean updateStockBuild(StockBuild stockBuild);
	
	public boolean deleteStockBuild(StockBuild stockBuild);
}
