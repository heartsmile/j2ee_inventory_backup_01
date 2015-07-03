/**
 * 
 */
package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.StockTransfer;

/**
 * @author John Tran
 *
 */
public interface StockTransferBO {

	public int getLastestBillID();
	
	public int getPriorityID(String priority);

	public String getPriorityString(int ID);

	public int getStatusID(String status);

	public String getStatusString(int ID);

	public StockTransfer getByID(int id);

	public List<StockTransfer> getAllStockTransfer();

	public boolean insertStockTransfer(StockTransfer stockTransfer);

	public boolean updateStockTransfer(StockTransfer stockTransfer);

	public boolean deleteStockTransfer(StockTransfer stockTransfer);
	
	public boolean updateStockTransferStatus(int stockTransferID, int statusID);
}
