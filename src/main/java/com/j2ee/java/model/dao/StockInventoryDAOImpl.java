package com.j2ee.java.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;

@Component(value="StockInventoryDAOImpl")
public class StockInventoryDAOImpl implements StockInventoryDAO{

	static Logger logger = Logger.getLogger(StockInventoryDAOImpl.class.getName());
	
	@Override
	public StockInventory getByID(int id) {
		
		return (StockInventory) HibernateUtil.getSessionFactory()
				.getCurrentSession().get(StockInventory.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllStockInventory() {
		Query query = HibernateUtil.getSessionFactory()
				.getCurrentSession().createSQLQuery("call getAllStockInventory()");
		@SuppressWarnings("rawtypes")
		List results = query.list();
		return results;
	}

	@Override
	public boolean insertStockInventory(StockInventory stockInventory) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(stockInventory);
			result = true;
		} catch (Exception e) {
			
			logger.info("Can't save StockInventory");
		}
		return result;
	}

	@Override
	public boolean updateStockInventory(StockInventory stockInventory) {

		boolean result = false;
		try {
			Query query = HibernateUtil.getSessionFactory().getCurrentSession()
					.createQuery("UPDATE StockInventory SET Quantity = :quantity" +
    				" WHERE ProductID = :productID" + 
					" AND StockID = :stockID");
			query.setParameter("quantity", stockInventory.getQuantity());
			query.setParameter("productID", stockInventory.getProductID());
			query.setParameter("stockID", stockInventory.getStockID());
			query.executeUpdate();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockInventory");
		}
		return result;
	}

	@Override
	public boolean deleteStockInventory(StockInventory stockInventory) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentQuantity(StockInventory sInventory) {
		int quantity = -1;
		Long value = null;
		Query query = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("SELECT SUM(s.quantity) AS quantity"
						+ " FROM StockInventory s "
						+ " WHERE s.productID = :productID "
						+ " AND s.stockID = :stockID"
						+ " GROUP BY s.productID, s.stockID");
		query.setParameter("productID", sInventory.getProductID());
		query.setParameter("stockID", sInventory.getStockID());
		@SuppressWarnings("rawtypes")
		List results = query.list();
		if (results.size() > 0) {
			value = (Long) results.get(0);
			quantity = Integer.valueOf(value.toString());
		}
		return quantity;
	}

	@Override
	public List<StockInventory> getStockInventoryByProductAndStock(
			int productID, int stockID) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListByStockID(int stockID) {
		List<Object[]> results = new ArrayList<Object[]>();
		Query query = HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("SELECT s.productID, SUM(s.quantity), s.price"
						+ " FROM StockInventory s "
						+ " WHERE s.stockID = :stockID"
						+ "	GROUP BY s.productID, s.stockID");
		Stock stock = new Stock();
		stock.setStockID(stockID);
		query.setParameter("stockID", stock);
		results = query.list();
		
		return results;
	}

}
