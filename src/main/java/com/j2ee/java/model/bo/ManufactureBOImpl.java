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
import com.j2ee.java.model.dao.ManufactureDAO;
import com.j2ee.java.model.dto.Manufacture;
@Component(value="ManufactureBOImpl")
public class ManufactureBOImpl implements ManufactureBO {

	private static final Logger logger = LoggerFactory.getLogger(ManufactureBOImpl.class);
	@Autowired
	@Qualifier("ManufactureDAOImpl")
	private ManufactureDAO ManufactureDAO;
	
	@Override
	public Manufacture getByID(int id) {
		// TODO Auto-generated method stub
		Manufacture Manufacture = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			Manufacture = ManufactureDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return Manufacture;
	}

	@Override
	public List<Manufacture> getAllManufacture() {
		// TODO Auto-generated method stub
		List<Manufacture> listManufacture = new ArrayList<Manufacture>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listManufacture = ManufactureDAO.getAllManufacture();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listManufacture;
	}

	@Override
	public boolean insertManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = ManufactureDAO.insertManufacture(manufacture);
			
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
	public boolean updateManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = ManufactureDAO.updateManufacture(manufacture);
			
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
	public boolean deleteManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = ManufactureDAO.deleteManufacture(manufacture);
			
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
