package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Stock;

public interface StockDAO {
	
	public Stock getByID(int id);
	
	public List<Stock> getAllStock();
	
	public boolean insertStock(Stock Stock);
	
	public boolean updateStock(Stock Stock);
	
	public boolean deleteStock(Stock Stock);
}
