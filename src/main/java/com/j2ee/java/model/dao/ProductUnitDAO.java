/**
 * 
 */
package com.j2ee.java.model.dao;

import java.util.List;

import com.j2ee.java.model.dto.ProductUnit;

/**
 * @author John Tran
 *
 */
public interface ProductUnitDAO {

	public ProductUnit getByID(int id);

	public List<ProductUnit> getProductUnit();

	public boolean insertProductUnit(ProductUnit productUnit);

	public boolean updateProductUnit(ProductUnit productUnit);

	public boolean deleteProductUnit(ProductUnit productUnit);
}
