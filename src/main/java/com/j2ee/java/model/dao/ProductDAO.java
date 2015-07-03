package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.Product;;

public interface ProductDAO {

	public Product getByID(int id);
	
	public List<Product> getAllProduct();
	
	public boolean insertProduct(Product Product);
	
	public boolean updateProduct(Product Product);
	
	public boolean deleteProduct(Product Product);
}
