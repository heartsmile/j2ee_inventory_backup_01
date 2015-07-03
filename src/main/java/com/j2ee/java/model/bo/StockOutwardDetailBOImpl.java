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
import com.j2ee.java.model.dao.StockOutwardDetailDAO;
import com.j2ee.java.model.dto.StockOutwardDetail;

@Component(value = "StockOutwardDetailBOImpl")
public class StockOutwardDetailBOImpl implements StockOutwardDetailBO {

	private static final Logger logger = LoggerFactory.getLogger(StockOutwardDetailBOImpl.class);
	@Autowired
	@Qualifier("StockOutwardDetailDAOImpl")
	private StockOutwardDetailDAO sOutDetailDAO;

	@Override
	public StockOutwardDetail getByID(int id) {

		StockOutwardDetail StockOutwardDetail = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockOutwardDetail = sOutDetailDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return StockOutwardDetail;
	}

	@Override
	public List<StockOutwardDetail> getAllStockOutwardDetail() {

		List<StockOutwardDetail> listStockOutwardDetail = new ArrayList<StockOutwardDetail>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockOutwardDetail = sOutDetailDAO.getAllStockOutwardDetail();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockOutwardDetail;
	}

	@Override
	public boolean insertStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sOutDetailDAO.insertStockOutwardDetail(sOutDetail);

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
	public boolean updateStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sOutDetailDAO.updateStockOutwardDetail(sOutDetail);

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
	public boolean deleteStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sOutDetailDAO.deleteStockOutwardDetail(sOutDetail);

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
