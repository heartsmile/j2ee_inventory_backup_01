package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.Customer;

public interface CustomerBO {

	public Customer getByID(int id);
	
	public List<Customer> getAllCustomer();
	
	public boolean insertCustomer(Customer customer);
	
	public boolean updateCustomer(Customer customer);
	
	public boolean deleteCustomer(Customer customer);
}
