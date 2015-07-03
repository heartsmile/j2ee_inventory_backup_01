package com.j2ee.java.report;

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
import com.j2ee.java.model.bo.StockTransferBO;
import com.j2ee.java.model.dao.HibernateUtil;

@Component
public class StockTransferReportBO {
	
	@Autowired
	@Qualifier("StTransferBOImpl")
	private StockTransferBO stockTransferBO;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	static SessionFactory session = HibernateUtil.getSessionFactory();

	public Session getOpenSession() {

		return session.openSession();
	}

	public void runReport(String path, String fileName, int reportID) {// GEN-FIRST:event_jxBtnReviewActionPerformed
		
		Session s = this.getOpenSession();
		params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION,
				s);
		
		params.put("@StockTransferID", String.valueOf(reportID));
		
		String filePath = fileName;
		try {

			JasperReport reportFile = JasperCompileManager.compileReport(path);
			JasperPrint jPrint = JasperFillManager.fillReport(reportFile,
					params);
			JasperExportManager.exportReportToPdfFile(jPrint, filePath);

		} catch (JRException ex) {
			Logger.getLogger(ProductReportBO.class.getName()).log(Level.ERROR,
					null, ex);
		}
	}

}
