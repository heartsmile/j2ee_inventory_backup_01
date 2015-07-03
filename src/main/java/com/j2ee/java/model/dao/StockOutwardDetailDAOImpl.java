package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.StockOutwardDetail;

@Component(value="StockOutwardDetailDAOImpl")
public class StockOutwardDetailDAOImpl implements StockOutwardDetailDAO{

	static Logger logger = Logger.getLogger(StockOutwardDetailDAOImpl.class.getName());
	@Override
	public StockOutwardDetail getByID(int id) {

		return (StockOutwardDetail) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(StockOutwardDetail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockOutwardDetail> getAllStockOutwardDetail() {
		
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from StockOutwardDetail").list();
	}

	@Override
	public boolean insertStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(sOutDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save StockOutwardDetail");
		}
		return result;
	}

	@Override
	public boolean updateStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(sOutDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockOutwardDetail");
		}
		return result;
	}

	@Override
	public boolean deleteStockOutwardDetail(StockOutwardDetail sOutDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(sOutDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete StockOutwardDetail");
		}
		return result;
	}
}
