package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.StockBuild;

@Component(value="StockBuildDAOImpl")
public class StockBuildDAOImpl implements StockBuildDAO {

	static Logger logger = Logger.getLogger(StockBuildDAOImpl.class.getName());
	@Override
	public StockBuild getByID(int id) {

		return (StockBuild) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(StockBuild.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockBuild> getAllStockBuild() {

		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from StockBuild").list();
	}

	@Override
	public boolean insertStockBuild(StockBuild stockBuild) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(stockBuild);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save StockBuild");
		}
		return result;
	}

	@Override
	public boolean updateStockBuild(StockBuild stockBuild) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(stockBuild);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockBuild");
		}
		return result;
	}

	@Override
	public boolean deleteStockBuild(StockBuild stockBuild) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(stockBuild);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete StockBuild");
		}
		return result;
	}

}
