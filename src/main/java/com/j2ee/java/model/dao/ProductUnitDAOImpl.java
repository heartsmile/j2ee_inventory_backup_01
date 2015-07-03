/**
 * 
 */
package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.ProductUnit;

/**
 * @author John Tran
 *
 */
@Component(value = "ProductUnitDAOImpl")
public class ProductUnitDAOImpl implements ProductUnitDAO {
	
	static Logger logger = Logger.getLogger(ProductUnitDAOImpl.class.getName());

	@Override
	public ProductUnit getByID(int id) {
		return (ProductUnit) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(ProductUnit.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductUnit> getProductUnit() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from ProductUnit").list();
	}

	@Override
	public boolean insertProductUnit(ProductUnit productUnit) {
		
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(productUnit);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save productUnit");
		}
		return result;
	}

	@Override
	public boolean updateProductUnit(ProductUnit productUnit) {
		
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(productUnit);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update productUnit");
		}
		return result;
	}

	@Override
	public boolean deleteProductUnit(ProductUnit productUnit) {
		
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(productUnit);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete productUnit");
		}
		return result;
	}

}
