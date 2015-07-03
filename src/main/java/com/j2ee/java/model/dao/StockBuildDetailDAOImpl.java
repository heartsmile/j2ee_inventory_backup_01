package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import com.j2ee.java.model.dto.StockBuildDetail;

@Component(value="StockBuildDetailDAOImpl")
public class StockBuildDetailDAOImpl implements StockBuildDetailDAO {

	static Logger logger = Logger.getLogger(StockBuildDetailDAOImpl.class.getName());
	@Override
	public StockBuildDetail getByID(int id) {
		return (StockBuildDetail) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(StockBuildDetail.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StockBuildDetail> getAllStockBuildDetail() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from StockBuildDetail").list();
	}

	@Override
	public boolean insertStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(sBuildDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save StockBuildDetail");
		}
		return result;
	}

	@Override
	public boolean updateStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(sBuildDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update StockBuildDetail");
		}
		return result;
	}

	@Override
	public boolean deleteStockBuildDetail(StockBuildDetail sBuildDetail) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(sBuildDetail);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete StockBuildDetail");
		}
		return result;
	}

}
