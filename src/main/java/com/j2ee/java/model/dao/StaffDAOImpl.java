package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Staff;
@Component(value="StaffDAOImpl")
public class StaffDAOImpl implements StaffDAO {

	static Logger logger = Logger.getLogger(StaffDAOImpl.class.getName());
	@Override
	public Staff getByID(int id) {
		// TODO Auto-generated method stub
		return (Staff) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Staff.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> getAllStaff() {
		// TODO Auto-generated method stub
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Staff").list();
	}

	@Override
	public boolean insertStaff(Staff Staff) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(Staff);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Staff");
		}
		return result;
	}

	@Override
	public boolean updateStaff(Staff Staff) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(Staff);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Staff");
		}
		return result;
	}

	@Override
	public boolean deleteStaff(Staff Staff) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(Staff);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Staff");
		}
		return result;
	}
}
