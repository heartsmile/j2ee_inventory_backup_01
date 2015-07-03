/**
 * 
 */
package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockInventory;

/**
 * @author John Tran
 *
 */
public interface StockInventoryDAO {

	public StockInventory getByID(int id);

	public List<Object[]> getAllStockInventory();

	public boolean insertStockInventory(StockInventory stockInventory);

	public boolean updateStockInventory(StockInventory stockInventory);

	public boolean deleteStockInventory(StockInventory stockInventory);
	
	public List<StockInventory> getStockInventoryByProductAndStock(int productID, int stockID);
	
	public int getCurrentQuantity(StockInventory sInventory);
	
	public List<Object[]> getListByStockID(int stockID);
}
