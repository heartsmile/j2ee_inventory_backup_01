package com.j2ee.java.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.j2ee.java.model.bo.StaffBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.dto.Staff;
import com.j2ee.java.model.dto.Stock;

@Controller
public class StockManageController {

	private static final Logger logger = LoggerFactory
			.getLogger(StockManageController.class);

	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;

	@Autowired
	@Qualifier("StaffBOImpl")
	private StaffBO staffBO;

	@RequestMapping(value = "/listStock")
	public String listStock(Model model) {

		logger.info("List stock");

		List<Stock> listStock = stockBO.getAllStock();
		if (listStock == null) {
			return "ErrorPage";
		}
		model.addAttribute("listStock", listStock);

		return "ListStock";
	}

	@RequestMapping(value = "/createStock", method = RequestMethod.GET)
	public String createStock(Model model) {

		List<Staff> listStaff = staffBO.getAllStaff();
		model.addAttribute("listStaff", listStaff);

		model.addAttribute("isEdit", 0);

		return "StockDetail";
	}

	@RequestMapping(value = "/editStock", method = RequestMethod.GET)
	public String editStock(Model model, HttpServletRequest req) {

		List<Staff> listStaff = staffBO.getAllStaff();
		model.addAttribute("listStaff", listStaff);

		logger.info("Edit product!");

		String stockIDRequest = (String) req.getParameter("stockID");
		if (stockIDRequest == null || "".equals(stockIDRequest)) {
			return "ErrorPage";
		}

		int stockID = Integer.parseInt(stockIDRequest);

		Stock stock = stockBO.getByID(stockID);
		if (stock == null) {
			return "ErrorPage";
		}

		// add product information to model
		model.addAttribute("stockToEdit", stock);

		// set this process is create new
		model.addAttribute("isEdit", 1);

		return "StockDetail";
	}

	@RequestMapping(value = "/saveStock")
	public @ResponseBody String saveStock(HttpServletRequest req, Model model) {

		// get stock info
		String stockInfo = req.getParameter("0");
		JsonObject stockObj = new Gson().fromJson(stockInfo, JsonObject.class);

		Stock stock = new Stock();

		// set stock's name
		stock.setStockName(stockObj.get("stockName").getAsString());

		// set address
		stock.setAddress(stockObj.get("address").getAsString());

		// set size
		stock.setSize(stockObj.get("size").getAsInt());

		// set description
		stock.setDesciption(stockObj.get("description").getAsString());

		// set active
		stock.setActive(true);

		// set manager id
		Staff staff = staffBO.getByID(stockObj.get("managerID").getAsInt());
		stock.setManagerID(staff);

		int isEdit = stockObj.get("isEdit").getAsInt();

		if (isEdit == 0) {// create new
			if (stockBO.insertStock(stock)) {
				return "{\"result\" : \"1\", \"isEdit\" : \"1\"}";
			}
		} else {// update
			stock.setStockID(stockObj.get("stockID").getAsInt());
			if (stockBO.updateStock(stock)) {
				return "{\"result\" : \"2\", \"isEdit\" : \"1\"}";
			}
		}

		return "{\"result\" : \"0\"}";
	}

	@RequestMapping(value = "/deleteStock")
	public @ResponseBody String deleteStock(Model model, HttpServletRequest req) {

		// get stock info
		String stockInfo = req.getParameter("0");
		JsonObject stockObj = new Gson().fromJson(stockInfo,
				JsonObject.class);

		int stockID = stockObj.get("stockID").getAsInt();
		if(stockID == 0){
			return "{\"result\" : \"0\"}";
		}
		
		Stock stock = stockBO.getByID(stockID);
		if (stock == null) {
			return "{\"result\" : \"0\"}";
		}

		if (stockBO.deleteStock(stock)) {
			return "{\"result\" : \"1\"}";
		}

		return "{\"result\" : \"0\"}";

	}
}
