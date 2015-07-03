package com.j2ee.java.model.bo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.j2ee.java.model.dto.ProductComponent;

public interface ProductComponentBO {

	public List<ProductComponent> getByProductID(int id);
	
	public List<ProductComponent> getAllProductComponent();
	
	public String insertProductComponent(HttpServletRequest request);
	
	public boolean updateProductComponent(ProductComponent productComponent);
	
	public boolean deleteProductComponent(ProductComponent productComponent);
	
	public int deleteProductCompoByProductID(int id);
}
