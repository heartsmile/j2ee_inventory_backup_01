package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.ProductType;

public interface ProductTypeDAO {

	public ProductType getByID(int id);
	
	public List<ProductType> getAllProvider();
	
	public boolean insertProductType(ProductType Provider);
	
	public boolean updateProductType(ProductType Provider);
	
	public boolean deleteProductType(ProductType Provider);
}
