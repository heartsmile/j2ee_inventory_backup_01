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
import com.j2ee.java.model.dao.AdjustmentDetailDAO;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dao.AdjustmentDAO;
import com.j2ee.java.model.dao.ProductDAO;
import com.j2ee.java.model.dto.Adjustment;
import com.j2ee.java.model.dto.AdjustmentDetail;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;
@Component(value="AdjustmentBOImpl")
public class AdjustmentBOImpl implements AdjustmentBO {

	private static final Logger logger = LoggerFactory.getLogger(AdjustmentBOImpl.class);
	@Autowired
	@Qualifier("AdjustmentDAOImpl")
	private AdjustmentDAO adjustmentDAO;
	
	@Autowired
	@Qualifier("AdjustmentDetailDAOImpl")
	private AdjustmentDetailDAO adDetailDAO;
	
	@Autowired
	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;
	
	@Override
	public Adjustment getByID(int id) {
		// TODO Auto-generated method stub
		Adjustment Adjustment = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			Adjustment = adjustmentDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return Adjustment;
	}

	@Override
	public List<Adjustment> getAllAdjustment() {
		// TODO Auto-generated method stub
		List<Adjustment> listAdjustment = new ArrayList<Adjustment>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listAdjustment = adjustmentDAO.getAllAdjustment();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listAdjustment;
	}

	@Override
	public String insertAdjustment(HttpServletRequest request) {
		
		String result = "";
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// get stock inward
			String adjust = request.getParameter("0");
			JsonObject adjustObj = new Gson().fromJson(adjust,
					JsonObject.class);

			// Save value into database

			Adjustment adjustment = new Adjustment();

			// Get Stock
			Stock stock = new Stock();
			stock.setStockID(adjustObj.get("stockID").getAsInt());
			
			// Get StaffID
			Staff staff = new Staff();
			staff.setStaffID(adjustObj.get("staffID").getAsInt());

			String dateFormat = adjustObj.get("date").getAsString();
			Date date = Utils.DATE_FORMATTER.parse(dateFormat);

			BigDecimal totalDiffAmount = adjustObj.get("totalDiffAmount").getAsBigDecimal();
			int totalDiffQuantity = adjustObj.get("totalDiffQuantity").getAsInt();

			// set value for StockInward
			adjustment.setDate(date);
			adjustment.setStaffID(staff);
			adjustment.setStockID(stock);
			adjustment.setTotalDiffAmount(totalDiffAmount);
			adjustment.setTotalDiffQuantity(totalDiffQuantity);
			// save to database
			adjustmentDAO.insertAdjustment(adjustment);

			String adDetail = request.getParameter("1");
			JsonArray adDetailObj = (JsonArray) new Gson().fromJson(
					adDetail, JsonArray.class);
			
			Iterator<JsonElement> it = adDetailObj.iterator();
			List<JsonObject> listAdjustmentDetail = new ArrayList<JsonObject>();
			while (it.hasNext()) {
				JsonObject item = it.next().getAsJsonObject();
				if (!item.toString().equals("{}")) {
					listAdjustmentDetail.add(item);
				}
			}

			// get StockInwardDetail and save to database

			for (JsonObject jsonObject : listAdjustmentDetail) {
				// Save to StockInwarDetail
				AdjustmentDetail adjDetail = new AdjustmentDetail();

				adjDetail.setAdjustmentID(adjustment);
				Product product = new Product();
				product = productDAO.getByID(jsonObject.get("productID").getAsInt());
				adjDetail.setProductID(product);
				
				int stockQuantity = jsonObject.get("stockQuantity").getAsInt();
				int diffQuantity = jsonObject.get("differentQuantity").getAsInt();
				int realQuantity = jsonObject.get("realQuantity").getAsInt();
				BigDecimal subTotal = jsonObject.get("subTotal").getAsBigDecimal();
				
				adjDetail.setUnitPrice(product.getOrgPrice());
				adjDetail.setSubTotal(subTotal);
				adjDetail.setStockQuantity(stockQuantity);
				adjDetail.setDifferentQuantity(diffQuantity);
				adjDetail.setRealQuantity(realQuantity);
				
				adDetailDAO.insertAdjustmentDetail(adjDetail);
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
	public boolean updateAdjustment(Adjustment Adjustment) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = adjustmentDAO.updateAdjustment(Adjustment);
			
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
	public boolean deleteAdjustment(Adjustment Adjustment) {
		// TODO Auto-generated method stub
		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = adjustmentDAO.deleteAdjustment(Adjustment);
			
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
