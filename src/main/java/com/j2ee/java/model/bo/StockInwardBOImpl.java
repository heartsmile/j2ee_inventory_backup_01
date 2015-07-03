package com.j2ee.java.model.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.j2ee.java.model.dao.ProductDAO;
import com.j2ee.java.model.dao.StaffDAO;
import com.j2ee.java.model.dao.StockDAO;
import com.j2ee.java.model.dao.StockInventoryDAO;
import com.j2ee.java.model.dao.StockInwardDetailDAO;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Provider;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;
import com.j2ee.java.model.dto.StockInward;
import com.j2ee.java.model.dto.StockInwardDetail;
import com.j2ee.java.model.dao.StockInwardDAO;

@Component(value="StockInwardBOImpl")
public class StockInwardBOImpl implements StockInwardBO {

	private static final Logger logger = LoggerFactory.getLogger(StockInwardBOImpl.class);
	
	@Autowired
	@Qualifier("StockInwardDAOImpl")
	private StockInwardDAO stockInwardDAO;
	
	@Autowired
	@Qualifier("StaffDAOImpl")
	private StaffDAO staffDAO;
	
	@Autowired
	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;
	
	@Autowired
	@Qualifier("StockDAOImplCl")
	private StockDAO stockDAO;
	
	@Autowired
	@Qualifier("StockInwardDetailDAOImpl")
	private StockInwardDetailDAO stockInDetailDAO;
	
	@Autowired
	@Qualifier("StockInventoryDAOImpl")
	private StockInventoryDAO stockInventoryDAO;
	
	
	@Override
	public StockInward getByID(int id) {
		
		StockInward StockInward = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockInward = stockInwardDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return StockInward;
	}

	@Override
	public List<StockInward> getAllStockInward() {
		
		List<StockInward> listStockInward = new ArrayList<StockInward>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockInward = stockInwardDAO.getAllStockInward();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockInward;
	}

	@Override
	public String insertStockInward(HttpServletRequest request) {
		
		String result = "";
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// get stock inward
			String stockInward = request.getParameter("0");
			JsonObject stockInwardObj = new Gson().fromJson(stockInward,
					JsonObject.class);

			// Save value into database

			StockInward stockIn = new StockInward();

			// Get ProviderID
			Provider provider = new Provider();
			provider.setProviderID(stockInwardObj.get("providerID").getAsInt());
			
			// Get StaffID
			Staff staff = new Staff();
			staff = staffDAO.getByID(stockInwardObj.get("staffID").getAsInt());

			String dateFormat = stockInwardObj.get("date").getAsString();
			Date date = Utils.DATE_FORMATTER.parse(dateFormat);

			String reason = stockInwardObj.get("reason").getAsString();
			String note = stockInwardObj.get("note").getAsString();
			BigDecimal totalAmount = new BigDecimal(stockInwardObj
					.get("totalMoney").getAsFloat());
			int totalNumber = stockInwardObj.get("totalNumber").getAsInt();

			// set value for StockInward
			stockIn.setProviderID(provider);
			stockIn.setStaffID(staff);
			stockIn.setDate(date);
			stockIn.setReason(reason);
			stockIn.setNote(note);
			stockIn.setTotalAmount(totalAmount);
			stockIn.setTotalNumber(totalNumber);

			// save to database
			stockInwardDAO.insertStockInward(stockIn);

			String stockInwardDetail = request.getParameter("1");
			JsonArray stockInwardDetailObj = (JsonArray) new Gson().fromJson(
					stockInwardDetail, JsonArray.class);
			
			Iterator<JsonElement> it = stockInwardDetailObj.iterator();
			List<JsonObject> listStockInwardDetail = new ArrayList<JsonObject>();
			while (it.hasNext()) {
				JsonObject item = it.next().getAsJsonObject();
				if (!item.toString().equals("{}")) {
					listStockInwardDetail.add(item);
				}
			}

			// get StockInwardDetail and save to database

			for (JsonObject jsonObject : listStockInwardDetail) {
				// Save to StockInwarDetail
				StockInwardDetail stockInDetail = new StockInwardDetail();

				stockInDetail.setInwardID(stockIn);
				Product product = new Product();
				product = productDAO.getByID(jsonObject.get("productID").getAsInt());
				stockInDetail.setProductID(product);
				
				Stock stock = new Stock();
				stock = stockDAO.getByID(jsonObject.get("stockID").getAsInt());
				stockInDetail.setStockID(stock);
				// get Price and Amount
				stockInDetail.setNumber(jsonObject.get("quantity").getAsInt());
				stockInDetail.setPrice(product.getOrgPrice());
				BigDecimal amount = BigDecimal.ZERO;
				amount = product.getOrgPrice().multiply(
						new BigDecimal(stockInDetail.getNumber()));
				stockInDetail.setAmount(amount);
				stockInDetailDAO.insertStockInwardDetail(stockInDetail);
				
				// Save to StockInventory
				StockInventory sInventory = new StockInventory();
				sInventory.setDate(date);
				sInventory.setProductID(product);
				sInventory.setStockID(stock);
				sInventory.setQuantity(jsonObject.get("quantity").getAsInt());
				sInventory.setPrice(product.getOrgPrice());
				sInventory.setAmount(amount);
				stockInventoryDAO.insertStockInventory(sInventory);
			}
			tx.commit();
			result = "{\"ID\": \"1\"}";
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
				result = "{\"ID\": \"2\"}";
			}
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean updateStockInward(StockInward stockInward) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInwardDAO.updateStockInward(stockInward);
			
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
	public boolean deleteStockInward(StockInward stockInward) {
		
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInwardDAO.deleteStockInward(stockInward);
			
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
	public int getMaxStockInID() {
		int result = 0;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockInwardDAO.getMaxStockInID();
			
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

}
