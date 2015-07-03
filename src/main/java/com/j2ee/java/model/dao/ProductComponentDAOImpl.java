package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.ProductComponent;
@Component(value="ProductComponentDAOImpl")
public class ProductComponentDAOImpl implements ProductComponentDAO {

	static Logger logger = Logger.getLogger(ProductComponentDAOImpl.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductComponent> getByProductID(int id) {
		// TODO Auto-generated method stub
		Query query = HibernateUtil.getSessionFactory().getCurrentSession().createQuery("from ProductComponent where ProductID = :productID ");
		query.setParameter("productID", id);
		List<ProductComponent> listProductComponent = query.list();
		return listProductComponent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductComponent> getAllProductComponent() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from ProductComponent").list();
	}

	@Override
	public boolean insertProductComponent(ProductComponent ProductComponent) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(ProductComponent);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save ProductComponent");
		}
		return result;
	}

	@Override
	public boolean updateProductComponent(ProductComponent ProductComponent) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(ProductComponent);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update ProductComponent");
		}
		return result;
	}

	@Override
	public boolean deleteProductComponent(ProductComponent ProductComponent) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(ProductComponent);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete ProductComponent");
		}
		return result;
	}

	@Override
	public int deleteProductCompoByProductID(int id) {
		int result = 0;
		try {
			Query query = HibernateUtil.getSessionFactory().getCurrentSession()
					.createQuery("delete ProductComponent where ProductID = :productID");
			query.setParameter("productID", id);
			result = query.executeUpdate();
		} catch (Exception e) {
			logger.info("Can't delete ProductComponent");
		}
		return result;
	}

}
