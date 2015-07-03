package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Customer;

public interface CustomerDAO {

	public Customer getByID(int id);
	
	public List<Customer> getAllCustomer();
	
	public boolean insertCustomer(Customer customer);
	
	public boolean updateCustomer(Customer customer);
	
	public boolean deleteCustomer(Customer customer);
}
