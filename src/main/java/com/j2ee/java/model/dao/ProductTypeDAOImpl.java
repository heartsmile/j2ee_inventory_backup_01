package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.ProductType;

@Component(value = "ProductTypeDAOImpl")
public class ProductTypeDAOImpl implements ProductTypeDAO {

	static Logger logger = Logger.getLogger(ProductTypeDAOImpl.class.getName());
	
	@Override
	public ProductType getByID(int id) {
		return (ProductType) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(ProductType.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductType> getAllProvider() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from ProductType").list();
	}

	@Override
	public boolean insertProductType(ProductType productType) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(productType);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Provider");
		}
		return result;
	}

	@Override
	public boolean updateProductType(ProductType productType) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(productType);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Provider");
		}
		return result;
	}

	@Override
	public boolean deleteProductType(ProductType productType) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(productType);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete ProductType");
		}
		return result;
	}

}
