/**
 * 
 */
package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.StockInventory;

/**
 * @author John Tran
 *
 */
public interface StockInventoryBO {
	
	public StockInventory getByID(int id);

	public List<Object[]> getAllStockInventory();

	public boolean insertStockInventory(StockInventory stockInventory);

	public boolean updateStockInventory(StockInventory stockInventory);

	public boolean deleteStockInventory(StockInventory stockInventory);
	
	public int checkAvailableOfProduct(StockInventory stockInventory);
	
	public int getCurrentQuantity(StockInventory sInventory);
	
	public List<Object[]> getListByStockID(int stockID);
}
