package com.j2ee.java.model.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dao.ProductComponentDAO;
import com.j2ee.java.model.dao.ProductDAO;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.ProductComponent;
@Component(value="ProductComponentBOImpl")
public class ProductComponentBOImpl implements ProductComponentBO {

	private static final Logger logger = LoggerFactory.getLogger(ProductComponentBOImpl.class);
	@Autowired
	@Qualifier("ProductComponentDAOImpl")
	private ProductComponentDAO productCompoDAO;
	
	@Autowired
	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;
	
	@Override
	public List<ProductComponent> getByProductID(int id) {
		// TODO Auto-generated method stub
		List<ProductComponent> ProductComponent = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			ProductComponent = productCompoDAO.getByProductID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return ProductComponent;
	}

	@Override
	public List<ProductComponent> getAllProductComponent() {
		// TODO Auto-generated method stub
		List<ProductComponent> listProductComponent = new ArrayList<ProductComponent>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listProductComponent = productCompoDAO.getAllProductComponent();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listProductComponent;
	}

	@Override
	public String insertProductComponent(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String result = "";
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// get compoID 
			String checkProductID = request.getParameter("0");
			JsonObject checkProductIDObj = new Gson().fromJson(checkProductID,
					JsonObject.class);
			List<ProductComponent> itemCheck = new ArrayList<ProductComponent>();
			itemCheck = productCompoDAO.getByProductID(checkProductIDObj.get("productID").getAsInt());
			// check list
			if (itemCheck.size() > 0) {
				// delete all product Component
				productCompoDAO.deleteProductCompoByProductID(checkProductIDObj.get("productID").getAsInt());
			}
			
			// get product component
			String ajaxData = request.getParameter("1");
			JsonArray productComponentObj = (JsonArray) new Gson().fromJson(
					ajaxData, JsonArray.class);

			Iterator<JsonElement> it = productComponentObj.iterator();
			List<JsonObject> listProCompo = new ArrayList<JsonObject>();
			while (it.hasNext()) {
				JsonObject item = it.next().getAsJsonObject();
				if (!item.toString().equals("{}")) {
					listProCompo.add(item);
				}
			}

			for (JsonObject jsonObject : listProCompo) {

				Product product = new Product();
				product.setProductID(jsonObject.get("productID").getAsInt());

				Product component = new Product();
				component = productDAO.getByID(jsonObject.get("componentID").getAsInt());

				BigDecimal unitPrice = component.getOrgPrice();

				ProductComponent productCompo = new ProductComponent();
				productCompo.setProductID(product);
				productCompo.setComponentID(component);
				productCompo.setQuantity(jsonObject.get("quantity").getAsInt());
				productCompo.setUnitPrice(unitPrice);
				productCompo.setTotal(unitPrice.multiply(new BigDecimal(
						productCompo.getQuantity())));

				productCompoDAO.insertProductComponent(productCompo);
			}
			
			tx.commit();
			result = "{\"ID\": \"1\"}";
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean updateProductComponent(ProductComponent productComponent) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productCompoDAO.updateProductComponent(productComponent);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean deleteProductComponent(ProductComponent productComponent) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productCompoDAO.deleteProductComponent(productComponent);
			
			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public int deleteProductCompoByProductID(int id) {
		int result = 0;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = productCompoDAO.deleteProductCompoByProductID(id);
			
			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return result;
	}

}
