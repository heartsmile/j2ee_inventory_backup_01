package com.j2ee.java.controller;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j2ee.java.model.bo.AdjustmentBO;
import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockInventoryBO;
import com.j2ee.java.model.bo.Utils;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Stock;

@Controller
public class StockAdjustmentController {

	@Autowired
	@Qualifier("StockInventoryBOImpl")
	private StockInventoryBO stockInventoryBO;
	
	@Autowired
	@Qualifier("AdjustmentBOImpl")
	private AdjustmentBO adjustmentBO;

	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;

	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;

	@Autowired
	private Utils utils;

	@RequestMapping(value = "/getListStockAdjustment")
	public @ResponseBody String stockInventory(HttpServletRequest request) throws ParseException {
		String response = "";
		try {
			String stockID = request.getParameter("stockID");
			Gson gson = new Gson();
			
			List<StockAdjustmentView> listSInven = new ArrayList<StockAdjustmentView>();

			List<Object[]> listInventory = stockInventoryBO.getListByStockID(Integer.parseInt(stockID));
			Iterator<Object[]> itr = listInventory.iterator();
			while (itr.hasNext()) {
				Object[] obj = (Object[]) itr.next();

				Product product = new Product();
				product = (Product) obj[0];

				int totalQ = Integer.parseInt(obj[1].toString());

				/* set value */
				StockAdjustmentView sAdjustment = new StockAdjustmentView();
				sAdjustment.setProductID(product.getProductID());
				sAdjustment.setProductName(product.getProductName());
				sAdjustment.setRealQuantity(0);
				sAdjustment.setDifferentQuantity(0);
				sAdjustment.setSubTotal(new BigDecimal(0));
				sAdjustment.setPrice(product.getOrgPrice());
				sAdjustment.setStockQuantity(totalQ);
				// add to list
				listSInven.add(sAdjustment);
			}

			Type type = new TypeToken<List<StockAdjustmentView>>() {
			}.getType();
			
			response = gson.toJson(listSInven, type);
		} catch (Exception e) {
			response = "{\"ID\": \"2\"}";
		}
		return response;
	}
	
	@RequestMapping(value = "/StockAdjustment")
	public String StockAdjustment(Model model) throws ParseException {
		List<Stock> listStock = stockBO.getAllStock();
		model.addAttribute("listStock", listStock);
		
		return "StockAdjustment";
	}
	
	@RequestMapping(value = "/saveStockAdjustment")
	public @ResponseBody String saveStockAdjustment(HttpServletRequest request) throws ParseException {
		String response = "";
		try {
			response = adjustmentBO.insertAdjustment(request);
		} catch (Exception e) {
			response = "{\"ID\": \"2\"}";
		}
		return response;
	}
}
