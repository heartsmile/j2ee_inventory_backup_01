/**
 * 
 */
package com.j2ee.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.StaffBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockInventoryBO;
import com.j2ee.java.model.bo.StockTransferBO;
import com.j2ee.java.model.bo.Utils;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;
import com.j2ee.java.model.dto.StockTransfer;
import com.j2ee.java.report.StockTransferReportBO;

/**
 * @author John Tran
 *
 */
@Component
@Controller
public class StockMoveController {

	private static final Logger logger = LoggerFactory
			.getLogger(StockMoveController.class);

	private int stockTransferIDToEdit = 0;

	@Autowired
	@Qualifier("StTransferBOImpl")
	private StockTransferBO stockTransferBO;

	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;

	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;

	@Autowired
	@Qualifier("StaffBOImpl")
	private StaffBO staffBO;

	@Autowired
	@Qualifier("StockInventoryBOImpl")
	private StockInventoryBO stockInventoryBO;

	@Autowired
	private StockTransferReportBO stTransferReportBO;

	@RequestMapping(value = "/StockMove")
	public String stockMove(Model model) {

		// get all stock transfer from database
		List<StockTransfer> listStockTran = stockTransferBO
				.getAllStockTransfer();
		model.addAttribute("listStockTrans", listStockTran);

		return "StockMove";
	}

	// NewStockMoveBill -- StockMoveNew -- POST
	@RequestMapping(value = "/NewStockMoveBill", method = RequestMethod.POST)
	public String newStockMoveBill(Model model) {

		// set this process is create new
		model.addAttribute("isEdit", 0);

		// set stockTransfer to model
		model.addAttribute("curStockTransfer", null);

		// set current stock transfer id to model attribute
		model.addAttribute("curTransferID",
				stockTransferBO.getLastestBillID() + 1);

		// set current status is "New"
		model.addAttribute("curStatus", 1);

		// get list product
		List<Product> listProducts = productBO.getAllProduct();

		// set to model attribute
		model.addAttribute("listProducts", listProducts);

		// get list active stock
		List<Stock> listStocks = stockBO.getAllStock();

		// set to model attribute
		model.addAttribute("listStocks", listStocks);

		return "StockMoveNew";
	}

	// NewStockMoveBill -- StockMoveNew -- POST
	@RequestMapping(value = "/cancelMove", method = RequestMethod.POST)
	public @ResponseBody String cancelMove(HttpServletRequest req) {

		int result = 0;

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get latest stock transfer id from form
		int curIDFromForm = stockMoveObj.get("curTransferID").getAsInt();
		StockTransfer stockTransfer = stockTransferBO.getByID(curIDFromForm);
		if (stockTransferBO.deleteStockTransfer(stockTransfer)) {
			result = 1;
		}

		return "{\"result\" : \"" + result + "\"}";
	}

	// NewStockMoveBill -- StockMoveNew -- GET
	@RequestMapping(value = "/NewStockMoveBill", method = RequestMethod.GET)
	public String newStockMoveBillGET(Model model) {
		return "ErrorPage";
	}

	// updateToAvailable -- update status of bill to available
	@RequestMapping(value = "/updateToAvailable")
	public @ResponseBody String updateToAvailable(HttpServletRequest req) {

		int result = 0;

		// get current stock transfer id
		String curTransferID = req.getParameter("curTransferID");
		if (curTransferID != null && !"".equals(curTransferID)) {
			StockTransfer stockTransfer = stockTransferBO.getByID(Integer
					.parseInt(curTransferID));
			if (stockTransfer == null) {
				result = 0;
			} else {
				stockTransfer.setStatusID(stockTransferBO
						.getStatusID("Available"));
				if (stockTransferBO.updateStockTransfer(stockTransfer)) {
					result = 1;
				}
			}
		}
		return "{\"result\" : \"" + result + "\"}";
	}

	// processLater -- StockMoveWaiting
	@RequestMapping(value = "/processLater")
	public @ResponseBody String processLater(HttpServletRequest req, Model model) {

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get latest stock transfer id from form
		int latestIDFromForm = stockMoveObj.get("curTransferID").getAsInt();

		// get request process type from form
		int isEdit = stockMoveObj.get("isEdit").getAsInt();

		if (latestIDFromForm <= 0) {
			return "{\"result\" : \"0\"}";
		}

		// check if have this stock transfer id in database
		// get latest stock transfer id from database
		// int latestIDFromData = stockTransferBO.getLastestBillID();

		// save data to database
		if (isEdit == 0) {

			// if didn't have:
			// create a stockTransfer object from request, with status is
			// Waiting Available (2)
			StockTransfer stTranfer = createStockTransferDTOObject(req, 2);
			if (stockTransferBO.insertStockTransfer(stTranfer)) {

				// set current stock transfer id to model attribute
				return "{\"result\" : \"1\", \"curTransferID\":\""
						+ stockTransferBO.getLastestBillID()
						+ "\", \"isEdit\":\"1\"}";
			} else {
				return "{\"result\" : \"0\"}";
			}
		} else {

			// if had: update status of stock transfer bill to: Waiting
			StockTransfer stTranfer = stockTransferBO.getByID(latestIDFromForm);
			stTranfer.setStatusID(2); // 2 == Waiting Available
			if (stockTransferBO.updateStockTransfer(stTranfer)) {

				// set current stock transfer id to model attribute
				return "{\"result\" : \"1\", \"curTransferID\":\""
						+ stockTransferBO.getLastestBillID()
						+ "\", \"isEdit\":\"1\"}";
			} else {
				return "{\"result\" : \"0\"}";
			}
		}

	}

