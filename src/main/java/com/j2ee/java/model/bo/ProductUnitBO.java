/**
 * 
 */
package com.j2ee.java.model.bo;

import java.util.List;

import com.j2ee.java.model.dto.ProductUnit;

/**
 * @author John Tran
 *
 */
public interface ProductUnitBO {
	public ProductUnit getByID(int id);

	public List<ProductUnit> getAllProductUnit();

	public boolean insertProductUnit(ProductUnit productUnit);

	public boolean updateProductUnit(ProductUnit productUnit);

	public boolean deleteProductUnit(ProductUnit productUnit);
}
