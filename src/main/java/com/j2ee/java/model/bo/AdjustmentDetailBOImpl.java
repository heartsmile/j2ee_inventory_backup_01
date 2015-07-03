package com.j2ee.java.model.bo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dao.AdjustmentDetailDAO;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dto.AdjustmentDetail;
@Component(value="AdjustmentDetailBOImpl")
public class AdjustmentDetailBOImpl implements AdjustmentDetailBO {

	private static final Logger logger = LoggerFactory.getLogger(AdjustmentDetailBOImpl.class);
	@Autowired
	@Qualifier("AdjustmentDetailDAOImpl")
	private AdjustmentDetailDAO adjustmentDAO;
	
	@Override
	public AdjustmentDetail getByID(int id) {
		AdjustmentDetail Adjustment = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			Adjustment = adjustmentDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return Adjustment;
	}

	@Override
	public List<AdjustmentDetail> getAllAdjustment() {
		List<AdjustmentDetail> listAdjustment = new ArrayList<AdjustmentDetail>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listAdjustment = adjustmentDAO.getAllAdjustment();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listAdjustment;
	}

	@Override
	public boolean insertAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = adjustmentDAO.insertAdjustmentDetail(adDetail);
			
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
	public boolean updateAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = adjustmentDAO.updateAdjustmentDetail(adDetail);
			
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
	public boolean deleteAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = adjustmentDAO.deleteAdjustmentDetail(adDetail);
			
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