	// checkAvailable -- StockMoveAvailable
	@RequestMapping(value = "/checkAvailable")
	public @ResponseBody String checkAvailable(HttpServletRequest req) {

		StockInventory stockInventory = new StockInventory();

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get latest stock transfer id from form
		int latestIDFromForm = stockMoveObj.get("curTransferID").getAsInt();

		if (latestIDFromForm <= 0) {
			return "{\"result\" : \"0\"}";
		}

		// get Product
		Product product = new Product();
		product = productBO.getByID(Integer.parseInt(stockMoveObj
				.get("product").getAsString().split(":")[0]));
		stockInventory.setProductID(product);

		// get quantity
		int quantity = stockMoveObj.get("quantity").getAsInt();
		stockInventory.setQuantity(quantity);

		// get from stock
		Stock fromStock = new Stock();
		fromStock = stockBO.getByID(Integer.parseInt(stockMoveObj
				.get("fromStock").getAsString().split(":")[0]));
		stockInventory.setStockID(fromStock);

		int rs = stockInventoryBO.getCurrentQuantity(stockInventory);

		int result = 0;

		if (rs < 0) {
			result = 0;
		} else {
			if (rs > 0) {
				if (rs > product.getMinStock()) {
					result = 1;
				} else {
					result = -1;
				}
			}
		}

		if (result == 0) {

			// product is not available, so set status is Waiting
			StockTransfer stTranfer = stockTransferBO.getByID(latestIDFromForm);
			stTranfer.setStatusID(2); // 2 == Waiting Available
			stockTransferBO.updateStockTransfer(stTranfer);
		} else {

			// product is available, so set status is available
			StockTransfer stTranfer = stockTransferBO.getByID(latestIDFromForm);
			stTranfer.setStatusID(3); // 3 == Available
			stockTransferBO.updateStockTransfer(stTranfer);
		}

		return "{\"result\" : \"" + result + "\"}";
	}

	// saveNewStockMove Bill
	@RequestMapping(value = "/saveNewStockMove")
	public @ResponseBody String saveStockMove(HttpServletRequest req,
			Model model) {

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get request process type from form
		int curStatus = stockMoveObj.get("curStatus").getAsInt();

		// create a stockTransfer object from request, with its status
		StockTransfer stTranfer = createStockTransferDTOObject(req, curStatus);

		// get latest stock transfer id from form
		int latestIDFromForm = stockMoveObj.get("curTransferID").getAsInt();

		// get request process type from form
		int isEdit = stockMoveObj.get("isEdit").getAsInt();

		// save data to database
		if (isEdit == 0) {
			if (stockTransferBO.insertStockTransfer(stTranfer)) {
				// set current stock transfer id to model attribute
				return "{\"result\" : \"1\", \"curTransferID\":\""
						+ stockTransferBO.getLastestBillID()
						+ "\", \"isEdit\":\"1\"}";
			}
		} else {
			stTranfer.setTransferID(latestIDFromForm);
			if (stockTransferBO.updateStockTransfer(stTranfer)) {
				return "{\"result\" : \"2\"}";
			}
		}

		return "{\"result\" : \"0\"}";
	}

	// processAllTransfer Bill
	@RequestMapping(value = "/processAll")
	public @ResponseBody String processAllTransfer(HttpServletRequest req,
			Model model) {

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);
		int latestIDFromForm = 0;

		// stt is new
		// create a stockTransfer object from request,
		// with status is New (1)
		StockTransfer stTranfer = createStockTransferDTOObject(req, 1);

		// get latest stock transfer id from form
		latestIDFromForm = stockMoveObj.get("curTransferID").getAsInt();

		// get request process type from form
		int isEdit = stockMoveObj.get("isEdit").getAsInt();

		// get latest stock transfer id from database
		// int latestIDFromData = stockTransferBO.getLastestBillID();

