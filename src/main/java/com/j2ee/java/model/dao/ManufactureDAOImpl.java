package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Manufacture;
@Component(value="ManufactureDAOImpl")
public class ManufactureDAOImpl implements ManufactureDAO {

	static Logger logger = Logger.getLogger(ManufactureDAOImpl.class.getName());
	@Override
	public Manufacture getByID(int id) {
		// TODO Auto-generated method stub
		return (Manufacture) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Manufacture.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Manufacture> getAllManufacture() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Manufacture").list();
	}

	@Override
	public boolean insertManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(manufacture);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Manufacture");
		}
		return result;
	}

	@Override
	public boolean updateManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(manufacture);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Manufacture");
		}
		return result;
	}

	@Override
	public boolean deleteManufacture(Manufacture manufacture) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(manufacture);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Manufacture");
		}
		return result;
	}

}
