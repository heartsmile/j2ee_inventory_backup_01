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
import com.j2ee.java.model.dao.StockBuildDAO;
import com.j2ee.java.model.dao.StockBuildDetailDAO;
import com.j2ee.java.model.dao.StockDAO;
import com.j2ee.java.model.dao.StockInventoryDAO;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockBuild;
import com.j2ee.java.model.dto.StockBuildDetail;
import com.j2ee.java.model.dto.StockInventory;

@Component(value = "StockBuildBOImpl")
public class StockBuildBOImpl implements StockBuildBO {

	private static final Logger logger = LoggerFactory
			.getLogger(StockBuildBOImpl.class);

	@Autowired
	@Qualifier("StockBuildDAOImpl")
	private StockBuildDAO stockBuildDAO;
	
	@Autowired
	@Qualifier("StockBuildDetailDAOImpl")
	private StockBuildDetailDAO sBuildDetailDAO;
	
	@Autowired
	@Qualifier("ProductDAOImpl")
	private ProductDAO productDAO;
	
	@Autowired
	@Qualifier("StockDAOImplCl")
	private StockDAO stockDAO;
	
	@Autowired
	@Qualifier("StockInventoryDAOImpl")
	private StockInventoryDAO stockInventoryDAO;

	@Override
	public StockBuild getByID(int id) {

		StockBuild StockBuild = null;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			StockBuild = stockBuildDAO.getByID(id);

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return StockBuild;
	}

	@Override
	public List<StockBuild> getAllStockBuild() {

		List<StockBuild> listStockBuild = new ArrayList<StockBuild>();
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			listStockBuild = stockBuildDAO.getAllStockBuild();

			tx.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();
			}
			logger.error("Error", ex);
		}
		return listStockBuild;
	}

	@Override
	public String insertStockBuild(HttpServletRequest request) {

		String result = "";
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// get stock inward
			String stockBuild = request.getParameter("0");
			JsonObject stockBuildObj = new Gson().fromJson(stockBuild,
					JsonObject.class);

			// Save value into database

			StockBuild sBuild = new StockBuild();

			// product
			Product product = new Product();
			product = productDAO.getByID(stockBuildObj.get("productID")
					.getAsInt());

			// Get StaffID
			Staff staff = new Staff();
			staff.setStaffID(stockBuildObj.get("staffID").getAsInt());
			Stock stockID = new Stock();
			stockID = stockDAO.getByID(stockBuildObj.get("stockID").getAsInt());
			String dateFormat = stockBuildObj.get("buildDate").getAsString();
			Date date = Utils.DATE_FORMATTER.parse(dateFormat);
			String note = stockBuildObj.get("note").getAsString();
			BigDecimal totalAmount = new BigDecimal(stockBuildObj.get(
					"totalAmount").getAsFloat());
			int totalQuantity = stockBuildObj.get("totalQuantity").getAsInt();

			/* check if component of product is not enough quantity */
			String proBuildDetail = request.getParameter("1");
			JsonArray proBuildDetailObj = (JsonArray) new Gson().fromJson(
					proBuildDetail, JsonArray.class);

			Iterator<JsonElement> it = proBuildDetailObj.iterator();
			List<JsonObject> listproBuildDetail = new ArrayList<JsonObject>();
			while (it.hasNext()) {
				JsonObject item = it.next().getAsJsonObject();
				if (!item.toString().equals("{}")) {
					listproBuildDetail.add(item);
				}
			}

			for (JsonObject jsonObject : listproBuildDetail) {
				Product compoID = new Product();
				compoID = productDAO.getByID(jsonObject.get("compoID")
						.getAsInt());

				StockInventory sInvenCheck = new StockInventory();
				sInvenCheck.setProductID(compoID);
				sInvenCheck.setStockID(stockID);
				int currentQuantity = stockInventoryDAO
						.getCurrentQuantity(sInvenCheck);

				int quantityCompo = jsonObject.get("quantityCompo").getAsInt();

				if ((currentQuantity - quantityCompo) < compoID.getMinStock()) {
					result = "{\"ID\": \"2\",\"MGS\": \"Can not build this product, Please check item "
							+ compoID.getProductName() + "\"}";
					return result;
				}
			}

			// set value for StockBuild
			sBuild.setProductID(product);
			sBuild.setStaffID(staff);
			sBuild.setStockID(stockID);
			sBuild.setTotalAmount(totalAmount);
			sBuild.setTotalQuantity(totalQuantity);
			sBuild.setBuildDate(date);
			sBuild.setReason(note);

			// save to database
			stockBuildDAO.insertStockBuild(sBuild);

			// update quantity in stock inventory
			StockInventory sInventory = new StockInventory();
			sInventory.setDate(date);
			sInventory.setProductID(product);
			sInventory.setStockID(stockID);
			sInventory.setQuantity(totalQuantity);
			sInventory.setPrice(product.getOrgPrice());
			sInventory.setAmount(totalAmount);
			stockInventoryDAO.insertStockInventory(sInventory);

			// get StockInwardDetail and save to database

			for (JsonObject jsonObject : listproBuildDetail) {
				// Save to Product Build Detail
				StockBuildDetail sBuildDetail = new StockBuildDetail();

				sBuildDetail.setBuildID(sBuild);

				Product compoID = new Product();
				compoID = productDAO.getByID(jsonObject.get("compoID")
						.getAsInt());
				sBuildDetail.setComponentID(compoID);

				int quantitySBuildDetail = jsonObject.get("quantityCompo")
						.getAsInt();
				sBuildDetail.setQuantity(quantitySBuildDetail);
				sBuildDetail.setPrice(compoID.getOrgPrice());
				BigDecimal subTotal = BigDecimal.ZERO;
				subTotal = product.getOrgPrice().multiply(
						new BigDecimal(sBuildDetail.getQuantity()));
				sBuildDetail.setSubTotal(subTotal);

				// Save to StockBuild

				sBuildDetailDAO.insertStockBuildDetail(sBuildDetail);

				// stock inventory detail
				StockInventory sInvenDetail = new StockInventory();
				sInvenDetail.setDate(date);
				sInvenDetail.setProductID(compoID);
				sInvenDetail.setStockID(stockID);
				sInvenDetail.setQuantity(-quantitySBuildDetail);
				sInvenDetail.setPrice(compoID.getOrgPrice());
				sInvenDetail.setAmount(subTotal);
				stockInventoryDAO.insertStockInventory(sInvenDetail);

			}
			tx.commit();
			result = "{\"ID\": \"1\"}";
			return result;
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
	public boolean updateStockBuild(StockBuild stockBuild) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockBuildDAO.updateStockBuild(stockBuild);

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
	public boolean deleteStockBuild(StockBuild stockBuild) {

		boolean result = false;
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			result = stockBuildDAO.deleteStockBuild(stockBuild);

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