		// save data to database
		if (isEdit == 0) {// new
			if (stockTransferBO.insertStockTransfer(stTranfer)) {

				int curTransferID = stockTransferBO.getLastestBillID();

				// check available for this bill
				String rs = checkAvailableForProcessAll(req, curTransferID);
				if (rs.contains("1")) {
					// stt is available
					// change quantity in inventory
					if (!changeQuantityInInventory(stTranfer)) {
						return "{\"result\" : \"0\"}";
					}

					// --> set stt to done
					stockTransferBO.updateStockTransferStatus(curTransferID, 4);

					return "{\"result\" : \"success\"}";
				} else if (rs.contains("-1")) {
					// stt is available but lower than MinValue
					// change quantity in inventory
					if (!changeQuantityInInventory(stTranfer)) {
						return "{\"result\" : \"0\"}";
					}

					// --> set stt to done
					stockTransferBO.updateStockTransferStatus(curTransferID, 4);
					return "{\"result\" : \"lower\"}";
				} else {

					// stt is not available --> set stt to waiting
					stockTransferBO.updateStockTransferStatus(curTransferID, 2);
					return "{\"result\" : \"notAvailable\"}";
				}
			}
		} else {// update
			stTranfer.setTransferID(latestIDFromForm);
			if (stockTransferBO.updateStockTransfer(stTranfer)) {

				// check available for this bill
				String rs = checkAvailable(req);
				if (rs.contains("1")) {

					// stt is available
					// change quantity in inventory
					if (!changeQuantityInInventory(stTranfer)) {
						return "{\"result\" : \"0\"}";
					}

					// --> set stt to done
					stockTransferBO.updateStockTransferStatus(latestIDFromForm,
							4);
					return "{\"result\" : \"success\"}";
				} else if (rs.contains("-1")) {

					// stt is available but lower than MinValue
					// change quantity in inventory
					if (!changeQuantityInInventory(stTranfer)) {
						return "{\"result\" : \"0\"}";
					}

					// --> set stt to done
					stockTransferBO.updateStockTransferStatus(latestIDFromForm,
							4);
					return "{\"result\" : \"lower\"}";
				} else {

					// stt is not available --> set stt to waiting
					stockTransferBO.updateStockTransferStatus(latestIDFromForm,
							2);
					return "{\"result\" : \"notAvailable\"}";
				}
			}
		}

