package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.StockOutward;

@Component(value = "StockOutwardDAOImpl")
public class StockOutwardDAOImpl implements StockOutwardDAO {

	static Logger logger = Logger.getLogger(StockOutwardDAOImpl.class.getName());

	@Override
	public StockOutward getByID(int id) {

		return (StockOutward) HibernateUtil.getSessionFactory()
				.getCurrentSession().get(StockOutward.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockOutward> getAllStockOutward() {

		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from StockOutward").list();
	}

	@Override
	public boolean insertStockOutward(StockOutward sOutward) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(sOutward);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save StockOutward");
		}
		return result;
	}

	@Override
	public boolean updateStockOutward(StockOutward sOutward) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(sOutward);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockOutward");
		}
		return result;
	}

	@Override
	public boolean deleteStockOutward(StockOutward sOutward) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(sOutward);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete StockOutward");
		}
		return result;
	}

	@Override
	public int getMaxStockOutID() {
		int result = 0;
		String hql = "SELECT MAX(outwardID) FROM StockOutward";
		Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		if (results.get(0) != null) {
			result = Integer.parseInt(results.get(0).toString());
		}
		return result;
	}
}
