package com.j2ee.java.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockInventoryBO;
import com.j2ee.java.model.bo.Utils;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;

@Controller
public class StockInventoryController {

	@Autowired
	@Qualifier("StockInventoryBOImpl")
	private StockInventoryBO stockInventoryBO;
	
	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;
	
	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;
	
	@Autowired
	private Utils utils;

	// return page of function StockInward
	@RequestMapping(value = "/StockInventory")
	public String stockInventory(Model model) throws ParseException {

		List<StockInventory> listSInven = new ArrayList<StockInventory>();
		
		List<Object[]> listInventory = stockInventoryBO.getAllStockInventory();
		Iterator<Object[]> itr = listInventory.iterator();
		while (itr.hasNext()) {
			Object[] obj = (Object[]) itr.next();
			String date = String.valueOf(obj[0]);
			
			String proID = String.valueOf(obj[1]);
			Product product = new Product();
			product = productBO.getByID(Integer.parseInt(proID));
			
			String stocID = String.valueOf(obj[2]);
			Stock stock = new Stock();
			stock = stockBO.getByID(Integer.parseInt(stocID));
			
			int totalQ = Integer.parseInt(obj[3].toString());
			
			/* set value */
			StockInventory sInven = new StockInventory();
			sInven.setDate(Utils.DATE_FORMATTER.parse(date));
			sInven.setProductID(product);
			sInven.setStockID(stock);
			sInven.setQuantity(totalQ);
			sInven.setPrice(product.getOrgPrice());
			BigDecimal amount = BigDecimal.ZERO;
			amount = product.getOrgPrice().multiply(
					new BigDecimal(totalQ));
			sInven.setAmount(amount);
			
			// add to list
			listSInven.add(sInven);
		}

		model.addAttribute("listSInven", listSInven);
		
		return "ListStockInventory";
	}
}
