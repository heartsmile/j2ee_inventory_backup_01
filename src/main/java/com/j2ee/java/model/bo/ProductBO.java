package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.Product;

public interface ProductBO {

	public Product getByID(int id);
	
	public List<Product> getAllProduct();
	
	public boolean insertProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(Product product);
}
