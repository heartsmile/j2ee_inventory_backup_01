package com.j2ee.java.model.bo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dao.StockInventoryDAO;
import com.j2ee.java.model.dto.StockInventory;

@Component(value="StockInventoryBOImpl")
public class StockInventoryBOImpl implements StockInventoryBO {

	private static final Logger logger = LoggerFactory.getLogger(StockInventoryBOImpl.class);
	
	@Autowired
	@Qualifier("StockInventoryDAOImpl")
	private StockInventoryDAO stockInventoryDAO;
	
	@Override
	public StockInventory getByID(int id) {
		
		StockInventory stockInventory = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			stockInventory = stockInventoryDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return stockInventory;
	}

	@Override
	public List<Object[]> getAllStockInventory() {
		
		List<Object[]> listStockInventory = new ArrayList<Object[]>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockInventory = stockInventoryDAO.getAllStockInventory();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockInventory;
	}

	@Override
	public boolean insertStockInventory(StockInventory stockInventory) {
		
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInventoryDAO.insertStockInventory(stockInventory);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean updateStockInventory(StockInventory stockInventory) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInventoryDAO.updateStockInventory(stockInventory);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean deleteStockInventory(StockInventory stockInventory) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInventoryDAO.deleteStockInventory(stockInventory);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public int getCurrentQuantity(StockInventory sInventory) {
		int result = 0;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInventoryDAO.getCurrentQuantity(sInventory);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public int checkAvailableOfProduct(StockInventory stockInventory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Object[]> getListByStockID(int stockID) {
		List<Object[]> listStockInventory = new ArrayList<Object[]>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockInventory = stockInventoryDAO.getListByStockID(stockID);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockInventory;
	}

}
