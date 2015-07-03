package com.j2ee.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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

import com.j2ee.java.model.bo.CustomerBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockOutwardBO;
import com.j2ee.java.model.dto.Customer;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.report.StockOutwardReportBO;

@Controller
public class StockOutwardController {

	private static final Logger logger = LoggerFactory
			.getLogger(StockOutwardController.class);
	
	@Autowired
	@Qualifier("StockBOImpl")
	private StockBO stockBO;

	@Autowired
	@Qualifier("CustomerBOImpl")
	private CustomerBO customerBO;

	@Autowired
	@Qualifier("StockOutwardBOImpl")
	private StockOutwardBO sOutwardBO;

	@Autowired
	private StockOutwardReportBO stOutReportBO;

	// return page of function StockInward
	@RequestMapping(value = "/StockOutward")
	public String stockInward(Model model) {

		List<Stock> listStock = stockBO.getAllStock();
		model.addAttribute("listStock", listStock);

		List<Customer> listCustomer = customerBO.getAllCustomer();
		model.addAttribute("listCustomer", listCustomer);

		// get current max StockInward ID.
		int maxStockOut = sOutwardBO.getMaxStockOutID();
		model.addAttribute("maxStockOut", (maxStockOut + 1));

		return "StockOutward";
	}

	// save data
	@RequestMapping(value = "/saveStockOutward", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public @ResponseBody String saveStockInward(HttpServletRequest request)
			throws ParseException {

		String response = "";
		try {
			response = sOutwardBO.insertStockOutward(request);
			return response;
		} catch (Exception e) {
			response = "{\"ID\": \"2\",\"MGS\": \"Insert failed. Please try again.\"}";
			return response;
		}
	}

	@RequestMapping(value = "/exportOutward")
	public ModelAndView exportProduct(HttpServletRequest request,
			HttpServletResponse response) {

		String reportIDReq = request.getParameter("currentOutwardID");
		int reportID = -1;
		if (reportIDReq != null) {
			reportID = Integer.parseInt(reportIDReq);
		}

		final String fileName = "E:\\stockOutward.pdf";
		final String reportPath = "E:\\j2ee_backUp_001\\src\\main\\resources\\stockOutward.jrxml";
		try {
			if (reportID > 0) {
				stOutReportBO.runReport(reportPath, fileName, reportID);

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
				logger.error("Can't get report stock outward ID");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

}
