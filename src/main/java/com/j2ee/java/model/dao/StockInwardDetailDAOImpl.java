package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.StockInwardDetail;
@Component(value="StockInwardDetailDAOImpl")
public class StockInwardDetailDAOImpl implements StockInwardDetailDAO {

	static Logger logger = Logger.getLogger(StockInwardDetailDAOImpl.class.getName());
	@Override
	public StockInwardDetail getByID(int id) {

		return (StockInwardDetail) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(StockInwardDetail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockInwardDetail> getAllStockInwardDetail() {
		
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from StockInwardDetail").list();
	}

	@Override
	public boolean insertStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(stockInDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save StockInwardDetail");
		}
		return result;
	}

	@Override
	public boolean updateStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(stockInDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockInwardDetail");
		}
		return result;
	}

	@Override
	public boolean deleteStockInwardDetail(StockInwardDetail stockInDetail) {

		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(stockInDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete StockInwardDetail");
		}
		return result;
	}

}
