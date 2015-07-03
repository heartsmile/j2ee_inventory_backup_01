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
import com.j2ee.java.model.dao.ProductUnitDAO;
import com.j2ee.java.model.dto.ProductUnit;

@Component(value="ProductUnitBOImpl")
public class ProductUnitBOImpl implements ProductUnitBO {

	private static final Logger logger = LoggerFactory.getLogger(ProductUnitBOImpl.class);
	
	@Autowired
	@Qualifier("ProductUnitDAOImpl")
	private ProductUnitDAO productUnitDAO;
	
	@Override
	public ProductUnit getByID(int id) {
		ProductUnit productUnit = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			productUnit = productUnitDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return productUnit;
	}

	@Override
	public List<ProductUnit> getAllProductUnit() {
		List<ProductUnit> listProductUnit = new ArrayList<ProductUnit>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listProductUnit = productUnitDAO.getProductUnit();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listProductUnit;
	}

	@Override
	public boolean insertProductUnit(ProductUnit productUnit) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productUnitDAO.insertProductUnit(productUnit);
			
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
	public boolean updateProductUnit(ProductUnit productUnit) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productUnitDAO.updateProductUnit(productUnit);
			
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
	public boolean deleteProductUnit(ProductUnit productUnit) {
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productUnitDAO.deleteProductUnit(productUnit);
			
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
