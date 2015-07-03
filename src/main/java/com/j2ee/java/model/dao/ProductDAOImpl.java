package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Product;

@Component(value = "ProductDAOImpl")
public class ProductDAOImpl implements ProductDAO {

	static Logger logger = Logger.getLogger(ProductDAOImpl.class.getName());

	@Override
	public Product getByID(int id) {
		// TODO Auto-generated method stub
		return (Product) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Product.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Product").list();
	}

	@Override
	public boolean insertProduct(Product Product) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().save(Product);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Product");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateProduct(Product Product) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(Product);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Product");
		}
		return result;
	}

	@Override
	public boolean deleteProduct(Product Product) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(Product);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Product");
		}
		return result;
	}

}
