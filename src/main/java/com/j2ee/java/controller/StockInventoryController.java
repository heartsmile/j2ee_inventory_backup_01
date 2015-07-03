package com.j2ee.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.j2ee.java.model.bo.ProductBO;
import com.j2ee.java.model.bo.StockBO;
import com.j2ee.java.model.bo.StockInventoryBO;
import com.j2ee.java.model.bo.Utils;
import com.j2ee.java.model.dto.Product;
import com.j2ee.java.model.dto.Stock;
import com.j2ee.java.model.dto.StockInventory;
import com.j2ee.java.report.StockInventoryReportBO;

@Controller
public class StockInventoryController {

	/*private static final Logger logger = LoggerFactory
			.getLogger(StockInventoryController.class);*/
	
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
	private StockInventoryReportBO stockInventoryReportBO;
	
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
	
	@RequestMapping(value = "/exportInventory")
	public ModelAndView exportInventory(HttpServletRequest request,
			HttpServletResponse response) {

		/*String reportIDReq = request.getParameter("currentOutwardID");
		int stockID = -1;
		if (reportIDReq != null) {
			stockID = Integer.parseInt(reportIDReq);
		}*/

		final String fileName = Utils.LINK_REPORT_OUTPUT + "StockInventoryReport.pdf";
		final String reportPath = Utils.LINK_REPORT_RESOURCE + "reportStockInventory.jrxml";
		try {
			//if (stockID > 0) {
				stockInventoryReportBO.runReport(reportPath, fileName, 0);

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
			/*} else {
				logger.error("Can't get report stock inventory");
			}*/

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}
	
}