		return "{\"result\" : \"0\"}";
	}

	private StockTransfer createStockTransferDTOObject(HttpServletRequest req,
			int statusID) {

		StockTransfer stTranfer = new StockTransfer();

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get staff object
		 Staff staff = new Staff(); 
		 staff = staffBO.getByID(1);
		 stTranfer.setStaffID(staff);

		// get Reference Type
		stTranfer.setStatusID(statusID);

		// get Product
		Product product = new Product();
		product = productBO.getByID(Integer.parseInt(stockMoveObj
				.get("product").getAsString().split(":")[0]));
		stTranfer.setProductID(product);

		// get expected day
		try {
			stTranfer.setExpectedDate(Utils.DATE_FORMATTER.parse(stockMoveObj
					.get("expectedDay").getAsString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// get quantity
		stTranfer.setQuantity(stockMoveObj.get("quantity").getAsInt());

		// get priority
		stTranfer.setPriority(stockTransferBO.getPriorityID(stockMoveObj.get(
				"priority").getAsString()));

		// get from stock
		Stock fromStock = new Stock();
		fromStock = stockBO.getByID(Integer.parseInt(stockMoveObj
				.get("fromStock").getAsString().split(":")[0]));
		stTranfer.setFromStock(fromStock);

		// get to stock
		Stock toStock = new Stock();
		toStock = stockBO.getByID(Integer.parseInt(stockMoveObj.get("toStock")
				.getAsString().split(":")[0]));
		stTranfer.setToStock(toStock);

		// get description
		stTranfer.setDescription(stockMoveObj.get("description").getAsString());

		return stTranfer;
	}

	@RequestMapping(value = "/navigateToEditBill", method = RequestMethod.POST)
	public @ResponseBody String navigateToEditBill(HttpServletRequest req) {

		String stockTransferID = req.getParameter("stockTransferID");
		if (stockTransferID != null && !"".equals(stockTransferID)) {
			stockTransferIDToEdit = Integer.parseInt(stockTransferID);
		}

		return "{\"result\" : \"1\"}";
	}

	@RequestMapping(value = "/stockMoveEdit")
	public String stockMoveEdit(Model model) {

		StockTransfer stockTransfer = stockTransferBO
				.getByID(stockTransferIDToEdit);

		// set this process is edit
		model.addAttribute("isEdit", 1);

		// set current stock transfer id to model attribute
		model.addAttribute("curTransferID", stockTransferIDToEdit);

		// set stockTransfer to model
		model.addAttribute("curStockTransfer", stockTransfer);

		// set to model attribute, after increase this ID
		model.addAttribute("lastestID", stockTransfer.getTransferID());

		// set current status
		model.addAttribute("curStatus", stockTransfer.getStatusID());

		// get list product
		List<Product> listProducts = productBO.getAllProduct();

		// set to model attribute
		model.addAttribute("listProducts", listProducts);

		// get list active stock
		List<Stock> listStocks = stockBO.getAllStock();

		// set to model attribute
		model.addAttribute("listStocks", listStocks);

		return "StockMoveNew";
	}

	// export stock transfer bill
	@RequestMapping(value = "/exportTransfer")
	public ModelAndView exportProduct(HttpServletRequest request,
			HttpServletResponse response) {

		String reportIDReq = request.getParameter("currentTransferID");
		int reportID = -1;
		if (reportIDReq != null) {
			reportID = Integer.parseInt(reportIDReq);
		} else {
			return null;
		}

		final String fileName = "E:\\stockTransfer.pdf";
		final String reportPath = "E:\\j2ee_backUp_001\\src\\main\\resources\\stockMove.jrxml";
		try {
			if (reportID > 0) {
				stTransferReportBO.runReport(reportPath, fileName, reportID);

				File reportFile = new File(fileName);

				OutputStream outStream = response.getOutputStream();

				String dir = reportFile.getAbsolutePath();
				Path path = Paths.get(dir);

				byte[] fileData = Files.readAllBytes(path);

				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + fileName + "\"");
				response.setContentLength((int) reportFile.length());

				outStream.write(fileData, 0, (int) reportFile.length());
				outStream.close();
			} else {
				logger.error("Can't get report stock move ID");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public String checkAvailableForProcessAll(HttpServletRequest req,
			int currentID) {

		StockInventory stockInventory = new StockInventory();

		// get stock move
		String stockMove = req.getParameter("0");
		JsonObject stockMoveObj = new Gson().fromJson(stockMove,
				JsonObject.class);

		// get latest stock transfer id which is just inserted
		int latestIDFromForm = currentID;

		if (latestIDFromForm <= 0) {
			return "{\"result\" : \"0\"}";
		}

		// get Product
		Product product = new Product();
		product = productBO.getByID(Integer.parseInt(stockMoveObj
				.get("product").getAsString().split(":")[0]));
		stockInventory.setProductID(product);

		// get quantity
		int quantity = stockMoveObj.get("quantity").getAsInt();
		stockInventory.setQuantity(quantity);

		// get from stock
		Stock fromStock = new Stock();
		fromStock = stockBO.getByID(Integer.parseInt(stockMoveObj
				.get("fromStock").getAsString().split(":")[0]));
		stockInventory.setStockID(fromStock);

		int result = stockInventoryBO.checkAvailableOfProduct(stockInventory);

		if (result == 0) {

			// product is not available, so set status is Waiting
			StockTransfer stTranfer = stockTransferBO.getByID(latestIDFromForm);
			stTranfer.setStatusID(2); // 2 == Waiting Available
			stockTransferBO.updateStockTransfer(stTranfer);
		} else {

			// product is available, so set status is available
			StockTransfer stTranfer = stockTransferBO.getByID(latestIDFromForm);
			stTranfer.setStatusID(3); // 3 == Available
			stockTransferBO.updateStockTransfer(stTranfer);
		}

		return "{\"result\" : \"" + result + "\"}";
	}

	private boolean changeQuantityInInventory(StockTransfer stTranfer) {

		boolean result = false;

		// change quantity of product in stock_inventory -- for fromStock
		StockInventory stockInventory = new StockInventory();
		stockInventory.setProductID(stTranfer.getProductID());
		stockInventory.setStockID(stTranfer.getFromStock());
		stockInventory.setQuantity(-stTranfer.getQuantity());// "-" mean
																// decrease
																// quantity
		stockInventory.setPrice(stTranfer.getProductID().getOrgPrice());
		stockInventory.setAmount(stTranfer.getProductID().getOrgPrice()
				.multiply(new BigDecimal(stTranfer.getQuantity())));
		stockInventory.setDate(stTranfer.getExpectedDate());

		result = stockInventoryBO.insertStockInventory(stockInventory);
		if (!result) {
			return result;
		}

		// change quantity of product in stock_inventory -- for toStock
		stockInventory = new StockInventory();
		stockInventory.setProductID(stTranfer.getProductID());
		stockInventory.setStockID(stTranfer.getToStock());
		stockInventory.setQuantity(stTranfer.getQuantity());// "+" mean increase
															// quantity
		stockInventory.setPrice(stTranfer.getProductID().getOrgPrice());
		stockInventory.setAmount(stTranfer.getProductID().getOrgPrice()
				.multiply(new BigDecimal(stTranfer.getQuantity())));
		stockInventory.setDate(stTranfer.getExpectedDate());

		result = stockInventoryBO.insertStockInventory(stockInventory);

		return result;
	}

}
