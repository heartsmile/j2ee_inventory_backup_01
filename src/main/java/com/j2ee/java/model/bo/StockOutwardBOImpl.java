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
import com.j2ee.java.model.dao.StockOutwardDAO;
import com.j2ee.java.model.dao.StockOutwardDetailDAO;
import com.j2ee.java.model.dto.Customer;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;
import com.j2ee.java.model.dto.StockOutward;
import com.j2ee.java.model.dto.StockOutwardDetail;

@Component(value = "StockOutwardBOImpl")
public class StockOutwardBOImpl implements StockOutwardBO {

	private static final Logger logger = LoggerFactory
			.getLogger(StockOutwardBOImpl.class);

	@Autowired
	@Qualifier("StockOutwardDAOImpl")
	private StockOutwardDAO stockOutwardDAO;

	@Autowired
	@Qualifier("StaffDAOImpl")
	private StaffDAO staffDAO;

	@Autowired
	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;

	@Autowired
	@Qualifier("StockInventoryDAOImpl")
	private StockInventoryDAO sInventoryDAO;

	@Autowired
	@Qualifier("StockDAOImplCl")
	private StockDAO stockDAO;

	@Autowired
	@Qualifier("StockOutwardDetailDAOImpl")
	private StockOutwardDetailDAO stockOutDetailDAO;

	@Override
	public StockOutward getByID(int id) {

		StockOutward StockOutward = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockOutward = stockOutwardDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return StockOutward;
	}

	@Override
	public List<StockOutward> getAllStockOutward() {

		List<StockOutward> listStockOutward = new ArrayList<StockOutward>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockOutward = stockOutwardDAO.getAllStockOutward();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockOutward;
	}

	@Override
	public String insertStockOutward(HttpServletRequest request) {

		String result = "";
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// get stock inward
			String stockOutward = request.getParameter("0");
			JsonObject stockOutwardObj = new Gson().fromJson(stockOutward,
					JsonObject.class);

			// Save value into database

			StockOutward stockOut = new StockOutward();

			// Get customerID
			Customer customer = new Customer();
			customer.setCustomerID(stockOutwardObj.get("customerID").getAsInt());

			// Get StaffID
			Staff staff = new Staff();
			staff = staffDAO.getByID(stockOutwardObj.get("staffID").getAsInt());

			String dateFormat = stockOutwardObj.get("date").getAsString();
			Date date = Utils.DATE_FORMATTER.parse(dateFormat);

			String reason = stockOutwardObj.get("reason").getAsString();
			String note = stockOutwardObj.get("note").getAsString();
			BigDecimal totalAmount = new BigDecimal(stockOutwardObj.get(
					"totalMoney").getAsFloat());
			int totalNumber = stockOutwardObj.get("totalNumber").getAsInt();

			/* check if component of product is not enough quantity */
			String stockOutwardDetail = request.getParameter("1");
			JsonArray stockOutwardDetailObj = (JsonArray) new Gson().fromJson(
					stockOutwardDetail, JsonArray.class);

			Iterator<JsonElement> it = stockOutwardDetailObj.iterator();
			List<JsonObject> listStockOutwardDetail = new ArrayList<JsonObject>();
			while (it.hasNext()) {
				JsonObject item = it.next().getAsJsonObject();
				if (!item.toString().equals("{}")) {
					listStockOutwardDetail.add(item);
				}
			}
			for (JsonObject jsonObject : listStockOutwardDetail) {
				Product productID = new Product();
				productID = productDAO.getByID(jsonObject.get("productID")
						.getAsInt());

				Stock stockID = new Stock();
				stockID.setStockID(jsonObject.get("stockID").getAsInt());

				StockInventory sInvenCheck = new StockInventory();
				sInvenCheck.setProductID(productID);
				sInvenCheck.setStockID(stockID);
				int currentQuantity = sInventoryDAO
						.getCurrentQuantity(sInvenCheck);

				int quantityOfProduct = jsonObject.get("quantity").getAsInt();

				if ((currentQuantity - quantityOfProduct) < productID
						.getMinStock()) {
					result = "{\"ID\": \"2\",\"MGS\": \"Can not Shipment this product, Please check item "
							+ productID.getProductName() + "\"}";
					return result;
				}
			}

			// set value for StockInward
			stockOut.setCustomerID(customer);
			stockOut.setStaffID(staff);
			stockOut.setDate(date);
			stockOut.setReason(reason);
			stockOut.setNote(note);
			stockOut.setTotalAmount(totalAmount);
			stockOut.setTotalQuantity(totalNumber);

			// save to database
			stockOutwardDAO.insertStockOutward(stockOut);

			// get StockOutwardDetail and save to database

			for (JsonObject jsonObject : listStockOutwardDetail) {
				// Save to StockOutwardDetail
				StockOutwardDetail sOutDetail = new StockOutwardDetail();

				sOutDetail.setOutwardID(stockOut);
				Product product = new Product();
				product = productDAO.getByID(jsonObject.get("productID")
						.getAsInt());
				sOutDetail.setProductID(product);

				Stock stock = new Stock();
				stock = stockDAO.getByID(jsonObject.get("stockID").getAsInt());
				sOutDetail.setStockID(stock);
				// get Price and Amount
				sOutDetail.setNumber(jsonObject.get("quantity").getAsInt());
				sOutDetail.setPrice(product.getOrgPrice());
				BigDecimal amount = BigDecimal.ZERO;
				amount = product.getOrgPrice().multiply(
						new BigDecimal(sOutDetail.getNumber()));
				sOutDetail.setAmount(amount);
				stockOutDetailDAO.insertStockOutwardDetail(sOutDetail);

				// Save to StockInventory
				StockInventory sInventory = new StockInventory();
				sInventory.setDate(date);
				sInventory.setProductID(product);
				sInventory.setStockID(stock);
				sInventory
						.setQuantity(-(jsonObject.get("quantity").getAsInt()));
				sInventory.setPrice(product.getOrgPrice());
				sInventory.setAmount(amount);
				sInventoryDAO.insertStockInventory(sInventory);
			}
			tx.commit();
			result = "{\"ID\": \"1\"}";
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			result = "{\"ID\": \"2\"}";
			logger.error("Error", ex);
		}
		return result;
	}

	@Override
	public boolean updateStockOutward(StockOutward sOutward) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockOutwardDAO.updateStockOutward(sOutward);

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
	public boolean deleteStockOutward(StockOutward sOutward) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockOutwardDAO.deleteStockOutward(sOutward);

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
	public int getMaxStockOutID() {
		int result = 0;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockOutwardDAO.getMaxStockOutID();

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
