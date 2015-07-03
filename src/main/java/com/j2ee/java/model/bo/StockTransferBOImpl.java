/**
 * 
 */
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
import com.j2ee.java.model.dao.StockTranferDAO;
import com.j2ee.java.model.dto.StockTransfer;

/**
 * @author John Tran
 *
 */
@Component(value="StTransferBOImpl")
public class StockTransferBOImpl implements StockTransferBO {

	private static final Logger logger = LoggerFactory.getLogger(StockTransferBOImpl.class);
	
	@Autowired
	@Qualifier("StTransferDAOImpl")
	private StockTranferDAO stockTransferDAO;
	
	@Override
	public StockTransfer getByID(int id) {
		
		StockTransfer stockTransfer = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			stockTransfer = stockTransferDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return stockTransfer;
	}
	
	@Override
	public List<StockTransfer> getAllStockTransfer() {

		List<StockTransfer> listStockTransfer = new ArrayList<StockTransfer>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockTransfer = stockTransferDAO.getAllStockTransfer();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockTransfer;
	}

	@Override
	public boolean insertStockTransfer(StockTransfer stockTransfer) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockTransferDAO.insertStockTransfer(stockTransfer);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateStockTransfer(StockTransfer stockTransfer) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockTransferDAO.updateStockTransfer(stockTransfer);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
				result = false;
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean deleteStockTransfer(StockTransfer stockTransfer) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockTransferDAO.deleteStockTransfer(stockTransfer);
			
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
	public int getPriorityID(String priority){
		
		int result = 0;
		if("Urgent".equals(priority)){
			result = 4;
		}
		if("High".equals(priority)){
			result = 3;
		}
		if("Normal".equals(priority)){
			result = 2;
		}
		if("Low".equals(priority)){
			result = 1;
		}
		
		return result;
	}

	@Override
	public String getPriorityString(int ID) {
		
		String result = "";
		if(ID == 4){
			result = "Urgent";
		}
		if(ID == 3){
			result = "High";
		}
		if(ID == 2){
			result = "Normal";
		}
		if(ID == 1){
			result = "Low";
		}
		
		return result;
	}

	@Override
	public int getStatusID(String status) {

		int result = 0;
		if("Done".equals(status)){
			result = 4;
		}
		if("Available".equals(status)){
			result = 3;
		}
		if("Waiting Available".equals(status)){
			result = 2;
		}
		if("New".equals(status)){
			result = 1;
		}
		
		return result;
	}

	@Override
	public String getStatusString(int ID) {
		String result = "";
		if(ID == 4){
			result = "Done";
		}
		if(ID == 3){
			result = "Available";
		}
		if(ID == 2){
			result = "Waiting Available";
		}
		if(ID == 1){
			result = "New";
		}
		
		return result;
	}

	@Override
	public int getLastestBillID() {

		int result = 0;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockTransferDAO.getLastestBillID();
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateStockTransferStatus(int stockTransferID, int statusID) {
		
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockTransfer stTransfer = stockTransferDAO.getByID(stockTransferID);
			stTransfer.setStatusID(statusID);
			stockTransferDAO.updateStockTransfer(stTransfer);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
				result = false;
			}
			logger.error("Error", ex);
		}
		return result;
	}

}
