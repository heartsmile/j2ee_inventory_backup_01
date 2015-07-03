package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.Stock;

public interface StockBO {

	public Stock getByID(int id);
	
	public List<Stock> getAllStock();
	
	public boolean insertStock(Stock stock);
	
	public boolean updateStock(Stock stock);
	
	public boolean deleteStock(Stock stock);
}
