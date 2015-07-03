package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Provider;
@Component(value="ProviderDAOImpl")
public class ProviderDAOImpl implements ProviderDAO {

	static Logger logger = Logger.getLogger(ProviderDAO.class.getName());

	@Override
	public Provider getByID(int id) {
		// TODO Auto-generated method stub
		return (Provider) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Provider.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Provider> getAllProvider() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Provider").list();
	}

	@Override
	public boolean insertProvider(Provider Provider) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(Provider);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Provider");
		}
		return result;
	}

	@Override
	public boolean updateProvider(Provider Provider) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(Provider);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Provider");
		}
		return result;
	}

	@Override
	public boolean deleteProvider(Provider Provider) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(Provider);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Provider");
		}
		return result;
	}

}
