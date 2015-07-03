package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.AdjustmentDetail;
@Component(value="AdjustmentDetailDAOImpl")
public class AdjustmentDetailDAOImpl implements AdjustmentDetailDAO {

	static Logger logger = Logger.getLogger(AdjustmentDetailDAOImpl.class.getName());
	@Override
	public AdjustmentDetail getByID(int id) {
		return (AdjustmentDetail) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(AdjustmentDetail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdjustmentDetail> getAllAdjustment() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from AdjustmentDetail").list();
	}

	@Override
	public boolean insertAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(adDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save AdjustmentDetail");
			result = false;
		}
		return result;
	}

	@Override
	public boolean updateAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(adDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Adjustment");
		}
		return result;
	}

	@Override
	public boolean deleteAdjustmentDetail(AdjustmentDetail adDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(adDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete AdjustmentDetail");
		}
		return result;
	}

}
