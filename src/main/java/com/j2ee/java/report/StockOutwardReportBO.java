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

import com.j2ee.java.model.bo.StockOutwardBO;
import com.j2ee.java.model.dao.HibernateUtil;
import com.j2ee.java.model.dto.StockOutward;

@Component
public class StockOutwardReportBO {
	
	@Autowired
	@Qualifier("StockOutwardBOImpl")
	private StockOutwardBO sOutwardBO;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	private Date date = new Date();
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-MM-YY:HH.mm.ss");
	static SessionFactory session = HibernateUtil.getSessionFactory();

	public Session getOpenSession() {

		return session.openSession();
	}

	public void runReport(String path, String fileName, int reportID) {// GEN-FIRST:event_jxBtnReviewActionPerformed
		
		
		StockOutward stockOut = new StockOutward();
		stockOut = sOutwardBO.getByID(reportID);
		
		Session s = this.getOpenSession();
		params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,
				s);
		params.put("@CustomerName", stockOut.getCustomerID().getCustomerName());
		params.put("@Receiver", stockOut.getCustomerID().getCustomerName());
		params.put("@PhoneNo", stockOut.getCustomerID().getTel());
		params.put("@Address", stockOut.getCustomerID().getAddress());
		params.put("@Reason", stockOut.getReason());
		params.put("@Note", stockOut.getNote());
		params.put("@TotalAmount", stockOut.getTotalAmount());
		params.put("@TotalQuantity", stockOut.getTotalQuantity());
		params.put("@StaffName", stockOut.getStaffID().getStaffName());
		params.put("@outWardID", stockOut.getOutwardID());
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
