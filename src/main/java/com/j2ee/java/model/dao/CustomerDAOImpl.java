package com.j2ee.java.model.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.dto.Customer;

@Component(value="CustomerDAOImpl")
public class CustomerDAOImpl implements CustomerDAO {

	static Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());
	@Override
	public Customer getByID(int id) {
		return (Customer) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Customer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAllCustomer() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Customer").list();
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.save(customer);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't save Customer");
		}
		return result;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.update(customer);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't update Customer");
		}
		return result;
	}

	@Override
	public boolean deleteCustomer(Customer customer) {
		boolean result = false;
		try {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.delete(customer);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("Can't delete Customer");
		}
		return result;
	}

}
