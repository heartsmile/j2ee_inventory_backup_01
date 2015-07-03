package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Adjustment;
@Component(value="AdjustmentDAOImpl")
public class AdjustmentDAOImpl implements AdjustmentDAO {

	static Logger logger = Logger.getLogger(AdjustmentDAOImpl.class.getName());
	@Override
	public Adjustment getByID(int id) {
		// TODO Auto-generated method stub
		return (Adjustment) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Adjustment.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Adjustment> getAllAdjustment() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Adjustment").list();
	}

	@Override
	public boolean insertAdjustment(Adjustment adjustment) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(adjustment);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Adjustment");
		}
		return result;
	}

	@Override
	public boolean updateAdjustment(Adjustment adjustment) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(adjustment);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Adjustment");
		}
		return result;
	}

	@Override
	public boolean deleteAdjustment(Adjustment adjustment) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(adjustment);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Adjustment");
		}
		return result;
	}
}
