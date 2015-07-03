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
import com.j2ee.java.model.dao.StockBuildDetailDAO;
import com.j2ee.java.model.dto.StockBuildDetail;

@Component(value="StockBuildDetailBOImpl")
public class StockBuildDetailBOImpl implements StockBuildDetailBO {

	private static final Logger logger = LoggerFactory.getLogger(StockBuildDetailBOImpl.class);
	
	@Autowired
	@Qualifier("StockBuildDetailDAOImpl")
	private StockBuildDetailDAO sBuildDetailDAO;
	
	@Override
	public StockBuildDetail getByID(int id) {
		StockBuildDetail sBuildDetail = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			sBuildDetail = sBuildDetailDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return sBuildDetail;
	}

	@Override
	public List<StockBuildDetail> getAllStockBuildDetail() {
		List<StockBuildDetail> listSBuildDetail = new ArrayList<StockBuildDetail>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listSBuildDetail = sBuildDetailDAO.getAllStockBuildDetail();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listSBuildDetail;
	}

	@Override
	public boolean insertStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sBuildDetailDAO.insertStockBuildDetail(sBuildDetail);
			
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
	public boolean updateStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sBuildDetailDAO.updateStockBuildDetail(sBuildDetail);
			
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
	public boolean deleteStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = sBuildDetailDAO.deleteStockBuildDetail(sBuildDetail);
			
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
