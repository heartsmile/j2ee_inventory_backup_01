package com.j2ee.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.ProviderBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockInwardBO;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Provider;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.report.StockInwardReportBO;

@Controller
public class StockInwardController {
	
	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;
	
	@Autowired
	@Qualifier("ProviderBOImpl")
	private ProviderBO providerBO;
	
	@Autowired
	@Qualifier("ProductBOImpl")
	private ProductBO productBO;
	
	@Autowired
	@Qualifier("StockInwardBOImpl")
	private StockInwardBO stockInwardBO;
	
	@Autowired
	private StockInwardReportBO stInReportBO;
	
	private static final Logger logger = LoggerFactory
			.getLogger(StockInwardController.class);
	
	// return page of function StockInward
	@RequestMapping(value = "/StockInward")
	public String stockInward(Model model) {
		
		List<Stock> listStock = stockBO.getAllStock();
		model.addAttribute("listStock", listStock);

		List<Provider> listProvider = providerBO.getAllProvider();
		model.addAttribute("listProvider", listProvider);
		
		// get current max StockInward ID.
		int maxStockIn = stockInwardBO.getMaxStockInID();
		model.addAttribute("maxStockIn", (maxStockIn + 1));
		
		return "StockInward";
	}

	// get list of Provider
	@RequestMapping(value = "/getProvider", method = RequestMethod.GET)
	public @ResponseBody String getProvide() {
		Gson gson = new Gson();
		
		Provider listProvider = providerBO.getByID(1);

		Type type = new TypeToken<Provider>() {
		}.getType();

		String jsonS = gson.toJson(listProvider, type);
		logger.info(jsonS);
		return jsonS;
	}

	// get list of Product
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String getProduct() {
		Gson gson = new Gson();
		String data = "";
		
		List<Product> product = productBO.getAllProduct();
		
		Type type = new TypeToken<List<Product>>() {
		}.getType();
		
		String jsonS = gson.toJson(product, type);
		
		logger.info(jsonS);
		
		data += "[";
		for (int i = 0; i < product.size(); i++) {
			data += "\"";
			data += product.get(i).getProductID() + "|"
					+ product.get(i).getProductName() + "|"
					+ product.get(i).getUnitID().getUnitName() + "|"
					+ product.get(i).getSalePrice() + "|"
					+ product.get(i).getDescription();
			data += "\"";
			if (i < product.size() - 1) {
				data += ",";
			}
		}
		data += "]";

		return data;
	}

	// save data
	@RequestMapping(value = "/saveStockInward", method = RequestMethod.POST, 
			produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String saveStockInward(HttpServletRequest request)
			throws ParseException {

		String response = "";
		logger.info("Calling StockInward Service");
		try {
			response = stockInwardBO.insertStockInward(request);
		} catch (Exception e) {
			response = "{\"ID\": \"2\"}";
		}
		return response;
	}
	
	@RequestMapping(value = "/exportInward")
	public ModelAndView exportProduct(HttpServletRequest request,
			HttpServletResponse response) {

		String reportIDReq = request.getParameter("currentInwardID");
		int reportID = -1;
		if (reportIDReq != null) {
			reportID = Integer.parseInt(reportIDReq);
		}

		final String fileName = "E:\\stockInward.pdf";
		final String reportPath = "E:\\j2ee_backUp_001\\src\\main\\resources\\stockInWard.jrxml";
		try {
			if (reportID > 0) {
				stInReportBO.runReport(reportPath, fileName, reportID);

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
				logger.error("Can't get report stock inward ID");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}
}
