package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.ProductType;

public interface ProductTypeBO {

	public ProductType getByID(int id);
	
	public List<ProductType> getAllProductType();
	
	public boolean insertProductType(ProductType productType);
	
	public boolean updateProductType(ProductType productType);
	
	public boolean deleteProductType(ProductType productType);
}
