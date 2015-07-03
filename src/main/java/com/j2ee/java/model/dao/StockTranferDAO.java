/**
 * 
 */
package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.StockTransfer;

/**
 * @author John Tran
 *
 */

public interface StockTranferDAO {
	
	public StockTransfer getByID(int id);
	
	public int getLastestBillID();

	public List<StockTransfer> getAllStockTransfer();

	public boolean insertStockTransfer(StockTransfer stockTransfer);

	public boolean updateStockTransfer(StockTransfer stockTransfer);

	public boolean deleteStockTransfer(StockTransfer stockTransfer);
}
