package com.j2ee.java.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.j2ee.java.model.bo.StockInwardBO;
import com.j2ee.java.model.bo.StockTransferBO;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dto.StockInward;
import com.j2ee.java.model.dto.StockTransfer;

@Component
public class StockTransferReportBO {
	
	@Autowired
	@Qualifier("StTransferBOImpl")
	private StockTransferBO stockTransferBO;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	private Date date = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-MM-YY:HH.mm.ss");
	static SessionFactory session = HibernateUtil.getSessionFactory();

	public Session getOpenSession() {

		return session.openSession();
	}

	public void runReport(String path, String fileName, int reportID) {// GEN-FIRST:event_jxBtnReviewActionPerformed
		
		
		StockTransfer stockTransfer = new StockTransfer();
		stockTransfer = stockTransferBO.getByID(reportID);
		
		Session s = this.getOpenSession();
		params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,
				s);
		params.put("@productID", stockTransfer.getProductID().getProductName());
		//params.put("@expectedDate", stockTransfer.getExpectedDate()); 
		params.put("@quantity", stockTransfer.getQuantity());
		params.put("@priority", stockTransferBO.getPriorityString(stockTransfer.getPriority()));
		params.put("@fromStock", stockTransfer.getFromStock().getStockName());
		params.put("@toStock", stockTransfer.getToStock().getStockName());
		params.put("@description", stockTransfer.getDescription());
		params.put("@staffID", stockTransfer.getStaffID().getStaffName());
		String filePath = fileName;
		try {

			JasperReport reportFile = JasperCompileManager.compileReport(path);
			JasperPrint jPrint = JasperFillManager.fillReport(reportFile,
					params);
			//JasperViewer.viewReport(jPrint, false);
			JasperExportManager.exportReportToPdfFile(jPrint, filePath);

		} catch (JRException ex) {
			Logger.getLogger(ProductReportBO.class.getName()).log(Level.ERROR,
					null, ex);
		}
	}

}
