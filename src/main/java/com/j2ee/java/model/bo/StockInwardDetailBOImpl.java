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
import com.j2ee.java.model.dao.StockInwardDetailDAO;
import com.j2ee.java.model.dto.StockInwardDetail;

@Component(value = "StockInwardDetailBOImpl")
public class StockInwardDetailBOImpl implements StockInwardDetailBO {

	private static final Logger logger = LoggerFactory.getLogger(StockInwardDetailBOImpl.class);
	@Autowired
	@Qualifier("StockInwardDetailDAOImpl")
	private StockInwardDetailDAO stockInDetailDAO;

	@Override
	public StockInwardDetail getByID(int id) {

		StockInwardDetail StockInwardDetail = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockInwardDetail = stockInDetailDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return StockInwardDetail;
	}

	@Override
	public List<StockInwardDetail> getAllStockInwardDetail() {

		List<StockInwardDetail> listStockInwardDetail = new ArrayList<StockInwardDetail>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockInwardDetail = stockInDetailDAO.getAllStockInwardDetail();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockInwardDetail;
	}

	@Override
	public boolean insertStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInDetailDAO.insertStockInwardDetail(stockInDetail);

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
	public boolean updateStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInDetailDAO.updateStockInwardDetail(stockInDetail);

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
	public boolean deleteStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInDetailDAO.deleteStockInwardDetail(stockInDetail);

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

}
