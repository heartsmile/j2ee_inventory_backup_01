package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Stock;
@Component(value="StockDAOImplCl")
public class StockDAOImpl implements StockDAO {

	static Logger logger = Logger.getLogger(StockDAOImpl.class.getName());

	@Override
	public Stock getByID(int id) {
		// TODO Auto-generated method stub
		return (Stock) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Stock.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getAllStock() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Stock").list();
	}

	@Override
	public boolean insertStock(Stock Stock) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(Stock);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Stock");
		}
		return result;
	}

	@Override
	public boolean updateStock(Stock Stock) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(Stock);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Stock");
		}
		return result;
	}

	@Override
	public boolean deleteStock(Stock Stock) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(Stock);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Stock");
		}
		return result;
	}
}
